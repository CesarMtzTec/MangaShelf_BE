/**
 * 
 */
package mx.tec.web.vo;

import java.util.Calendar;

/**
 * @author Cesar
 *
 */
public class ToReadVO {

	private long id;
    private long userId; 
    private long mangaId;
    private Calendar dateAdded;   
    private String mangaTitle;
    private String mangaAuthor;
	/**
	 * 
	 */
	public ToReadVO() {
	}

	

	/**
	 * @param id
	 * @param userId
	 * @param mangaId
	 * @param dateAdded
	 * @param mangaTitle
	 * @param mangaAuthor
	 */
	public ToReadVO(long id, long userId, long mangaId, Calendar dateAdded, String mangaTitle, String mangaAuthor) {
		this.id = id;
		this.userId = userId;
		this.mangaId = mangaId;
		this.dateAdded = dateAdded;
		this.mangaTitle = mangaTitle;
		this.mangaAuthor = mangaAuthor;
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
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the mangaId
	 */
	public long getMangaId() {
		return mangaId;
	}

	/**
	 * @param mangaId the mangaId to set
	 */
	public void setMangaId(long mangaId) {
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

	public String getMangaAuthor() {
		return mangaAuthor;
	}

	public void setMangaAuthor(String mangaAuthor) {
		this.mangaAuthor = mangaAuthor;
	}

	public String getMangaTitle() {
		return mangaTitle;
	}

	public void setMangaTitle(String mangaTitle) {
		this.mangaTitle = mangaTitle;
	}

}
