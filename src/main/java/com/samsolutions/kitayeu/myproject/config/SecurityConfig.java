package com.samsolutions.kitayeu.myproject.config;

import com.samsolutions.kitayeu.myproject.services.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
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
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/departments/new/**").hasAnyRole("ADMIN", "HR")
                .antMatchers("/api/departments/{id}/**").hasAnyRole("ADMIN", "HR")
                .antMatchers("/api/departments/**").authenticated()
                .antMatchers("/api/users/**").hasRole("ADMIN")
                .antMatchers("/api/roles/edit/**").hasAnyRole("ADMIN","HR")
                .antMatchers("/api/roles/new/**").hasAnyRole("ADMIN","HR")
                .antMatchers("/api/roles/delete/**").hasAnyRole("ADMIN","HR")
                .antMatchers("/api/roles/**").authenticated()
                .antMatchers("/api/employees/department={departmentName}/**").hasAnyRole("ADMIN","HR")
                .antMatchers("/api/employees/edit/**").hasAnyRole("ADMIN","HR")
                .antMatchers("/api/employees/new/**").hasAnyRole("ADMIN","HR")
                .antMatchers("/api/employees/delete/**").hasAnyRole("ADMIN","HR")
                .antMatchers("/api/employees/**").authenticated()
                .antMatchers("/api").permitAll()
                .and()
                .formLogin().defaultSuccessUrl("/api")
                .and()
                .logout().logoutSuccessUrl("/api");
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userAuthService);
        return daoAuthenticationProvider;
    }
}
