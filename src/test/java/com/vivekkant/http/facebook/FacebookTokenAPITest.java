package com.vivekkant.http.facebook;

import com.vivekkant.util.ConfigMgr;

public class FacebookTokenAPITest {

	public static void main(String[] args) throws Exception {
		
		String code = "AQBD1rhL1kfG45P424TerVrl_PpyTkPK23pY8tkJzqo5RvNJ6uzap-IgK8LMs-YqffGRxKtiRkgg4srLY-adohmAdSqk9IB1VD-E3PqxFkVrcgxZYvi4UQpcVJ7jprpctZZJDbcWcS6RJFpSIlXQQlqr56ANbsXcgAOLo0gCZ-5v7iIP0L2cmyP5u-XRW-Rcuj_y22ieTUMoGJoYPBCBQylm-hNRjrcMlvZYZqWLNz2Ss4WuCitJSm98tpiaH2eKl0TkZmQvjoj_mrofSaMTWk4Vx1HQhxhTuo-fzjr2gXHrSYt2aaA5GI6sdlwDy99Uclg";
		
		ConfigMgr cfg = ConfigMgr.getInstance();
		FacebookTokenAPI api = new FacebookTokenAPI(cfg.getString("clientId"), 
													cfg.getString("secret"), 
													cfg.getString("redirectURI"));
		AccessToken token = api.getAccessToken(code);
		System.out.println(token);
		
	}

}
