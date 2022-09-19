// package com.entando.apiman.config;

// import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
// import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
// import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
// import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
// import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Import;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
// import org.springframework.security.core.session.SessionRegistry;
// import org.springframework.security.core.session.SessionRegistryImpl;
// import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
// import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;


// @Configuration
// @EnableWebSecurity
// @ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
// @EnableGlobalMethodSecurity(jsr250Enabled = true)
// //@Import(SecurityProblemSupport.class)
// @Import(KeycloakSpringBootConfigResolver.class)
// @KeycloakConfiguration
// public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter
// {
//     /**
//      * Registers the KeycloakAuthenticationProvider with the authentication manager.
//      */
// 	  @Autowired
// 	    public void configureGlobal(
// 	            AuthenticationManagerBuilder auth) throws Exception {

// 	        KeycloakAuthenticationProvider keycloakAuthenticationProvider
// 	                = keycloakAuthenticationProvider();
// 	        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(
// 	                new SimpleAuthorityMapper());
// 	        auth.authenticationProvider(keycloakAuthenticationProvider);
// 	    }
//     /**
//      * Defines the session authentication strategy.
//      */
//     @Bean
//     @Override
//     protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//         return new RegisterSessionAuthenticationStrategy(buildSessionRegistry());
//     }

//     @Bean
//     protected SessionRegistry buildSessionRegistry() {
//         return new SessionRegistryImpl();
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception
//     {
//         super.configure(http);
//         http
//         .exceptionHandling()
//         .and()
//         .authorizeRequests()
//         .antMatchers(HttpMethod.OPTIONS,"/api/apiman/*").permitAll()
//         .anyRequest()
//         .permitAll();
//     }
// }