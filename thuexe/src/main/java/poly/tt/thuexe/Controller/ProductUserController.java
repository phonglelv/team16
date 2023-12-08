package poly.tt.thuexe.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import poly.tt.thuexe.Entity.Product;
import poly.tt.thuexe.Service.ProductService;

@Controller
public class ProductUserController {
@Autowired
    ProductService productService;
    @GetMapping("product/list")
    public String showlogin(Model model,
                            @RequestParam("cid") Optional<String> cid,
                            @RequestParam("p") Optional<Integer> p) {

        // Creating a Pageable object for pagination
        Pageable pageable = PageRequest.of(p.orElse(0), 6);

        Page<Product> productPage;

        if (cid.isPresent()) {
            productPage = productService.findByAllCategoryId(cid.get(), pageable);
        } else {
            productPage = productService.findAll(pageable);
        }

        model.addAttribute("page", productPage);

        return "user/product/list";
    }
    @GetMapping("/product/detail/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Product product = productService.findById(id).orElse(null);
    
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("technicalSpecification", product.getTechnicalSpecifications());
        }
    
        return "user/product/detail";
    }
    

}
