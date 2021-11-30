package mx.tec.web.vo;

import java.io.Serializable;

public class CredentialsVO implements Serializable {
	private static final long serialVersionUID = 8041570321571938096L;
	private String username;
    private String password;
    
    public CredentialsVO() {
    }
    
    public CredentialsVO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
