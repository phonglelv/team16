package poly.tt.thuexe.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import poly.tt.thuexe.Entity.Customer;
import poly.tt.thuexe.Entity.Product;
import poly.tt.thuexe.Service.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductUserController {
@Autowired
    ProductService productService;
    @GetMapping("product/list")
    public String showlogin(Model model,
                            @RequestParam("cid") Optional<String> cid,
                            @RequestParam("p") Optional<Integer> p) {

        // Creating a Pageable object for pagination
        Pageable pageable = PageRequest.of(p.orElse(0), 5);

        Page<Product> productPage;

        if (cid.isPresent()) {
            productPage = productService.findByAllCategoryId(cid.get(), pageable);
        } else {
            productPage = productService.findAll(pageable);
        }

        model.addAttribute("page", productPage);

        return "user/product/list";
    }

}
