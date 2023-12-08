package poly.tt.thuexe.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.tt.thuexe.Entity.Product;
import poly.tt.thuexe.Service.ProductService;

@Controller
public class HomeUserController {
     private final ProductService productService;

    @Autowired
    public HomeUserController(ProductService productService) {
        this.productService = productService;
    }

  
    @GetMapping("/home")
    public String list(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 3; // Số sản phẩm trên mỗi trang
        Page<Product> productPage = productService.findAllWithMaxPrice(PageRequest.of(page, pageSize));
        
        List<Product> productList = productPage.getContent(); // Lấy danh sách sản phẩm từ Page
        
        model.addAttribute("products", productList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "user/home/index";
    }
}
