package mx.tec.web.dao;

import java.util.Optional;

import mx.tec.web.vo.UserVO;

public interface UserDAO {
    Optional<UserVO> findById(long id);
    Optional<UserVO> findByUsername(String username);
    public UserVO insert(final UserVO user);
}
