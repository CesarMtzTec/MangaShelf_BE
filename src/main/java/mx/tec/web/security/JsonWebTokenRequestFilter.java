package mx.tec.web.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import mx.tec.web.manager.SecurityManager;
import mx.tec.web.util.JsonWebTokenUtil;

@Component
public class JsonWebTokenRequestFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(JsonWebTokenRequestFilter.class);
    
    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private JsonWebTokenUtil tokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (null != requestTokenHeader && requestTokenHeader.startsWith("Bearer ")) {
            token = requestTokenHeader.substring(7);

            try {
                username = tokenUtil.getUsernameFromToken(token);
            } catch (IllegalArgumentException iae) {
                LOG.error("Unable to get JWT Token", iae);
            } catch (ExpiredJwtException eje) {
                LOG.error("JWT Token has expired", eje);
            }
        } else {
            LOG.warn("JWT Token does not begin with Bearer String");
        }

        if (null != username && null == SecurityContextHolder.getContext().getAuthentication()) {
            Optional<UsernamePasswordAuthenticationToken> authenticationToken = securityManager.authenticate(username,
                    token);

            if (authenticationToken.isPresent()) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = authenticationToken.get();
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                LOG.info("User authenticated with token");

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        
        chain.doFilter(request, response);
    }
    
}
