package com.epam.polinakrukovich.worldvision.controller;

import com.epam.polinakrukovich.worldvision.util.AuthUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter for servlet requests. It checks the existence of the
 * user specified by credentials passed in the request. Also
 * it sets some response headers (Access-Control-Allow-Origin,
 * Access-Control-Allow-Methods, Access-Control-Allow-Headers)
 * to allow requests from all the domains, all request methods
 * and headers.
 */
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) { }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods","*");
        resp.addHeader("Access-Control-Allow-Headers","*");

        String userId = req.getParameter("userId");
        if (userId != null && !userId.isEmpty()) {
            AuthUtil auth = AuthUtil.getInstance();
            if (auth.checkIfUserExists(userId)) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() { }
}
