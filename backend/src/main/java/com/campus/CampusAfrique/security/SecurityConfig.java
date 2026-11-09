package com.campus.CampusAfrique.security;

import com.campus.CampusAfrique.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**",
                                "/uploads/**",
                                "/api/dashboard/**",
                                "/api/documentsConsultation/**",
                                "/api/etablissements/**",
                                "/api/etudiants/**",
                                "/api/admins/**",
                                "/api/agents-ambassade/**",
                                "/api/agents-ministere/**",
                                "/api/attestations/**",
                                "/api/candidatures/**",
                                "/api/demandes-bourse/**",
                                "/api/documents/**",
                                "/api/dossiers/**",
                                "/api/formations/**",
                                "/api/inscriptions/**",
                                "/api/niveaux-scolaires/**",
                                "/api/notifications/**",
                                "/api/organismes-bourse/**",
                                "/api/paiements/**",
                                "/api/preinscriptions/**",
                                "/api/responsables-etablissement/**"
                        ).permitAll()
                        // ✅ Ces deux lignes nécessitent le rôle "STUDENT"
                        .requestMatchers(HttpMethod.PUT, "/api/etudiants/update/**").hasRole("STUDENT")
                        .requestMatchers("/api/preinscriptions/formulaire").hasRole("STUDENT")
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
