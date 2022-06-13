package com.example.sesiones456.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/holamundo").permitAll()
                .antMatchers("/api/laptops").hasRole("USER")
                .anyRequest().authenticated()
                .and().formLogin()
                .and().httpBasic();

        return http.build();
    }

    //Customizar el firewall
    @Bean
    public HttpFirewall looseHttpFirewall(){
        StrictHttpFirewall firewall = new StrictHttpFirewall();

        firewall.setAllowBackSlash(true);
        firewall.setAllowSemicolon(true);

        return firewall;
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("pass"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("pass"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
