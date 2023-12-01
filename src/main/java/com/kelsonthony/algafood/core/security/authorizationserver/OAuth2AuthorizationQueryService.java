package com.kelsonthony.algafood.core.security.authorizationserver;

import java.util.List;

import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

public interface OAuth2AuthorizationQueryService {
	 
	List<RegisteredClient> listClientsWithConsent(String principalName);
	List<OAuth2Authorization> listAuthorization(String principalName, String clientId);
}
