package com.abandonedlabs.movierama.configuration;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * The type Cors filter.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

	/**
	 * Do filter.
	 *
	 * @param req   the req
	 * @param res   the res
	 * @param chain the chain
	 * @throws IOException      the io exception
	 * @throws ServletException the servlet exception
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, PATCH, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization");
		chain.doFilter(req, res);
	}

	/**
	 * Init.
	 *
	 * @param filterConfig the filter config
	 */
	public void init(FilterConfig filterConfig) {
	}

	/**
	 * Destroy.
	 */
	public void destroy() {
	}
}