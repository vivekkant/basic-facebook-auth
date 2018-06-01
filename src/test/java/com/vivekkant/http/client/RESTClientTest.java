package com.vivekkant.http.client;

import java.util.Properties;

public class RESTClientTest {

	
	public static void main(String[] args) throws Exception {
		
		RESTClient client = new RESTClient();
		
		Properties props = new Properties();
		props.setProperty("client_id", "174629846524289");
		props.setProperty("redirect_uri", "https://a944a50a.ngrok.io/basic-facebook-auth/response");
		props.setProperty("client_secret", "9152aca2a8ef1ca88b326f35d3f31deb");
		props.setProperty("code", "AQCVM2VgJscOCE3OTKwe7a5ya8AKkquHUG_7kceOc0m1tK3HV19aCXq-aPXxONHD5f1GphjDoKqDjSjss3zGR81sVCkUsPEndXFFCrWghBg_xJorcSS9CQl4KKz_xwXwS9qGS2Nvb4T4uffYl43fJ-Zf7kVDSWrxR7USDv6NacvtLAkTbrTIPmXCSFyQz0fyj-OeAviIbxs8gq9-mXmc4YpOcKyVI6HS_Qq2f0oMOCCf0aokQDK8OniyTtyFM3d9ESeTJ3Art7ju5YJWvvAyNSjyMeIop4_3K2CbA8s2U0sSx4-b12TcdRsCcCoJSqOUr0g");
		
		String response = client.getResponseAsString("https://graph.facebook.com/v3.0/oauth/access_token", props);
		System.out.println(response);
		
	}

}
