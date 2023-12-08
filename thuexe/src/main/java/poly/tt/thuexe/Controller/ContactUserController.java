package poly.tt.thuexe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactUserController {
    @GetMapping("/contact/list")
    public String list(){
        return "user/common/contact";
    }
}
