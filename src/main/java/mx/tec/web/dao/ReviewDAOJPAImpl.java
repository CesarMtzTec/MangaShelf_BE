package mx.tec.web.dao;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import mx.tec.web.mapper.Mapper;
import mx.tec.web.repository.ReviewRepository;
import mx.tec.web.vo.ReviewVO;

@Component
public class ReviewDAOJPAImpl implements ReviewDAO {

    /** A reference to the Repository */
    @Resource
    private ReviewRepository reviewRepository;

    /** A reference to the Product Mapper */
    @Resource
    private Mapper mapper;

    /** {@inheritDoc} */
    public List<ReviewVO> findAllReviews() {
        return mapper.convertReviewToVOList(reviewRepository.findAll());
    }
    
    /** {@inheritDoc} */
    @Override
    public Optional<ReviewVO> findReviewById(long id) {
        return mapper.convertReviewToOptionalVO(reviewRepository.findById(id));
    }
    
    /** {@inheritDoc} */
    @Override
    public ReviewVO insertReview(ReviewVO newReview) {
        return mapper.convertReviewToVO(reviewRepository.save(mapper.convertReviewToEntity(newReview)));
    }
    
    /** {@inheritDoc} */
    @Override
    public void removeReview(ReviewVO existingReview) {
    	reviewRepository.delete(mapper.convertReviewToEntity(existingReview));
    }
    
    /** {@inheritDoc} */
    @Override
    public void updateReview(ReviewVO existingReview) {
        reviewRepository.save(mapper.convertReviewToEntity(existingReview));
    }
    
    /** {@inheritDoc} */
    @Override
	public List<ReviewVO> findByMangaId(long mangaId) {
		return mapper.convertReviewToVOList(reviewRepository.findByMangaId(mangaId));
	}
    
}
