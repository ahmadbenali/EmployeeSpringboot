package com.example.demo.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    //user accounts hard coded in java source code
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails ahmad= User.builder().username("ahmad").password("{noop}ahmad").roles("Employee").build();
        UserDetails ali= User.builder().username("ali").password("{noop}ali").roles("Employee","Manager").build();
        UserDetails safaa= User.builder().username("safaa").password("{noop}safaa").roles("Employee","Manager","Admin").build();

        return new  InMemoryUserDetailsManager(ahmad,ali,safaa);
    }

    //add database access

    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer
                ->configurer
                .requestMatchers(HttpMethod.GET,"/employees").hasRole("Employee")
                .requestMatchers(HttpMethod.GET,"/employees/**").hasRole("Employee")

                .requestMatchers(HttpMethod.POST,"/employees").hasRole("Manager")

                .requestMatchers(HttpMethod.PUT,"/employees").hasRole("Manager")
                .requestMatchers(HttpMethod.PUT,"/employees/**").hasRole("Manager")

                .requestMatchers(HttpMethod.PATCH,"/employees/**").hasRole("Manager")

                .requestMatchers(HttpMethod.DELETE,"/employees").hasRole("Admin")
                .requestMatchers(HttpMethod.DELETE,"/employees/**").hasRole("Admin")
        );
        //is the line of code that tells Spring Security to
        // enable HTTP Basic Authentication using its standard, default settings
        http.httpBasic(Customizer.withDefaults());

        //disable it if you are building a rest app
        //it's good for real wep app
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
