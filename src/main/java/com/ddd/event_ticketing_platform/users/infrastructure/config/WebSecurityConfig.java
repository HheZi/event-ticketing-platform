package com.ddd.event_ticketing_platform.users.infrastructure.config;

import com.ddd.event_ticketing_platform.users.infrastructure.AdminUserDetailsService;
import com.ddd.event_ticketing_platform.users.infrastructure.BuyerUserDetailsService;
import com.ddd.event_ticketing_platform.users.infrastructure.OrganizerUserDetailsService;
import com.ddd.event_ticketing_platform.users.infrastructure.VenueManagerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    @Order(1)
    SecurityFilterChain adminChain(
            HttpSecurity http, AdminUserDetailsService adminUserDetailsService,
            PasswordEncoder encoder
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(adminUserDetailsService);
        provider.setPasswordEncoder(encoder);

        http.securityMatcher("/admin/**")
                .authenticationProvider(provider)
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/admin/login").permitAll()
                        .anyRequest().hasRole("ADMIN"))
                .formLogin(f -> f
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/login")
                        .defaultSuccessUrl("/admin/home", true))
                .logout(l -> l.logoutUrl("/admin/logout"));
        return http.build();
    }

    @Bean
    @Order(2)
    SecurityFilterChain buyerChain(
            HttpSecurity http, BuyerUserDetailsService buyerUserDetailsService,
            PasswordEncoder encoder
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(buyerUserDetailsService);
        provider.setPasswordEncoder(encoder);

        http.securityMatcher("/buyer/**")
                .authenticationProvider(provider)
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/buyer/login").permitAll()
                        .anyRequest().hasRole("BUYER"))
                .formLogin(f -> f
                        .loginPage("/buyer/login")
                        .loginProcessingUrl("/buyer/login")
                        .defaultSuccessUrl("/buyer/home", true))
                .logout(l -> l.logoutUrl("/buyer/logout"));
        return http.build();
    }

    @Bean
    @Order(3)
    SecurityFilterChain organizerChain(
            HttpSecurity http, OrganizerUserDetailsService organizerUserDetailsService,
            PasswordEncoder encoder
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(organizerUserDetailsService);
        provider.setPasswordEncoder(encoder);

        http.securityMatcher("/organizer/**")
                .authenticationProvider(provider)
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/organizer/login").permitAll()
                        .anyRequest().hasRole("ORGANIZER"))
                .formLogin(f -> f
                        .loginPage("/organizer/login")
                        .loginProcessingUrl("/organizer/login")
                        .defaultSuccessUrl("/organizer/home", true))
                .logout(l -> l.logoutUrl("/organizer/logout"));
        return http.build();
    }

    @Bean
    @Order(4)
    SecurityFilterChain venueManagerChain(
            HttpSecurity http, VenueManagerUserDetailsService venueManagerUserDetailsService,
            PasswordEncoder encoder
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(venueManagerUserDetailsService);
        provider.setPasswordEncoder(encoder);

        http.securityMatcher("/venue-manager/**")
                .authenticationProvider(provider)
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/venue-manager/login").permitAll()
                        .anyRequest().hasRole("VENUE_MANAGER"))
                .formLogin(f -> f
                        .loginPage("/venue-manager/login")
                        .loginProcessingUrl("/venue-manager/login")
                        .defaultSuccessUrl("/venue-manager/home", true))
                .logout(l -> l.logoutUrl("/venue-manager/logout"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

}
