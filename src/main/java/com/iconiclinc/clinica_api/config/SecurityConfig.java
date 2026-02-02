package com.iconiclinc.clinica_api.config;

import com.iconiclinc.clinica_api.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(eh -> eh
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/usuarios/login"). permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuarios/register").permitAll()
                        .requestMatchers("/api/profesional/**").hasRole("PROFESIONAL")
                        .requestMatchers(HttpMethod.GET,"/api/usuario").hasRole("PROFESIONAL")
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/*").hasRole("PROFESIONAL")
                        .requestMatchers(HttpMethod.POST,"/api/rutinas/**").hasRole("PROFESIONAL")
                        .requestMatchers(HttpMethod.PUT,  "/api/rutinas/**").hasRole("PROFESIONAL")
                        .requestMatchers(HttpMethod.DELETE,"/api/rutinas/**").hasRole("PROFESIONAL")
                        .requestMatchers(HttpMethod.POST, "/api/tratamientos/**").hasRole("PROFESIONAL")
                        .requestMatchers(HttpMethod.PUT,  "/api/tratamientos/**").hasRole("PROFESIONAL")
                        .requestMatchers(HttpMethod.DELETE,"/api/tratamientos/**").hasRole("PROFESIONAL")
                        .requestMatchers(HttpMethod.POST, "/api/recomendaciones/**").hasRole("PROFESIONAL")
                        .requestMatchers(HttpMethod.PUT,  "/api/recomendaciones/**").hasRole("PROFESIONAL")
                        .requestMatchers(HttpMethod.DELETE,"/api/recomendaciones/**").hasRole("PROFESIONAL")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
