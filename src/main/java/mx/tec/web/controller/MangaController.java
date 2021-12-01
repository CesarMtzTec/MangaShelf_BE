/**
 * 
 */
package mx.tec.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tec.web.vo.MangaVO;
import mx.tec.web.manager.MangaManager;

/**
 * @author Cesar
 *
 */
@RestController
@RequestMapping("/api")
@Validated
public class MangaController {
	private static final String MANGA_WITH_ID_NOT_FOUND = "[MangaController]: Manga with id {} not found";
	private static final Logger LOG = LoggerFactory.getLogger(MangaController.class);

	/** A reference to the Manga Manager */
	@Resource
	private MangaManager mangaManager;

	/**
	 * The end point for GET {url}/mangas
	 * @return a json list of all the mangas
	 */
	@GetMapping("/mangas")
	public ResponseEntity<List<MangaVO>> getMangas() {
		LOG.debug("[MangaController]: Getting all the mangas");
		List<MangaVO> mangas = mangaManager.getMangas();

		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	/**
	 * The end point for GET {url}/mangas/{id}
	 * @param id Manga id
	 * @return a json containing the manga info and status 200 if the manga is found or status 204 if the manga is not found
	 */
	@GetMapping("/mangas/{id}")
	public ResponseEntity<MangaVO> getManga(
			@PathVariable(value = "id") @Min(value = 0, message = "The id must be positive") long id) {
		LOG.debug("[MangaController]: Getting the manga with id {}", id);
		ResponseEntity<MangaVO> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);

		Optional<MangaVO> manga = mangaManager.getManga(id);

		if (manga.isPresent()) {
			responseEntity = new ResponseEntity<>(manga.get(), HttpStatus.OK);
		} else {
			LOG.warn(MANGA_WITH_ID_NOT_FOUND, id);
		}

		return responseEntity;
	}

	/**
	 * The end point for POST {url}/mangas/
	 * @param newProduct a json containing the info for the new Manga
	 * @return If the Manga is created succesfully then status 201 and the Manga info is returned, otherwise it returns status 400
	 */
	@PostMapping("/mangas")
	public ResponseEntity<MangaVO> addManga(@Valid @RequestBody MangaVO newManga) {
		LOG.debug("[MangaController]: Creating manga {}", newManga);
		MangaVO manga = mangaManager.addManga(newManga);
		return new ResponseEntity<>(manga, HttpStatus.CREATED);
	}

	/**
	 * The end point for PUT {url}/mangas/{id}
	 * @param id Manga id
	 * @param modifiedProduct a json containing the info for the modified Manga
	 * @return status 200 if the Manga is found and updated or status 204 if the Manga is not found
	 */
	@PutMapping("/mangas/{id}")
	public ResponseEntity<MangaVO> updateManga(@PathVariable(value = "id") long id,
			@RequestBody MangaVO modifiedManga) {
		LOG.debug("[MangaController]: Updating the manga with id {}", id);
		ResponseEntity<MangaVO> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		Optional<MangaVO> manga = mangaManager.getManga(id);

		if (manga.isPresent()) {
			mangaManager.updateManga(id, modifiedManga);
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		} else {
			LOG.warn(MANGA_WITH_ID_NOT_FOUND, id);
		}

		return responseEntity;
	}

	/**
	 * The end point for DELETE {url}/mangas/{id}
	 * @param id Manga id
	 * @return status 200 if the Manga is found and deleted or status 204 if the Manga is not found
	 */
	@DeleteMapping("/mangas/{id}")
	public ResponseEntity<MangaVO> deleteManga(@PathVariable(value = "id") long id) {
		LOG.debug("[MangaController]: Deleting the manga with id {}", id);
		ResponseEntity<MangaVO> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		Optional<MangaVO> manga = mangaManager.getManga(id);

		if (manga.isPresent()) {
			mangaManager.deleteManga(manga.get());
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		} else {
			LOG.warn(MANGA_WITH_ID_NOT_FOUND, id);
		}

		return responseEntity;
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> onConstraintViolationException(final ConstraintViolationException cve) {
    	LOG.error("[MangaController]: Invalid parameter", cve);
        return new ResponseEntity<>(cve.getMessage(), HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> onMethodArgumentNotValidException(final MethodArgumentNotValidException manve) {
		LOG.error("Invalid input", manve);

		List<String> messages = new ArrayList<>();

		List<ObjectError> errors = manve.getAllErrors();
		for (ObjectError error : errors) {
			LOG.debug(error.getDefaultMessage());
			messages.add(error.getDefaultMessage());
		}

		return new ResponseEntity<>(messages.toString(), HttpStatus.BAD_REQUEST);
	}
}
