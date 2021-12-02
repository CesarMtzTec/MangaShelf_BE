package mx.tec.web.manager;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mx.tec.web.dao.ReviewDAO;
import mx.tec.web.vo.ReviewVO;

@Service
public class ReviewManager {
    private static final Logger LOG = LoggerFactory.getLogger(ReviewManager.class);

    /** Reference to the ReviewDAO */
    @Resource
    private ReviewDAO reviewDAO;

    /**
     * Retrieve all the Reviews
     * @return List of Reviews
     */
    public List<ReviewVO> getReviews() {
        LOG.info("[ReviewManager]: Getting all reviews available");
        return reviewDAO.findAllReviews();
    }

    /**
     * Retrieve a specific Review based on a given Review id
     * @param id Review id
     * @return Optional containing a Review if the Review was found or empty otherwise
     */
    public Optional<ReviewVO> getReview(final long id) {
        LOG.info("[ReviewManager]: Getting review with id: {}", id);
        return reviewDAO.findReviewById(id);
    }
    
    /**
     * Retrieve all the Reviews for a single Manga
     * @param mangaId Manga id
     * @return List of Reviews
     */
    public List<ReviewVO> getMangaReviews(final long mangaId) {
        LOG.info("[ReviewManager]: Getting all reviews of manga with id: {}", mangaId);
        return reviewDAO.findByMangaId(mangaId);
    }

    /**
     * Add a new Review to the Review list based on a given Review
     * @param newReview
     * @return
     */
    public ReviewVO addReview(final ReviewVO newReview) {
		LOG.info("[ReviewManager]: Adding a new review: {}", newReview);
		return reviewDAO.insertReview(newReview);
	}


}
