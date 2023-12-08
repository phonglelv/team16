package poly.tt.thuexe.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;




@Service
public class SessionService {
    @Autowired
    HttpSession session;
    public <T> T get(String name, Object object) {
        return (T) session.getAttribute(name);
    }
    public void set(String name, Object object) {
        session.setAttribute(name, object);
    }

    public void remove(String name) {
        session.removeAttribute(name);
    }
}
