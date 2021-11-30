package mx.tec.web.util;

import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class SecurityHelper {
    public String hash(String clearText) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(clearText);
    }
}
