package poly.tt.thuexe.AdminController;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import poly.tt.thuexe.Entity.Category;
import poly.tt.thuexe.Entity.Customer;
import poly.tt.thuexe.Model.CategotyModel;
import poly.tt.thuexe.Model.CustomerModel;
import poly.tt.thuexe.Service.CustomerService;
import poly.tt.thuexe.Service.SessionService;

import java.util.Optional;

@Controller
@RequestMapping("admin/customers")
public class CustomerAdminController {
    @Autowired
    CustomerService customerService;
    @Autowired
    SessionService session;
    @GetMapping("add")
    public String add(ModelMap modelMap){
        modelMap.addAttribute("customer", new CustomerModel());
        return "admin/customer/addCus";
    }


@GetMapping("edit/{username}")
public ModelAndView edit(ModelMap modelMap, @PathVariable("username") String username){
Optional<Customer> customer = customerService.findById(username);
    CustomerModel customerModel = new CustomerModel();
    if (customer.isPresent()){
        Customer customers = customer.get();
        BeanUtils.copyProperties(customers, customerModel);
        customerModel.setIsEdit(true);

        modelMap.addAttribute("customer",customerModel);
        return new ModelAndView("admin/customer/addCus",modelMap);

    }
    modelMap.addAttribute("message", "Không tìm thấy");
    return  new ModelAndView("forward:/admin/customers",modelMap);

}

    @GetMapping("delete/{username}")
    public ModelAndView delete(@PathVariable("username") String username , ModelMap modelMap){
        customerService.deleteById(username);

        modelMap.addAttribute("message" ,"Xóa thành công");
        return new ModelAndView("forward:/admin/customers",modelMap);
    }
    @PostMapping("/saveOrUpdate")
    public ModelAndView save(ModelMap modelMap, @Valid @ModelAttribute("customer") CustomerModel model, BindingResult result){
        if (result.hasErrors()){
            modelMap.addAttribute("error", "Thất bại");
            return new ModelAndView( "admin/customer/addCus",modelMap);
        }else {
            Customer entity = new Customer();
            BeanUtils.copyProperties(model, entity);

            customerService.save(entity);
            modelMap.addAttribute("message", "Thành công");


            return new ModelAndView("forward:/admin/customers", modelMap);
        }
    }
    @RequestMapping("/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw,
                                @RequestParam("p") Optional<Integer> p) {
        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Customer> page = customerService.findAllByFullnameLike("%"+kwords+"%", pageable);
        model.addAttribute("page",page);
        return "admin/customer/list";

    }


    @RequestMapping("")
    public String list(ModelMap modelMap, @RequestParam("p") @ModelAttribute("customer") Optional<Integer> p){
        Pageable pageable = PageRequest.of(p.orElse(0),5 );
        Page<Customer> page = customerService.findAll(pageable);
        modelMap.addAttribute("page", page);

        return "admin/customer/list";
    }
}
