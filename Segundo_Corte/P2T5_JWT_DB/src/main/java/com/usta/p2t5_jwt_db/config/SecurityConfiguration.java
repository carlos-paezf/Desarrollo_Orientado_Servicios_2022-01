package com.usta.p2t5_jwt_db.config;

import com.usta.p2t5_jwt_db.filter.JWTFilter;
import com.usta.p2t5_jwt_db.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Carlos Páez
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService _userService;

    @Autowired
    private JWTFilter _jwtFilter;

    /**
     * This function is called by the Spring Security framework to configure the
     * authentication manager. The authentication manager is responsible for
     * authenticating users.
     * 
     * @param auth AuthenticationManagerBuilder
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(_userService);
    }

    /**
     * The function is used to expose the AuthenticationManager from the
     * WebSecurityConfigurerAdapter for use by the framework
     * 
     * @return AuthenticationManager
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * If the request is to the /auth/login endpoint, allow it. Otherwise, require
     * authentication.
     * 
     * @param httpSecurity This is the object that is used to configure the security
     *                     of the application.
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(_jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
