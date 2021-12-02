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

    /** Reference to the ToReadDao */
    @Resource
    private ToReadDAO toReadDAO;

    /**
     * Retrieve a specific ToRead based on a given ToRead id
     * @param id ToRead id
     * @return Optional containing a ToRead if the ToRead was found or empty otherwise
     */
    public Optional<ToReadVO> getToRead(final long id) {
        LOG.info("[ToReadListManager]: Getting toRead with id: {}", id);
        return toReadDAO.findToReadById(id);
    }
    
    /**
     * Retrieve all the ToReads for a user
     * @param userId User id
     * @return List of ToReads
     */
    public List<ToReadVO> getToReadList(final long userId) {
        LOG.info("[ToReadListManager]: Getting to read list of user with id: {}", userId);
        return toReadDAO.getToReadList(userId);
    }

    /**
     * Add a new ToRead to the ToRead list based on a given ToRead
     * @param newToRead ToRead to add
     * @return An Optional containing the new ToRead
     */
    public ToReadVO addToRead(final ToReadVO newToRead) {
        LOG.info("[ToReadListManager]: Adding a new to read: {}", newToRead);
        return toReadDAO.insertToRead(newToRead);
    }
    
    /**
     * Delete the ToRead based on a given ToRead
     * @param existingToRead The ToRead to delete
     */
    public void deleteFromToReadList(final ToReadVO existingToRead) {
		LOG.info("[ToReadListManager]: Deleting an existing to read from list: {}", existingToRead);
		toReadDAO.removeToRead(existingToRead);
	}
    
}
