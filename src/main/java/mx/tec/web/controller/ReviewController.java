package mx.tec.web.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.tec.web.manager.ReviewManager;
import mx.tec.web.vo.ReviewVO;

@RestController
@CrossOrigin(origins = "${client.url}")
@RequestMapping("/api")
@Validated
public class ReviewController {
    private static final Logger LOG = LoggerFactory.getLogger(ReviewController.class);

    /** A reference to the Review Manager */
    @Resource
    private ReviewManager reviewManager;

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewVO>> getReviews() {
        LOG.info("[ReviewController]: Getting all the reviews");
        List<ReviewVO> reviews = reviewManager.getReviews();

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    
    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewVO> getReview(
            @PathVariable(value = "id") @Min(value = 0, message = "The id must be positive") long id) {
        LOG.info("[ReviewController]: Getting the review by id: {}", id);
        ResponseEntity<ReviewVO> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        Optional<ReviewVO> review = reviewManager.getReview(id);

        if (review.isPresent()) {
            responseEntity = new ResponseEntity<>(review.get(), HttpStatus.OK);
        } else {
            LOG.warn("[ReviewController]: Review with id {} not found ", id);
        }

        return responseEntity;
    }

    @GetMapping(value="/reviews", params="mangaId")
    public ResponseEntity<List<ReviewVO>> getMangaReviews(
            @RequestParam @PathVariable(value = "mangaId") @Min(value = 0, message = "The mangaId must be positive") long mangaId) {
        LOG.info("Getting the reviews by mangaId: {}", mangaId);
        List<ReviewVO> reviews = reviewManager.getMangaReviews(mangaId);

        return new ResponseEntity<>(reviews, HttpStatus.OK);

    }
    
    @PostMapping("/reviews")
	public ResponseEntity<ReviewVO> addReview(@Valid @RequestBody ReviewVO newReview) {
		LOG.info("add a new review: {}", newReview);
		ReviewVO review = reviewManager.addReview(newReview);		
		return new ResponseEntity<>(review, HttpStatus.CREATED);
	}
    
}
