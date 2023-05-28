package com.digitalhouse.clinicaodontologicag6.security;

import com.digitalhouse.clinicaodontologicag6.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/paciente/cadastrar").hasAnyRole("ADMIN").antMatchers(HttpMethod.POST, "/paciente/auth").permitAll().antMatchers(HttpMethod.GET, "/paciente/buscar").hasAnyRole("ADMIN").antMatchers(HttpMethod.GET, "/paciente/listar").hasAnyRole("ADMIN").antMatchers(HttpMethod.PUT, "/paciente/atualizar").hasAnyRole("ADMIN", "USER").antMatchers(HttpMethod.DELETE, "/paciente/excluir").hasAnyRole("ADMIN").antMatchers(HttpMethod.POST, "/dentista/cadastrar").permitAll().antMatchers(HttpMethod.POST, "/dentista/auth").permitAll().antMatchers(HttpMethod.GET, "/dentista/buscar").hasAnyRole("ADMIN").antMatchers(HttpMethod.GET, "/dentista/listar").hasAnyRole("ADMIN").antMatchers(HttpMethod.PUT, "/dentista/atualizar").hasAnyRole("ADMIN").antMatchers(HttpMethod.DELETE, "/dentista/excluir").hasAnyRole("ADMIN").antMatchers(HttpMethod.POST, "/consulta/cadastrar").hasAnyRole("ADMIN", "USER").antMatchers(HttpMethod.GET, "/consulta/buscar").hasAnyRole("ADMIN").antMatchers(HttpMethod.GET, "/consulta/listar").hasAnyRole("ADMIN").antMatchers(HttpMethod.PUT, "/consulta/atualizar").hasAnyRole("ADMIN").antMatchers(HttpMethod.DELETE, "/consulta/excluir").hasAnyRole("ADMIN").anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}