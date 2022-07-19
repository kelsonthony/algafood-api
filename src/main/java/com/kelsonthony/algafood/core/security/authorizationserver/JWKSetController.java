package com.kelsonthony.algafood.core.security.authorizationserver;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.jwk.JWKSet;

@RestController
public class JWKSetController {
	
	@Autowired
	private JWKSet jwkSet;

	@GetMapping("/.well-know/jwks.json")
	public Map<String, Object> keys() {
		System.out.println("JWKS Endpoint");
		return this.jwkSet.toJSONObject();
	}
}
