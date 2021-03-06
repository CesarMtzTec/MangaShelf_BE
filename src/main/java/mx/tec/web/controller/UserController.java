package mx.tec.web.controller;

import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@CrossOrigin(origins = "${client.url}")
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
     * The end point for GET url/user/{id}
     * @param user a json containing the info for the User
     * @return If the User is found successfully then status 200 and the User info is returned, otherwise it returns status 204
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<UserVO> getUser(
            @PathVariable(value = "id") @Min(value = 0, message = "The id must be positive") long id) {
        LOG.info("[UserController]: Getting the user by id: {}", id);
        ResponseEntity<UserVO> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        Optional<UserVO> user = userManager.getUser(id);

        if (user.isPresent()) {
            responseEntity = new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            LOG.warn("[UserController]: User with id {} not found ", id);
        }

        return responseEntity;
    }

    /**
     * The end point for GET {url}/user/{username}
     * @param username the username to get the User with
     * @return a json containing username info and status 200 if the User is found or status 204 if the User is not found
     */
    @GetMapping(value="/user", params="username")
    public ResponseEntity<UserVO> getUserByUsername(@RequestParam @PathVariable(value = "username") String username) {
        LOG.info("[UserController]: Getting the user by username: {}", username);
        ResponseEntity<UserVO> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        Optional<UserVO> user = userManager.getUserByUsername(username);

        if (user.isPresent()) {
            responseEntity = new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            LOG.warn("[UserController]: User with username {} not found", username);
        }

        return responseEntity;
    }
    
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
