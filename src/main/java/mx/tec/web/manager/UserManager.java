package mx.tec.web.manager;

import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import mx.tec.web.dao.UserDAO;
import mx.tec.web.util.SecurityHelper;
import mx.tec.web.vo.UserVO;

@Service
public class UserManager {
    @Resource
    public UserDAO userDAO;

    @Resource
    private SecurityHelper securityHelper;

    public Optional<UserVO> getUser(final long id) {
        return userDAO.findById(id);
    }

    public Optional<UserVO> getUserByUsername(final String username) {
        return userDAO.findByUsername(username);
    }

    public UserVO addUser(final UserVO user) {
        Optional<UserVO> foundUser = userDAO.findByUsername(user.getUsername());

        if (foundUser.isPresent()) {
            throw new EntityExistsException("User already exists " + user.getUsername());
        } else {
            user.setPassword(securityHelper.hash(user.getPassword()));
            return userDAO.insert(user);
        }
    }
    
}
