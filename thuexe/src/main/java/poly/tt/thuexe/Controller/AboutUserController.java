package poly.tt.thuexe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUserController {
    
    @GetMapping("about")
    public String list (){
        return "user/common/about";
    }
}
