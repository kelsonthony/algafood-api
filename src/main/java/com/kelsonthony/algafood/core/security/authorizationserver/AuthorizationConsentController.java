package com.kelsonthony.algafood.core.security.authorizationserver;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthorizationConsentController {

	private final RegisteredClientRepository registeredClientRepository;
	private final OAuth2AuthorizationConsentService oAuth2AuthorizationConsentService;
	
	@GetMapping("/oauth2/consent")
	public String consent(
			Principal principal,
			Model model,
			@RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId,
			@RequestParam(OAuth2ParameterNames.SCOPE) String scope,
			@RequestParam(OAuth2ParameterNames.STATE) String state
			) throws AccessDeniedException {
		RegisteredClient client = this.registeredClientRepository.findByClientId(clientId);
		
		if (client == null) {
			throw new AccessDeniedException(String.format("Client de %d não foi encontrado.", clientId));
		}
		
		OAuth2AuthorizationConsent consent = this.oAuth2AuthorizationConsentService.findById(client.getId(), principal.getName());
		
		String[] scopearray = StringUtils.delimitedListToStringArray(scope, " ");
		
		Set<String> scopesParaAprovar = new HashSet<String>(Set.of(scopearray));
		
		Set<String> scopesAProvadsAnteriormente;
		
		if (consent != null) {
			scopesAProvadsAnteriormente = consent.getScopes();
			scopesParaAprovar.removeAll(scopesAProvadsAnteriormente);
		} else {
			scopesAProvadsAnteriormente = Collections.emptySet();
		}
		
		model.addAttribute("clientId", clientId);
		model.addAttribute("state", state);
		model.addAttribute("principalName", principal.getName());
		model.addAttribute("scopesParaAprovar", scopesParaAprovar);
		model.addAttribute("scopesAProvadsAnteriormente", scopesAProvadsAnteriormente);
		
		return "pages/approval";
	}
	
}
