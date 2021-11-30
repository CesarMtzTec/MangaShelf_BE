/**
 * 
 */
package mx.tec.web.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Scyruz
 *
 */
@Entity
public class User {

	/** User id */
	@Id
	@GeneratedValue
	private long id;

	/** User username */
	private String username;

    /** User password */
	private String password;

    /** User email */
    private String email;
    
	/**
	 * No arguments constructor
	 */
	public User() {
	}

	/**
    * Constructor including all the properties
	 * @param id
	 * @param username
	 * @param password
	 * @param email
	 */
	public User(final long id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
