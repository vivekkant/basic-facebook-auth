package com.vivekkant.http.facebook;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivekkant.http.client.RESTClient;

public class FacebookGraphAPI {
	
    private static final Logger logger = Logger.getLogger( FacebookGraphAPI.class ) ;
	
	private String token;
	
	public static final String GRAPH_URI = "https://graph.facebook.com/v3.0";
	
	public FacebookGraphAPI(String token) {
		this.token = token;
	}
	
	public Profile getBasicProfile(String userId) {
		
		String URI = GRAPH_URI + "/" + userId;
		RESTClient client = new RESTClient();
		Profile profile = null;
		
		try {
			Properties props = new Properties();
			props.setProperty("access_token", this.token);			
			props.setProperty("fields", "name,email");
			
			String response = client.getResponseAsString(URI, props);
			logger.debug("RESPONSE: " + response);

			ObjectMapper mapper = new ObjectMapper();
			profile = mapper.readValue(response, Profile.class);
			logger.debug("Profile: " + profile);
			
		} catch (Exception e) {
			logger.error("Failed while calling Facebook Graph API", e);
		}	
		
		return profile;
	}
	

}
