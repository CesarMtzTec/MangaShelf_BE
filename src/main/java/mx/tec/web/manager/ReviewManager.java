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

    public List<ReviewVO> getReviews() {
        LOG.info("[ReviewManager]: Getting all reviews available");
        return reviewDAO.findAllReviews();
    }

    public Optional<ReviewVO> getReview(final long id) {
        LOG.info("[ReviewManager]: Getting review with id: {}", id);
        return reviewDAO.findReviewById(id);
    }
    
    public List<ReviewVO> getMangaReviews(final long mangaId) {
        LOG.info("[ReviewManager]: Getting all reviews of manga with id: {}", mangaId);
        return reviewDAO.findByMangaId(mangaId);
    }

    public ReviewVO addReview(final ReviewVO newReview) {
		LOG.info("[ReviewManager]: Adding a new review: {}", newReview);
		return reviewDAO.insertReview(newReview);
	}


}
