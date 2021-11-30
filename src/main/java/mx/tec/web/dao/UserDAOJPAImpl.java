package mx.tec.web.dao;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mx.tec.web.mapper.Mapper;
import mx.tec.web.entity.User;
import mx.tec.web.repository.UserRepository;
import mx.tec.web.vo.UserVO;

@Component
public class UserDAOJPAImpl implements UserDAO, UserDetailsService {
    private final Logger LOG = LoggerFactory.getLogger(UserDAOJPAImpl.class);

    /** A reference to the user repository */
    @Resource
    private UserRepository userRepository;

    /** A reference to the mapper */
    @Resource
    private Mapper mapper;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        User foundUser = user.get();
        LOG.info("[UserDAO]: Loading user {}", foundUser);

        return new org.springframework.security.core.userdetails.User(foundUser.getUsername(), foundUser.getPassword(),
                grantedAuthorities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserVO> findById(final long id) {
        return mapper.convertUserToOptionalVO(userRepository.findById(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserVO insert(final UserVO user) {
        return mapper.convertUserToVO(userRepository.save(mapper.convertUserToEntity(user)));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserVO> findByUsername(String username) {
        return mapper.convertUserToOptionalVO(userRepository.findByUsername(username));
    }
    
}
