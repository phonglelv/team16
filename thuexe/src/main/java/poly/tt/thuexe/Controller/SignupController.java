package poly.tt.thuexe.Controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import poly.tt.thuexe.Entity.Customer;
import poly.tt.thuexe.Model.CustomerModel;
import poly.tt.thuexe.Repository.RoleRepository;
import poly.tt.thuexe.Service.CustomerService;


@Controller
public class SignupController {

    @Autowired
    CustomerService customerService;
  @Autowired
    RoleRepository roleRepository;

    @RequestMapping("Signup")
    public  String list(Model model){
        model.addAttribute("customer", new CustomerModel());
        return "user/common/dangky";
    }
    @PostMapping("/save")
    public ModelAndView save(ModelMap modelMap, @Valid @ModelAttribute("customer") CustomerModel customerModel, BindingResult result) {
        if (result.hasErrors()) {
            modelMap.addAttribute("error", "Đăng ký thất bại");
            return new ModelAndView("user/common/dangky", modelMap);
        } else {
            try {
                Customer entity = new Customer();
                BeanUtils.copyProperties(customerModel, entity);

                // Set vai trò mặc định cho khách hàng
                customerService.save(entity);
                modelMap.addAttribute("message", "Đăng ký thành công");

                return new ModelAndView("forward:/Signup", modelMap);
            } catch (Exception e) {
                e.printStackTrace(); // Add proper logging in a real application
                modelMap.addAttribute("error", "Đã xảy ra lỗi khi xử lý đăng ký");
                return new ModelAndView("user/common/dangky", modelMap);
            }
        }
    }

}
