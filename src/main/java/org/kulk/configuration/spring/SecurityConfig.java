package org.kulk.configuration.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * User: frisokulk
 * Date: 7/15/16
 * Time: 10:40 PM
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
	/*builder
	    .inMemoryAuthentication()
	    .withUser("root").password("welkom").roles("USER");*/

	builder.jdbcAuthentication().dataSource(dataSource)
	    .usersByUsernameQuery(
		"select user_name, password, enabled from user where user_name=?")
	    .authoritiesByUsernameQuery(
		"select u.user_name, r.role from user_role ur join " +
		    "user u on ur.user_id = u.id " +
		    "join role r on ur.role_id = r.id " +
		    "where u.user_name = ?");
    }


    //http://www.mkyong.com/spring-security/spring-security-form-login-using-database/
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
	http

	    .authorizeRequests()
	    //.antMatchers("/**").access("hasRole('ROLE_USER')")
	    	/*.anyRequest()
	    	.authenticated()*/
	    .antMatchers("/*").hasRole("USER")
	    .antMatchers("/wicket/bookmarkable/org.kulk.web.pages.search..CreatePersonPage").hasRole("ADMIN")
	    .and()
	    .formLogin()
	    .usernameParameter("username")
	    .passwordParameter("password")
	    .loginProcessingUrl("/login")
	    .loginPage("/login")
	    //.failureUrl("/?error=denied")
	    .defaultSuccessUrl("/", true)
	    .permitAll()
	    .and()
	    .csrf()
	    .disable();
    }
}
