package com.film_radar.film_radar_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(JWTAuthorizationFilter jwtAuthorizationFilter){
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults()).csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests( authz -> authz
                        .requestMatchers(HttpMethod.POST,"/auth/logIn").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/signIn").permitAll()
                        .requestMatchers(HttpMethod.GET,"/auth/all/movies").permitAll()
                        .requestMatchers(HttpMethod.GET,"/auth/search/title").permitAll()
                        .requestMatchers(HttpMethod.GET,"/auth/search/genre").permitAll()
                        .requestMatchers(HttpMethod.GET,"/auth/search/year").permitAll()
                        .requestMatchers(HttpMethod.POST,"/movie/fetch").permitAll()
                        .requestMatchers(HttpMethod.GET,"/auth/movies/{movieId}/reviews").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/{movieId}/rate").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/{movieId}/addReview").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/{movieId}/favorites").authenticated()
                ).addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Configuration
    public class WebConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(@NonNull CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:5173")  // Frontend URL
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
        }
    }
}
