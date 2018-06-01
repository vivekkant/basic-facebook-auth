package com.vivekkant.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vivekkant.http.facebook.AccessToken;
import com.vivekkant.http.facebook.FacebookGraphAPI;
import com.vivekkant.http.facebook.FacebookTokenAPI;
import com.vivekkant.http.facebook.TokenDebug;
import com.vivekkant.util.ConfigMgr;

@Controller
public class FacebookLoginController {
	
    private static final Logger logger = Logger.getLogger( FacebookLoginController.class ) ;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView printWelcome() {
		
		ConfigMgr cfg = ConfigMgr.getInstance();

		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		model.addObject("message", "Facebook Authentication Test");
		model.addObject("clientId", cfg.get("clientId"));
		model.addObject("redirectURI", cfg.get("redirectURI"));
		model.addObject("state", "xyz");
		return model;

	}

	
	@RequestMapping(value = "/response", method = RequestMethod.GET)
	public ModelAndView response(@RequestParam("code") String code) {
		
		ModelAndView model = new ModelAndView();
		FacebookTokenAPI tapi = getTokenAPI();
		
		model.setViewName("response");
		model.addObject("tokenAccessURI", FacebookTokenAPI.ACESS_TOKEN_URI);
		model.addObject("tokenDebugURI", FacebookTokenAPI.TOKEN_DEBUG_URI);
		model.addObject("graphURI", FacebookGraphAPI.GRAPH_URI);
		
		model.addObject("code", code);
		
		AccessToken token = tapi.getAccessToken(code);
		model.addObject("token", token);
		
		TokenDebug td = tapi.debugToken(token);
		model.addObject("td", td);
		
		FacebookGraphAPI gapi = getGraphAPI(token.getAccess_token());
		model.addObject("profile", gapi.getBasicProfile(td.getUserId()));

		return model;

	}
	
	private FacebookTokenAPI getTokenAPI() {
		ConfigMgr cfg = ConfigMgr.getInstance();
		FacebookTokenAPI api = new FacebookTokenAPI(cfg.getString("clientId"), 
													cfg.getString("secret"), 
													cfg.getString("redirectURI"));
		return api;
	}
	
	private FacebookGraphAPI getGraphAPI(String token) {
		FacebookGraphAPI api = new FacebookGraphAPI(token);
		return api;
	}
	
}