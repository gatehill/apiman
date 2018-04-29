package io.apiman.gateway.platforms.servlet.lifecycle;

import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Optional.ofNullable;

/**
 * Manages the lifecycle of a servlet request using asynchronous thread management,
 * provided that the Servlet container supports it.
 *
 * @author Pete Cornish {@literal outofcoffee@gmail.com}
 */
public class AsyncServletRequestLifecycle implements ServletRequestLifecycle {
    private static final int DEFAULT_ASYNC_TIMEOUT_MS = 60000;
    private final HttpServletRequest request;
    private final int asyncTimeoutMs;
    private AsyncContext async;

    public AsyncServletRequestLifecycle(HttpServletRequest request, ServletConfig config) {
        if (!request.isAsyncSupported()) {
            throw new IllegalStateException("Asynchronous processing is not supported");
        }
        this.request = request;
        asyncTimeoutMs = ofNullable(config.getInitParameter("asyncTimeoutMs"))
                .map(Integer::parseInt).orElse(DEFAULT_ASYNC_TIMEOUT_MS);
    }

    @Override
    public void onProcessingStarted() {
        async = request.startAsync();
        async.setTimeout(asyncTimeoutMs);
    }

    @Override
    public void onProcessingComplete() {
        async.complete();
    }

    /**
     * @return the {@link HttpServletResponse}
     */
    @Override
    public HttpServletResponse getResponse() {
        return (HttpServletResponse) async.getResponse();
    }
}
