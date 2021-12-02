package mx.tec.web.manager;

import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import mx.tec.web.util.JsonWebTokenUtil;
import mx.tec.web.vo.CredentialsVO;
import mx.tec.web.vo.JsonWebTokenVO;

@Service
public class SecurityManager {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityManager.class);

    /** Reference to the UserDetailsService */
    @Resource
    UserDetailsService userDAO;

    /** Reference to the AuthenticationManager */
    @Resource
    AuthenticationManager authenticationManager;

    /** Reference to the JsonWebTokenUtil */
    @Resource
    JsonWebTokenUtil jwtTokenUtil;

    /**
     * Authenticate a user by given credentials
     * @param credentials Credentials to authenticate the user with
     * @return JsonWebToken
     * @throws AuthenticationException
     */
    public JsonWebTokenVO authenticate(CredentialsVO credentials) throws AuthenticationException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
        final UserDetails userDetails = userDAO.loadUserByUsername(credentials.getUsername());
        return new JsonWebTokenVO(jwtTokenUtil.generateToken(userDetails));
    }

    /**
     * Authenticate a user with a token
     * @param username 
     * @param token
     * @return Optional containing authenticationToken
     */
    public Optional<UsernamePasswordAuthenticationToken> authenticate(final String username, final String token) {
        Optional<UsernamePasswordAuthenticationToken> authenticationToken = Optional.empty();
        UserDetails userDetails = userDAO.loadUserByUsername(username);

        LOG.info("Authenticating user {} with token", username);

        if (jwtTokenUtil.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authenticationToken = Optional.of(usernamePasswordAuthenticationToken);
            LOG.info("Token for user {} is valid with details {}", username, userDetails);
        }

        return authenticationToken;
    }
}
