package poly.tt.thuexe.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.tt.thuexe.Service.CustomUserDetails;
import poly.tt.thuexe.Service.CustomerService;

@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    PasswordEncoder passwordEncoder;



    @RequestMapping("/doimatkhau")
    public String showDoiMatKhau(Model model){
       return "user/common/doimatkhau";
    }
    @PostMapping("/doimatkhau")
    public String changePassword(@RequestParam String oldPassword,
                                  @RequestParam String newPassword,
                                  @RequestParam String confirmPassword,
                                  Authentication authentication,
                                  RedirectAttributes redirectAttributes) {
        
        // Lấy thông tin người dùng hiện tại đã xác thực
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
    
        // Kiểm tra xem mật khẩu mới và mật khẩu xác nhận có khớp nhau không
        if (!newPassword.equals(confirmPassword)) {
            // Mật khẩu không khớp
            redirectAttributes.addFlashAttribute("error", "Mật khẩu mới và mật khẩu xác nhận không khớp");
            return "redirect:/customer/doimatkhau";
        }
        
        // Kiểm tra và cập nhật mật khẩu
        if (customerService.changePassword(username, oldPassword, newPassword)) {
            // Đổi mật khẩu thành công

       
            // đăng xuất người dùng
            SecurityContextHolder.getContext().setAuthentication(null);
            redirectAttributes.addFlashAttribute("message", "Đổi mật khẩu thành công");
            return "redirect:/security/login/form";

        } else {
            // Mật khẩu hiện tại không chính xác
            redirectAttributes.addFlashAttribute("error", "Mật khẩu hiện tại không chính xác");
        }
    
        // Chuyển hướng đến trang thay đổi mật khẩu
        return "redirect:/customer/doimatkhau";
    }
    
    @GetMapping("/quenmatkhau")
    public String quenMatKhau(Model model){
        return "user/common/quenmatkhau";
    }




}
