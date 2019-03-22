package by.etc.shop.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class Encoding implements Filter {

    public static final String UTF_8 = "UTF-8";
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding(UTF_8);
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {
        this.filterConfig = null;
    }

}

