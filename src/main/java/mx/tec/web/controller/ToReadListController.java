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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.tec.web.manager.ToReadListManager;
import mx.tec.web.vo.ToReadVO;

@RestController
@RequestMapping("/api")
@Validated
public class ToReadListController {
    private static final Logger LOG = LoggerFactory.getLogger(ToReadListController.class);

    /** A reference to the ToReadList Manager */
    @Resource
    private ToReadListManager toReadListManager;

    /**
     * The endpoint for GET {url}/to-red-list/{id}
     * @param id ToReadList id
     * @return a json containing the ToReadList info and status 200 if the List is found or status 204 if the List is not found
     */
    @GetMapping("/to-read-list/{id}")
    public ResponseEntity<ToReadVO> getToRead(
            @PathVariable(value = "id") @Min(value = 0, message = "The id must be positive") long id) {
        LOG.info("[ToReadListController]: Getting the to read by id: {}", id);
        ResponseEntity<ToReadVO> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        Optional<ToReadVO> toRead = toReadListManager.getToRead(id);

        if (toRead.isPresent()) {
            responseEntity = new ResponseEntity<>(toRead.get(), HttpStatus.OK);
        } else {
            LOG.warn("To read with id {} not found ", id);
        }

        return responseEntity;
    }
    
    /**
     * The endpoint for GET {url}/to-read-list?userId{userId}
     * @param userId id of the user to get the ToReadList for
     * @return a json containing the ToReadList info and status 200 if the List is found or status 204 if the List is not found
     */
    @GetMapping(value="/to-read-list", params="userId")
    public ResponseEntity<List<ToReadVO>> getToReadList(
            @RequestParam @PathVariable(value = "userId") @Min(value = 0, message = "The userId must be positive") long userId) {
        LOG.info("[ToReadListController]: Getting the TO READ LIST by userId: {}", userId);
        List<ToReadVO> toReadList = toReadListManager.getToReadList(userId);

        return new ResponseEntity<>(toReadList, HttpStatus.OK);

    }
    
    /**
     * The endpoint for POST {url}/to-read-list
     * @param newToRead a json containing the info for the new ToRead
     * @return If the ToRead is created successfully then status 201 and the ToRead info, otherwise it returns status 400
     */
    @PostMapping("/to-read-list")
    public ResponseEntity<ToReadVO> addToRead(@Valid @RequestBody ToReadVO newToRead) {
        LOG.info("[ToReadListController]: Adding a new toRead: {}", newToRead);
        ToReadVO toRead = toReadListManager.addToRead(newToRead);
        return new ResponseEntity<>(toRead, HttpStatus.CREATED);
    }
	
    /**
     * The endpoint for DELETE {url}/to-read-list/{id}
     * @param id ToRead id
     * @return status 200 if the ToRead is found and deleted or status 204 if the ToRead is not found
     */
	@DeleteMapping("/to-read-list/{id}")
	public ResponseEntity<ToReadVO> deleteFromToReadList(@PathVariable(value = "id") long id) {
		LOG.info("[ToReadListController]: Deleting from to read list by id: {}", id);
		ResponseEntity<ToReadVO> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		Optional<ToReadVO> toRead = toReadListManager.getToRead(id);
		
		if (toRead.isPresent()) {
			toReadListManager.deleteFromToReadList(toRead.get());
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		}
		
		return responseEntity;
	}
    
}
