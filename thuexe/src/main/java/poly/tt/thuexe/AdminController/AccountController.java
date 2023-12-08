package poly.tt.thuexe.AdminController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.tt.thuexe.Entity.Customer;
import poly.tt.thuexe.Entity.Role;
import poly.tt.thuexe.Repository.RoleRepository;
import poly.tt.thuexe.Service.CustomerService;
import poly.tt.thuexe.Service.SessionService;

@Controller
@RequestMapping("admin/accounts")
public class AccountController {

@Autowired
    CustomerService customerService;

@Autowired
    SessionService sessionService;
@Autowired
    RoleRepository roleRepository;

    // Trong AccountController
    @RequestMapping("")
    public String list(ModelMap model, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Customer> page = customerService.findAll(pageable);
          
        
        List<Role> roles = roleRepository.findAll(); // Thay roleRepository bằng service tương ứng nếu có
        model.addAttribute("roles", roles);
        model.addAttribute("page", page);
        return "admin/account/list";
    }

}
