package mx.tec.web.dao;

import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.tec.web.mapper.Mapper;
import mx.tec.web.repository.UserRepository;
import mx.tec.web.vo.UserVO;

public class UserDAOJPAImpl implements UserDAO {
    private static final Logger log = LoggerFactory.getLogger(UserDAOJPAImpl.class);

    /** A reference to the user repository */
    @Resource
    private UserRepository userRepository;

    /** A reference to the mapper */
    @Resource
    private Mapper mapper;

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
