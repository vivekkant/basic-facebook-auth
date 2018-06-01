package com.vivekkant.http.facebook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivekkant.http.client.RESTClient;

public class FacebookTokenAPI {
	
    private static final Logger logger = Logger.getLogger( FacebookTokenAPI.class ) ;
	
	private String clientId;
	private String secret;
	private String redirectURI;
	
	public static final String ACESS_TOKEN_URI = "https://graph.facebook.com/v3.0/oauth/access_token";
	public static final String TOKEN_DEBUG_URI = "https://graph.facebook.com/debug_token";
	
	public FacebookTokenAPI(String clientId, String secret, String redirectURI) {
		this.clientId = clientId;
		this.secret = secret;
		this.redirectURI = redirectURI;
	}
	
	public AccessToken getAccessToken(String code) {
		
		RESTClient client = new RESTClient();
		String response;
		AccessToken token = null;
		
		try {
			Properties props = new Properties();
			props.setProperty("client_id", clientId);
			props.setProperty("redirect_uri", redirectURI);
			props.setProperty("client_secret", secret);
			props.setProperty("code", code);
			
			response = client.getResponseAsString(ACESS_TOKEN_URI, props);
			logger.debug("RESPONSE: " + response);

			ObjectMapper mapper = new ObjectMapper();
			token = mapper.readValue(response, AccessToken.class);
			logger.debug("TOKEN: " + token);
			
		} catch (Exception e) {
			logger.error("Failed while calling Facebook Token API", e);
		}		
		return token;
	}
	
	public TokenDebug debugToken(AccessToken token) {
		RESTClient client = new RESTClient();
		TokenDebug td = null;
		
		try {
			Properties props = new Properties();
			props.setProperty("input_token", token.getAccess_token());
			props.setProperty("access_token", token.getAccess_token());
			
			String response = client.getResponseAsString(TOKEN_DEBUG_URI, props);
			logger.debug("RESPONSE: " + response);
			
			td = deserializeTokenDebug(response);
			logger.debug("Serialized Response: " + td);

		} catch (Exception e) {
			logger.error("Failed while calling Facebook Token API", e);
		}

		return td;
	}
	
	private TokenDebug deserializeTokenDebug(String json) throws JsonProcessingException, IOException {
		JsonNode node = new ObjectMapper().readTree(json).get("data");
		TokenDebug td = new TokenDebug();
		td.setAppId(node.get("app_id").textValue());
		td.setType(node.get("type").textValue());
		td.setValid(node.get("is_valid").booleanValue());
		td.setExpiry(node.get("expires_at").longValue());
		td.setIssuedAt(node.get("issued_at").longValue());
		td.setUserId(node.get("user_id").textValue());
		
		List<String> scopes = new ArrayList<String>();
		for(JsonNode item : node.get("scopes")) {
			scopes.add(item.textValue());
		}
		td.setScopes(scopes);
		
		return td;
	}

}
