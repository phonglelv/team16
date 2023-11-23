package poly.tt.thuexe.AdminController;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import poly.tt.thuexe.Entity.*;
import poly.tt.thuexe.Model.OrderModel;
import poly.tt.thuexe.Model.ProductModel;
import poly.tt.thuexe.Service.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/bikes")
public class BikeAdminController {
    @Autowired
    ProductService productService;
    @Autowired
    CategotyServiceImpl categotyService;

    @Autowired
    SessionService session;
    @GetMapping("add")
    public String add(ModelMap modelMap){
        ProductModel dto = new ProductModel();
        List<Category> list = categotyService.findAll();
        modelMap.addAttribute("categories", list);
        dto.setIsEdit(false);
        modelMap.addAttribute("bike", dto);
        modelMap.addAttribute("bike",new ProductModel());
        return "admin/bike/addBike";
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(ModelMap modelMap, @PathVariable("id") Integer id){
        Optional<Product> customer = productService.findById(id);
        ProductModel customerModel = new ProductModel();
        if (customer.isPresent()){
            Product product = customer.get();
            List<Category> list = categotyService.findAll();
            modelMap.addAttribute("categories", list);

            BeanUtils.copyProperties(product, customerModel);
            customerModel.setCategory(product.getCategory().getId());

            customerModel.setIsEdit(true);
            List<Category> lists = categotyService.findAll();
            modelMap.addAttribute("categories", lists);

            modelMap.addAttribute("bike",customerModel);
            return new ModelAndView("admin/bike/addBike",modelMap);

        }
        modelMap.addAttribute("message", "Không tìm thấy");
        return  new ModelAndView("forward:/admin/orders",modelMap);

    }

    @PostMapping("/saveOrUpdate")
    public ModelAndView save(ModelMap modelMap, @ModelAttribute("order") ProductModel productModel){
        List<Category> list = categotyService.findAll();
        modelMap.addAttribute("categories", list);

        Product entity = new Product();
        Category customer = new Category();
        BeanUtils.copyProperties(productModel, entity);
        customer.setId(productModel.getId());
        entity.setCategory(customer);

        productService.save(entity);
        modelMap.addAttribute("message", "Thành công");


        return new ModelAndView("forward:/admin/bikes");

    }
    @RequestMapping("/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw,
                                @RequestParam("p") Optional<Integer> p) {
        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = productService.findAllByNameLike("%"+kwords+"%", pageable);
        model.addAttribute("page",page);
        return "admin/bike/list";

    }
    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id , ModelMap modelMap){
        productService.deleteById(id);

        modelMap.addAttribute("message" ,"Xóa thành công");
        return new ModelAndView("forward:/admin/bikes",modelMap);
    }
 @RequestMapping("")
    public String list(ModelMap modelMap, @RequestParam("p") @ModelAttribute("bike") Optional<Integer> p){
     Pageable pageable = PageRequest.of(p.orElse(0), 5);
     Page<Product> page = productService.findAll(pageable);
     modelMap.addAttribute("page", page);

     return "admin/bike/list";
    }

}
