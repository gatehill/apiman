package io.apiman.gateway.platforms.servlet.lifecycle;

import javax.servlet.http.HttpServletResponse;

/**
 * Manages the lifecycle of a servlet request, allowing implementations to choose
 * whether to process requests asynchronously or in a blocking fashion.
 *
 * @author Pete Cornish {@literal outofcoffee@gmail.com}
 */
public interface ServletRequestLifecycle {
    void onProcessingStarted();

    void onProcessingComplete();

    HttpServletResponse getResponse();
}
