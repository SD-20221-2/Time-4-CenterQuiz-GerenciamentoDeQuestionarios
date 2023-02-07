/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Filtro onde todas as requisições serão capturadas para autenticar
 * @author mathe
 */
public class JWTApiAutenticacaoFilter extends GenericFilterBean{

    @Override
    public void doFilter(
            ServletRequest sr,
            ServletResponse sr1,
            FilterChain fc
    ) throws IOException, ServletException {
        /**
         * Estabelece a autenticação para a requisição
         */
        Authentication authentication = new JWTTokenAutenticacaoService()
                .getAuthentication((HttpServletRequest) sr);
        
        /**
         * Coloca o processo de autenticação no Spring Security
         */
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        /**
         * Continua o processo
         */
        fc.doFilter(sr, sr1);
    }
    
}
