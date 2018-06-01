/**
 * 
 */
package com.vivekkant.http.client;

import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vivekkant.http.facebook.FacebookTokenAPI;

/**
 * @author Vivek Kant
 *
 */
public class RESTClient {
	
    private static final Logger logger = Logger.getLogger( RESTClient.class ) ;

    private boolean secure = false;

	public RESTClient() {
		this.secure = false;
	}
	
	public RESTClient(boolean secure) {
		this.secure = secure;
	}
	
	public String getResponseAsString(String uri) throws Exception {
		
		Client client = Client.create();
		
		logger.info("");
		logger.info("-------------------------------------------------------------------");
		logger.info("Calling API: " + uri);
		logger.info("-------------------------------------------------------------------");
		logger.info("");
		
		WebResource webResource = client.resource(uri);
		ClientResponse response = webResource.accept("application/json")
											 .get(ClientResponse.class);
		
		if (response.getStatus() != 200) {
				logger.error("Failed : HTTP error code : " + response.getStatus());
			   throw new Exception("Failed : HTTP error code : "
				+ response.getStatus() + " \n " + response.getEntity(String.class));
		}
		
		String output = response.getEntity(String.class);
		logger.info("");
		logger.info("-------------------------------------------------------------------");
		logger.info("API Response: " + output);
		logger.info("-------------------------------------------------------------------");
		logger.info("");
		return output;
	}
	
	public String getResponseAsString(String uri, Properties params) throws Exception {
		
		String queryString = createQueryString(params);
		String fulluri = uri + "?" + queryString;
		return getResponseAsString(fulluri);
	}
	
	@SuppressWarnings("unchecked")
	public String createQueryString(Properties params) {
		StringBuilder buf = new StringBuilder();
		boolean start = true;
	    Enumeration<String> enums = (Enumeration<String>) params.propertyNames();
	    while (enums.hasMoreElements()) {
	    	String key = enums.nextElement();
			if (!start) buf.append("&");
			start = false;
			buf.append(key);
			buf.append("=");
			buf.append(params.get(key));
	    }
		
		return buf.toString();
	}
	

}
