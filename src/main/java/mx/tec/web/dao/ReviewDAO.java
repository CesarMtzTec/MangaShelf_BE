package mx.tec.web.dao;

import java.util.List;
import java.util.Optional;

import mx.tec.web.vo.ReviewVO;

public interface ReviewDAO {
    /**
     * Find all the Reviews
     * @return List of Reviews or an empty list if no Reviews were found
     */
    List<ReviewVO> findAllReviews();
    
    /** 
     * Find a Review by its id
     * @param id Review id
     * @return An Optional containing the found Review or an empty Optional otherwise
     */
    Optional<ReviewVO> findReviewById(long id);
    
    /**
     * Find all the Reviews for one Manga
     * @param mangaId
     * @return
     */
    List<ReviewVO> findByMangaId(long mangaId);
    
    /** 
     * Persist a new Review
     * @param newReview Review to add
     * @return The persisted Review
     */
    ReviewVO insertReview(ReviewVO newReview);
    
    /** 
     * Remove a Review
     * @param existingReview The Review to remove
     */
    void removeReview(ReviewVO existingReview);
    
    /**
     * Update an existing Review
     * @param existingReview The Review to update
     */
    void updateReview(ReviewVO existingReview);
}
