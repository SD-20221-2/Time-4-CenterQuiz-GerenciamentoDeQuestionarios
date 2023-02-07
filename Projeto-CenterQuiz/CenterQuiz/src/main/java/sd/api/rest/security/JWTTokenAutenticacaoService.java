/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sd.api.rest.ApplicationContextLoad;
import sd.api.rest.model.Usuario;
import sd.api.rest.repository.UsuarioRepository;

/**
 *
 * @author mathe
 */
@Service
@Component
public class JWTTokenAutenticacaoService {

    /**
     * Tempo de validade do token = 2 dias em ms
     */
    private static final long EXPIRATION_TIME = 172800000;

    /**
     * Senha única para compor a autenticação e ajudar na segurança
     */
    private static final String SECRET = "SenhaTeste";

    /**
     * Prefixo padrão de token
     */
    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";

    /**
     * Gerando token de autenticação e adicionando ao cabeçalho e resposta HTTP
     */
    public void addAuthentication(
            HttpServletResponse response,
            String username
    ) throws IOException {
        /**
         * Montagem do token
         */
        String JWT = Jwts.builder() //chama do gerador de token
                .setSubject(username) // Adiciona o usuário
                .setExpiration( // tempo de expiração
                        new Date(System.currentTimeMillis() + EXPIRATION_TIME)
                )
                .signWith(SignatureAlgorithm.HS512, SECRET) // Algoritmo de geração de senha
                .compact(); // compactação

        /**
         * Junta o token com o prefixo
         */
        String token = TOKEN_PREFIX + " " + JWT;

        /**
         * Adiciona no cabeçalho HTTP
         */
        response.addHeader(HEADER_STRING, token);

        /**
         * Escreve token como responsta no corpo do HTTP
         */
        response.getWriter().write(
                "{\"Authorization\": \"" + token + "\"}"
        );

    }

    /**
     * Retorna o usuário validado com token ou caso não seja válido, retorna
     * null
     */
    public Authentication getAuthentication(HttpServletRequest request) {
        /**
         * Pega o token enviado no cabeçalho HTTP
         */
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            /**
             * Faz a validação do token do usuário na requisição
             */
            String user = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody().getSubject(); // Nome do usuário

            if (user != null) {
                Usuario usuario = ApplicationContextLoad.getApplicationContext()
                        .getBean(UsuarioRepository.class)
                        .findUserByLogin(user);

                /**
                 * Retornar o usuário logado
                 */
                if (usuario != null) {
                    return new UsernamePasswordAuthenticationToken(
                            usuario.getEmail(),
                            usuario.getSenha(),
                            usuario.getAuthorities()
                    );
                }
            }

        }

        return null;
    }
}
