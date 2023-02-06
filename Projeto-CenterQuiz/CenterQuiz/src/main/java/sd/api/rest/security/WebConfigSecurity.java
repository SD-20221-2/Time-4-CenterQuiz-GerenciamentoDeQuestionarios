/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
import sd.api.rest.service.ImplementacaoUserDetailsService;

/**
 * Mapear URLs, endereços; Autorizar ou bloquear acesso a URLs.
 *
 * @author mathe
 */
@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementacaoUserDetailsService implementacaoUserDetailsService;

    /**
     * Configura as solicitações de acesso por HTTP
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Ativando a proteção contra usuários que não estão validados por token
        http.csrf().csrfTokenRepository(
                CookieCsrfTokenRepository.withHttpOnlyFalse()
        )
                // Ativando a permissão para acessoa a página inicial. Ex: sistema.com.br/index.html
                .disable().authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/index").permitAll()
                // URL de Logout - Redireciona após o user deslogar do sistema
                .anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
                // Mapeia URL de Logout e invalida o usuário
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                
                //Filtra requisições de login para autenticação
                
                //Filtra demais requisições para verificar a presença do TOKEN JWT no HEADER HTTP
            
    }

    /**
     * Service que irá consultar o usuário no banco de dados.
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(implementacaoUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder()); // Padrão de codificação de senha.
    }

}
