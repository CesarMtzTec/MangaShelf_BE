/**
 * 
 */
package mx.tec.web.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;

/**
 * @author Scyruz
 *
 */
@Entity
public class ToRead implements Serializable {
	private static final long serialVersionUID = -6414894827465868023L;

	/** ToRead id */
	@Id
	@GeneratedValue
	private long id;    
    
    /** ToRead userId */
    private String userId;

    /** ToRead mangaId */ 
    private String mangaId;

    /** ToRead dateAdded */
    private Calendar dateAdded;   
    
	/**
	 * No arguments constructor
	 */
	public ToRead() {
	}

	/**
     * Constructor including all the properties
	 * @param id
	 * @param userId
	 * @param mangaId
	 * @param dateAdded
	 */
	public ToRead(final long id, String userId, String mangaId, Calendar dateAdded) {
		this.id = id;
		this.userId = userId;
		this.mangaId = mangaId;
		this.dateAdded = dateAdded;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the mangaId
	 */
	public String getMangaId() {
		return mangaId;
	}

	/**
	 * @param mangaId the mangaId to set
	 */
	public void setMangaId(String mangaId) {
		this.mangaId = mangaId;
	}

	/**
	 * @return the dateAdded
	 */
	public Calendar getDateAdded() {
		return dateAdded;
	}

	/**
	 * @param dateAdded the dateAdded to set
	 */
	public void setDateAdded(Calendar dateAdded) {
		this.dateAdded = dateAdded;
	}

}
