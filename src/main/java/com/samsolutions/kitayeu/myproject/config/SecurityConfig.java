package com.samsolutions.kitayeu.myproject.config;

import com.samsolutions.kitayeu.myproject.services.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserAuthService userAuthService;

    /* -In-Memory
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername("superadmin")
                .password(passwordEncoder.encode("123"))
                .roles("SUPERADMIN")
                .build();
        userDetailsManager.createUser(user);
        return userDetailsManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest()
                .authenticated().and()
                .formLogin();
    }
    */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().httpBasic().disable().authorizeRequests()
                // MVC-controllers
                .antMatchers("/departments/new/**").hasAnyRole("ADMIN", "HR")
                .antMatchers("/departments/{id}/edit/**").hasAnyRole("ADMIN", "HR")
                .antMatchers("/departments/**").authenticated()
                .antMatchers("/users/**").hasRole("ADMIN")
                .antMatchers("/roles/**").authenticated()
                .antMatchers("/employees/**").authenticated()
                // REST-controllers
                .antMatchers("/api/users/**").hasRole("ADMIN")
                .antMatchers("/api/roles/**").authenticated()
                .antMatchers("/api/employees/**").authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/")
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .oauth2ResourceServer()
                .jwt();
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userAuthService);
        return daoAuthenticationProvider;
    }
}
