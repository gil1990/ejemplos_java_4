package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;



@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider (UserDetailsService uds){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); 
        provider.setUserDetailsService(uds);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    

    //Configurando rutas de acceso 
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests (auth ->  auth
                .requestMatchers("/registro","/css/**", "/js/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/h2-console/**").permitAll() //Acceso a la BD H2
                .requestMatchers("/login","/resources/**").permitAll()
                .anyRequest().authenticated() // El resto necesita autenticacion
                )
            .formLogin(form -> form
                .loginPage("/login") //Se define pagina de Login
                .defaultSuccessUrl("/default",true) //Ruta por defecto, si la autenticacion fue exitosa
                .permitAll()
            )
            .logout(logout -> logout.permitAll());

        
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();

    }
}