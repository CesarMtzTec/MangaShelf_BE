package mx.tec.web.dao;

import java.util.List;
import java.util.Optional;

import mx.tec.web.vo.ToReadVO;

public interface ToReadDAO {
    /**
     * Find ToRead by its id
     * @param id ToRead id
     * @return An Optional containing the found ToRead or an empty Optional otherwise
     */
    Optional<ToReadVO> findToReadById(long id);

    /**
     * Persist a new ToRead
     * @param newToRead ToRead to add
     * @return The persisted ToRead
     */
    ToReadVO insertToRead(ToReadVO newToRead);

    /**
     * Remove a ToRead
     * @param existingToRead The ToRead to remove
     */
    void removeToRead(ToReadVO existingToRead);
    
    /**
     * Find a List of ToReads by userId
     * @param userId The id of the user to get ToReads
     * @return A List of ToReads
     */
	List<ToReadVO> getToReadList(long userId);
}
