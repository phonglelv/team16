package poly.tt.thuexe.Controller;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import poly.tt.thuexe.Config.ForgotPasswordToken;
import poly.tt.thuexe.Entity.Customer;
import poly.tt.thuexe.Repository.ForgotPasswordTokenRepository;
import poly.tt.thuexe.Service.CustomerService;
import poly.tt.thuexe.Service.PasswordService;

@Controller
public class ForgotPasswordController {
  

  @Autowired
  private CustomerService customerService;
 
  @Autowired
  private PasswordService fogotPasswordService;

  @Autowired
  ForgotPasswordTokenRepository forgotPasswordTokenRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

   @GetMapping("/password-request")
   public String passwordRequest(){


    
    return "user/common/password-request";
   }

   @PostMapping("/password-request")
   public String savePasswordRequest(@RequestParam("email") String email, Model model){
         Customer user = customerService.findByEmail(email);
         if (user == null) {
          model.addAttribute("error", "This Email is not registerd");
          return "user/common/password-request";
         }
     ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken();
      forgotPasswordToken.setExpireTime(fogotPasswordService.expireTimeRange());
      forgotPasswordToken.setToken(fogotPasswordService.generateToken());
      forgotPasswordToken.setUser(user);
      forgotPasswordToken.setUsed(false);
      
         forgotPasswordTokenRepository.save(forgotPasswordToken);

      String emailLink = "http://localhost:8080/reset-password?token=" + forgotPasswordToken.getToken();

          try {
            fogotPasswordService.sendEmail(user.getEmail(), "Password Rest Link", emailLink);
          } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error While Sending email");
          return "user/common/password-request";
          }
    return "redirect:/password-request?success";
   }

     @GetMapping("/reset-password")
   public String restPassword(@Param(value = "token") String token, Model model, HttpSession session){
      session.setAttribute("token", token);
    ForgotPasswordToken forgotPasswordToken =  forgotPasswordTokenRepository.findByToken(token);
        return fogotPasswordService.checkValidity(forgotPasswordToken, model);


   }


    @PostMapping("/reset-password")
   public String saveRestPassword(HttpServletRequest request, HttpSession session, Model model){
String password = request.getParameter("password");
String token = (String) session.getAttribute("token");
ForgotPasswordToken forgotPasswordToken = forgotPasswordTokenRepository.findByToken(token);
Customer user = forgotPasswordToken.getUser();
 user.setPassword(passwordEncoder.encode(password));
      forgotPasswordToken.setUsed(true);
customerService.saveToken(user);
forgotPasswordTokenRepository.save(forgotPasswordToken);

    // Đăng xuất người dùng
    SecurityContextHolder.getContext().setAuthentication(null);

    // Thêm thông báo thành công vào model
    model.addAttribute("message", "Bạn đã khôi phục lại mật khẩu thành công");

    // Chuyển hướng đến trang đăng nhập
    return "redirect:/security/login/form";
   }
}
