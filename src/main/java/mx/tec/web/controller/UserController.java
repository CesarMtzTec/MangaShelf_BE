package mx.tec.web.controller;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tec.web.manager.SecurityManager;
import mx.tec.web.manager.UserManager;
import mx.tec.web.vo.CredentialsVO;
import mx.tec.web.vo.JsonWebTokenVO;
import mx.tec.web.vo.UserVO;

/**
 * 
 * @author Cesar
 */
@RestController
@RequestMapping("/api")
@Validated
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    
    /** A reference to the User Manager */
    @Resource
    private UserManager userManager;

    /** A reference to the Security Manager */
    @Resource
    private SecurityManager securityManager;

    /**
     * The end point for POST {url}/user
     * @param user a json containing the info for the new User
     * @return If the User is created successfully then status 201 and the User info is returned, otherwise it returns status 400
     */
    @PostMapping("/user")
    public ResponseEntity<UserVO> addUser(@RequestBody UserVO newUser) {
        LOG.info("[UserController]: Adding user {} with email {}", newUser.getUsername(), newUser.getEmail());
        UserVO user = userManager.addUser(newUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * The end point for POST {url}/authenticate
     * @param credentials a json containing the credentials of the user to authenticate
     * @return If the User is authenticated successfully then status 200
     */
    @PostMapping("/authenticate")
    public ResponseEntity<JsonWebTokenVO> createAuthenticationToken(@RequestBody CredentialsVO credentials) {
        LOG.info("[UserController]: Authenticating user {}", credentials.getUsername());
        return ResponseEntity.ok(securityManager.authenticate(credentials));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> onSecurityException(final AuthenticationException ae) {
        LOG.error("Invalid credentials", ae);
        return new ResponseEntity<>(ae.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<String> onEntityExistsException(final EntityExistsException eee) {
        LOG.error("User already exists", eee);
        return new ResponseEntity<>(eee.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
