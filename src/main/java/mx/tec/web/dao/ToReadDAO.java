package mx.tec.web.dao;

import java.util.List;
import java.util.Optional;

import mx.tec.web.vo.ToReadVO;

public interface ToReadDAO {
    Optional<ToReadVO> findToReadById(long id);
    ToReadVO insertToRead(ToReadVO newToRead);
	void removeToRead(ToReadVO existingToRead);
	List<ToReadVO> getToReadList(long userId);
}
