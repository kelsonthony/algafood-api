package com.kelsonthony.algafood.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@SuppressWarnings("deprecation")
@Component
public class ApiDeprecationHandler extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		if(request.getRequestURI().startsWith("/v1/")) {
			/*
			 * response.addHeader("X-AlgaFood-Deprecated",
			 * "Essa versão da API está depreciada e deixará de exisitar a partir de 01/02/2022."
			 * );
			 */
			response.setStatus(HttpStatus.GONE.value());
			return false;
		}
		
		
		return true;
	}
}
