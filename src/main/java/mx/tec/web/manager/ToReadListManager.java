package mx.tec.web.manager;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mx.tec.web.dao.ToReadDAO;
import mx.tec.web.vo.ToReadVO;

@Service
public class ToReadListManager {
    private static final Logger LOG = LoggerFactory.getLogger(ToReadListManager.class);

    @Resource
    private ToReadDAO toReadDAO;

    public Optional<ToReadVO> getToRead(final long id) {
        LOG.info("[ToReadListManager]: Getting toRead with id: {}", id);
        return toReadDAO.findToReadById(id);
    }
     
    public List<ToReadVO> getToReadList(final long userId) {
        LOG.info("[ToReadListManager]: Getting to read list of user with id: {}", userId);
        return toReadDAO.getToReadList(userId);
    }

    public ToReadVO addToRead(final ToReadVO newToRead) {
        LOG.info("[ToReadListManager]: Adding a new to read: {}", newToRead);
        return toReadDAO.insertToRead(newToRead);
    }
    
    public void deleteFromToReadList(final ToReadVO existingToRead) {
		LOG.info("[ToReadListManager]: Deleting an existing to read from list: {}", existingToRead);
		toReadDAO.removeToRead(existingToRead);
	}
    
}
