package org.kulk.configuration.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * User: frisokulk
 * Date: 7/15/16
 * Time: 10:40 PM
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth
	    .inMemoryAuthentication()
	    .withUser("root").password("welkom").roles("USER");
    }
}
