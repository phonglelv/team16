package poly.tt.thuexe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopCartController {

    @GetMapping("cart/view")
    public String cart(){
        return "user/cart/view";
    }
}
