/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import sd.api.rest.model.Usuario;

/**
 * Estabelece o gerenciador de token
 *
 * @author mathe
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * Configura o gerenciador de autenticação
     *
     * @param url
     * @param am
     */
    protected JWTLoginFilter(String url, AuthenticationManager am) {
        /**
         * Obriga autenticar a URL
         */
        super(new AntPathRequestMatcher(url));

        /**
         * Gerenciador de autenticação
         */
        setAuthenticationManager(am);
    }

    /**
     * Retorna o usuário ao processar a autenticação
     *
     * @param hsr
     * @param hsr1
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest hsr,
            HttpServletResponse hsr1
    ) throws AuthenticationException, IOException, ServletException {
        /**
         * Está pegando o token para validar
         */
        Usuario usuario = new ObjectMapper().readValue(
                hsr.getInputStream(),
                Usuario.class
        );

        /**
         * Retorna o usuário login, senha e acessos
         */
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuario.getEmail(),
                        usuario.getSenha()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {
        new JWTTokenAutenticacaoService().addAuthentication(
                response,
                authResult.getName()
        );
    }

}
