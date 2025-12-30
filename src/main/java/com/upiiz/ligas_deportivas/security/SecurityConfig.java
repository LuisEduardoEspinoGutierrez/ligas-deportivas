package com.upiiz.ligas_deportivas.security;

import com.upiiz.ligas_deportivas.services.UsuarioDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final UsuarioDetailsServiceImpl usuarioDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter,
                          UsuarioDetailsServiceImpl usuarioDetailsService) {
        this.jwtFilter = jwtFilter;
        this.usuarioDetailsService = usuarioDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable());

        http.sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.authorizeHttpRequests(auth -> auth

                // -----------------------------------------
                // ENDPOINTS PÚBLICOS (Login y Register)
                // -----------------------------------------
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/").permitAll()

                // -----------------------------------------
                // GET públicos
                // -----------------------------------------
                .requestMatchers(HttpMethod.GET, "/api/jugadores").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/equipos").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/ligas").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/entrenadores").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/partidos").permitAll()


                // -----------------------------------------
                // Swagger público
                // -----------------------------------------
                .requestMatchers("/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/api/auth/**",
                        "/swagger-resources/**",
                        "/webjars/**").permitAll()

                .requestMatchers("/h2-console/**").permitAll()

                // -----------------------------------------
                // POST protegidos por JWT
                // -----------------------------------------
                .requestMatchers(HttpMethod.POST, "/api/jugadores").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/equipos").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/entrenadores").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/ligas").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/partidos").permitAll()
                //------------------------------------------
                //Otros metodos
                .requestMatchers(HttpMethod.PUT, "/api/ligas/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/ligas/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/jugadores/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/jugadores/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/equipos/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/equipos/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/entrenadores/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/entrenadores/**").authenticated()
                //.requestMatchers(HttpMethod.PUT, "/api/partidos/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/partidos/**").authenticated()



                // Cualquier otro request necesita token
                // -----------------------------------------
                .anyRequest().authenticated()
        );

        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}
