package mx.tec.web.vo;

import java.io.Serializable;

public class JsonWebTokenVO implements Serializable {
	private static final long serialVersionUID = 8861427633471796915L;
	private final String token;

    public JsonWebTokenVO(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
    
}
