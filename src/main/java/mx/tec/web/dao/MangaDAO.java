package mx.tec.web.dao;

import java.util.List;
import java.util.Optional;

import mx.tec.web.vo.MangaVO;

public interface MangaDAO {
    /** 
     * Find all the Mangas
     * @return List of Mangas or an empty list if no Mangas were found
     */
    List<MangaVO> findAllMangas();

    /** 
     * Find a Manga by its id
     * @param id Manga id
     * @return An Optional containing the found Manga or an empty Optional otherwise
     */
    Optional<MangaVO> findMangaById(long id);

    /** 
     * Persist a new Manga
     * @param newManga Manga to add
     * @return The persisted Manga
     */
    MangaVO insertManga(MangaVO newManga);

    /** 
     * Remove a Manga
     * @param existingManga The Manga to remove
     */
    void removeManga(MangaVO existingManga);

    /**
     * Update an existing Manga
     * @param existingManga The Manga to update
     */
    void updateManga(MangaVO existingManga);
}
