package mx.tec.web.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Component;

import mx.tec.web.entity.Manga;
import mx.tec.web.entity.Review;
import mx.tec.web.entity.ToRead;
import mx.tec.web.entity.User;
import mx.tec.web.vo.MangaVO;
import mx.tec.web.vo.ReviewVO;
import mx.tec.web.vo.ToReadVO;
import mx.tec.web.vo.UserVO;

/**
 * Mapper for Objects
 */
@Component
public class Mapper {
    /** Reference to the Model Mapper */
    @Resource
    private ModelMapper modelMapper;

    /**
     * Convert from Manga Entity to Manga Value Object
     * @param manga Manga Entity to convert
     * @return  Manga Value Object conversion
     */
    public MangaVO convertMangaToVO(final Manga manga) {
        return modelMapper.map(manga, MangaVO.class);
    }

    /**
     * Convert from Manga Value Object to Manga Entity
     * @param mangaVO Manga Value Object to convert
     * @return Manga Entity conversion
     */
    public Manga convertMangaToEntity(final MangaVO mangaVO) {
        return modelMapper.map(mangaVO, Manga.class);
    }

    /**
     * Convert from Optional Manga Entity to Optional Manga Value Object
     * @param manga Manga Entity Optional to convert
     * @return Optional Manga Value Object conversion
     */
    public Optional<MangaVO> convertMangaToOptionalVO(final Optional<Manga> manga) {
        Optional<MangaVO> mangaVO = Optional.empty();

        if (manga.isPresent()) {
            mangaVO = Optional.of(convertMangaToVO(manga.get()));
        }

        return mangaVO;
    }
    
    /**
     * Convert from Manga Entity List to Manga Value Object List
     * @param mangas Manga Entity List to convert
     * @return Manga Value Object List conversion
     */
    public List<MangaVO> convertMangaToVOList(final List<Manga> mangas) {
        final List<MangaVO> mangaVOs = new ArrayList<>();

        for (final Manga manga : mangas) {
            mangaVOs.add(convertMangaToVO(manga));
        }

        return mangaVOs;
    }

    /**
	 * Convert from User Entity to User Value Object
	 * @param user User Entity to convert
	 * @return User Value Object conversion
	 */
    public UserVO convertUserToVO(final User user) {
        return modelMapper.map(user, UserVO.class);
    }
    
    /**
	 * Convert from User Value Object to User Entity
	 * @param userVO User Value to convert
	 * @return User Entity conversion
	 */
	public User convertUserToEntity(final UserVO userVO) {
		return modelMapper.map(userVO, User.class);
	}

    /**
	 * Convert from Optional User Entity to Optional User Value Object
	 * @param optional User Entity Optional to convert
	 * @return Optional User Value Object conversion
	 */
    public Optional<UserVO> convertUserToOptionalVO(final Optional<User> optional) {
        Optional<UserVO> userVO = Optional.empty();

        if (optional.isPresent()) {
            userVO = Optional.of(convertUserToVO(optional.get()));
        }

        return userVO;
    }
    
    /**
	 * Convert from User Entity List to User Value Object List
	 * @param users User Entity List to convert
	 * @return User Value Object List conversion
	 */
    public List<UserVO> convertUserListToVOList(final List<User> users) {
        final List<UserVO> userVOs = new ArrayList<>();

        for (final User user : users) {
            userVOs.add(convertUserToVO(user));
        }

        return userVOs;
    }
    
    /**
     * Convert from Review Entity to Review Value Object
     * @param review Review Entity to convert
     * @return  Review Value Object conversion
     */
    public ReviewVO convertReviewToVO(final Review review) {
        return modelMapper.map(review, ReviewVO.class);
    }

    /**
     * Convert from Review Value Object to Review Entity
     * @param reviewVO Review Value Object to convert
     * @return Review Entity conversion
     */
    public Review convertReviewToEntity(final ReviewVO reviewVO) {
        return modelMapper.map(reviewVO, Review.class);
    }

    /**
     * Convert from Optional Review Entity to Optional Review Value Object
     * @param review Review Entity Optional to convert
     * @return Optional Review Value Object conversion
     */
    public Optional<ReviewVO> convertReviewToOptionalVO(final Optional<Review> review) {
        Optional<ReviewVO> reviewVO = Optional.empty();

        if (review.isPresent()) {
            reviewVO = Optional.of(convertReviewToVO(review.get()));
        }

        return reviewVO;
    }
    
    /**
     * Convert from Review Entity List to Review Value Object List
     * @param reviews Review Entity List to convert
     * @return Review Value Object List conversion
     */
    public List<ReviewVO> convertReviewToVOList(final List<Review> reviews) {
        final List<ReviewVO> reviewVOs = new ArrayList<>();

        for (final Review review : reviews) {
            reviewVOs.add(convertReviewToVO(review));
        }

        return reviewVOs;
    }

    /**
     * Convert from ToRead Entity to ToRead Value Object
     * @param toRead ToRead Entity to convert
     * @return ToRead Value Object conversion
     */
    public ToReadVO convertToReadToVO(final ToRead toRead) {
        return modelMapper.map(toRead, ToReadVO.class);
    }

    /**
     * Convert from ToRead Value Object to Entity
     * @param toReadVO ToRead Value Object to convert
     * @return ToRead Entity conversion
     */
    public ToRead convertToReadToEntity(final ToReadVO toReadVO) {
        return modelMapper.map(toReadVO, ToRead.class);
    }
    
    /**
     * Convert from Optional ToRead Entity to Optional Review Value Object
     * @param toRead Review ToRead Optional to convert
     * @return Optional ToRead Value Object conversion
     */
    public Optional<ToReadVO> convertToReadToOptionalVO(final Optional<ToRead> toRead) {
        Optional<ToReadVO> toReadVO = Optional.empty();

        if (toRead.isPresent()) {
            toReadVO = Optional.of(convertToReadToVO(toRead.get()));
        }

        return toReadVO;
    }
    
    /**
     * Convert from ToRead Entity List to ToRead Value Object List
     * @param toReads ToRead Entity List to convert
     * @return ToRead Value Object List conversion
     */
    public List<ToReadVO> convertToReadToVOList(final List<ToRead> toReads) {
		final List<ToReadVO> toReadVOs = new ArrayList<>();
		
		for (final ToRead toRead : toReads) {
			toReadVOs.add(convertToReadToVO(toRead));
	    }

		return toReadVOs;
	}
}
