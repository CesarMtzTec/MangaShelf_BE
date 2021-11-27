package mx.tec.web.manager;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional; 
import mx.tec.web.vo.MangaVO; 
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Resource;

import mx.tec.web.dao.MangaDAO;

/**
* @author eddy
*
 */

@Service
public class MangaManager {
    private static final Logger LOG = LoggerFactory.getLogger(MangaManager.class);

    /** Reference to the MangaDao */
    @Resource
    private MangaDAO mangaDao;

    /**
     * Retrieve all the Mangas
     * @return List of Mangas
     */
    public List<MangaVO> getMangas() {
        LOG.debug("[MangaManager]: Getting all mangas available");
        return mangaDao.findAllMangas();
    }

    /**
	 * Retrieve a specific Manga based on a given Manga id
	 * @param id Manga id
	 * @return Optional containing a Manga if the Manga was found or empty otherwise
	 */
    public Optional<MangaVO> getManga(final long id) {
        LOG.debug("[MangaManager]: Getting mangas with id: {}", id);
        return mangaDao.findMangaById(id);
    }

    /**
	 * Add a new Manga to the Manga list based on a given Manga
	 * @param newManga Manga to add
	 * @return An Optional containing the new Manga
	 */
    public MangaVO addManga(final MangaVO newManga) {
        LOG.debug("[MangaManager]: Adding a new manga: {}", newManga);
        return mangaDao.insertManga(newManga);
    }

    /**
	 * Delete the Manga based on a given Manga
	 * @param existingManga The Manga to delete
	 */
    public void deleteManga(final MangaVO existingManga) {
        LOG.debug("[MangaManager]: Deleting an existing manga by manga: {}", existingManga);
        mangaDao.removeManga(existingManga);
    }
    
    /**
	 * Update an existing Manga based on a given modified Manga and a Manga id
	 * @param id The Manga id for the original Manga
	 * @param modifiedManga The Manga new version
	 */
	public void updateManga(final long id, final MangaVO modifiedManga) {
		final Optional<MangaVO> existingManga = getManga(id);
		
		if (existingManga.isPresent()) {
			mangaDao.updateManga(modifiedManga);
		}
	}

}