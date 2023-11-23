package poly.tt.thuexe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeUserController {
    @GetMapping("/home")
    public String list(){
        return "user/home/index";
    }
}
