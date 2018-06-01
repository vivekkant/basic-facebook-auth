package com.vivekkant.util ;

import java.io.InputStream ;

import org.apache.commons.collections.ExtendedProperties ;
import org.apache.log4j.Logger;

import com.vivekkant.web.controller.FacebookLoginController;

public class ConfigMgr extends ExtendedProperties {

    private static final long serialVersionUID = 1L ;
    private static final ConfigMgr instance = new ConfigMgr() ;
    
    private static final Logger logger = Logger.getLogger( FacebookLoginController.class ) ;
    
    private ConfigMgr() {
        InputStream is = ConfigMgr.class.getResourceAsStream( "/app.properties" ) ;
        try {
            initialize( is ) ;
        } catch( Exception e ) {
            logger.error( "Error loading app properties", e) ;
        }
    }
    
    public static ConfigMgr getInstance() { return instance ; }
    
    public void initialize( InputStream is ) throws Exception {
        super.load( is ) ;
    }
}