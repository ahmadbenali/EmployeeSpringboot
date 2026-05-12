package com.example.demo.Security;


import com.example.demo.Service.MongoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {


    // Replace InMemoryUserDetailsManager with MongoDB-backed auth
    private final MongoUserDetailsService mongoUserDetailsService;

    public DemoSecurityConfig(MongoUserDetailsService mongoUserDetailsService) {
        this.mongoUserDetailsService = mongoUserDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(mongoUserDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // no encoding, plain text
    }


    //add database access
    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

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
