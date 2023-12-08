package poly.tt.thuexe.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.tt.thuexe.Entity.Customer;
import poly.tt.thuexe.Service.CustomUserDetails;
import poly.tt.thuexe.Service.CustomerService;

@Controller
public class ProfileController {
    
    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/profile")
    public String showProfile(Model model, Authentication authentication){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("fullname", userDetails.getFullname());
        model.addAttribute("email", userDetails.getEmail());
        model.addAttribute("phone", userDetails.getPhone());
        model.addAttribute("address", userDetails.getAddress());
        model.addAttribute("roles", userDetails.getAuthorities());
        return "user/common/profile";
    }
}
