/**
 * 
 */
package mx.tec.web.vo;

import java.util.Calendar;

/**
 * @author Scyruz
 *
 */
public class ReviewVO {

	private long id;
	private long userId;
	private long mangaId;
	private String description;
    private Float rate;
    private Calendar date;
    private String username;
    private String mangaTitle;
    
	/**
	 * 
	 */
	public ReviewVO() {
	}

	/**
	 * @param id
	 * @param userId
	 * @param mangaId
	 * @param description
	 * @param rate
	 * @param date
	 * @param username
	 * @param mangaTitle
	 */
	public ReviewVO(long id, long userId, long mangaId, String description, Float rate, Calendar date, String username, String mangaTitle) {
		this.id = id;
		this.userId = userId;
		this.mangaId = mangaId;
		this.description = description;
		this.rate = rate;
		this.date = date;
		this.username = username;
		this.mangaTitle = mangaTitle;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the rate
	 */
	public Float getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(Float rate) {
		this.rate = rate;
	}

	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the mangaTitle
	 */
	public String getMangaTitle() {
		return mangaTitle;
	}

	/**
	 * @param mangaTitle the mangaTitle to set
	 */
	public void setMangaTitle(String mangaTitle) {
		this.mangaTitle = mangaTitle;
	}

}
