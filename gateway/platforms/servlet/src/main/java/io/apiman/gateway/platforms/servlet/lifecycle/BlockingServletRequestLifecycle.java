package io.apiman.gateway.platforms.servlet.lifecycle;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.CountDownLatch;

/**
 * Manages the lifecycle of a servlet request using the default blocking behaviour
 * of the Servlet container.
 *
 * @author Pete Cornish {@literal outofcoffee@gmail.com}
 */
public class BlockingServletRequestLifecycle implements ServletRequestLifecycle {
    private final HttpServletResponse response;
    private final CountDownLatch latch = new CountDownLatch(1);

    public BlockingServletRequestLifecycle(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void onProcessingStarted() {
        try {
            latch.await();
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public void onProcessingComplete() {
        latch.countDown();
    }

    @Override
    public HttpServletResponse getResponse() {
        return response;
    }
}
