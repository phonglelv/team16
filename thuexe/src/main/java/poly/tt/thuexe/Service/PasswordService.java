package poly.tt.thuexe.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import poly.tt.thuexe.Config.ForgotPasswordToken;

@Service
public class PasswordService {
    
     private final int MINUTES = 10;
     @Autowired 
     JavaMailSender javaMailSender;
    public String generateToken(){
        return UUID.randomUUID().toString();
    }

    public LocalDateTime expireTimeRange(){
        return LocalDateTime.now().plusMinutes(MINUTES);
    }
    

    public void sendEmail(String to, String subject, String emailLink) throws MessagingException, UnsupportedEncodingException{
    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new  MimeMessageHelper(message);
         String emailContent = "<p>Xin chào khách hàng</p>"+ "Nhấp vào liên kết bên dưới để đặt lại mật khẩu"
         + "<p><a href=\""+ emailLink  +"\">Thay đổi mật khẩu  </a></p>"
         + "Bỏ qua email này nếu bạn không thực hiện yêu cầu";
        helper.setText(emailContent, true);
        helper.setFrom("lucu12145@gmail.com", "Bộ phận thuê xe hổ trợ mật khẩu");
        helper.setSubject(subject);
        helper.setTo(to);
        javaMailSender.send(message);
    }


    public boolean isExpired (ForgotPasswordToken forgotPasswordToken){
        return LocalDateTime.now().isAfter(forgotPasswordToken.getExpireTime());
    }

    public String checkValidity (ForgotPasswordToken forgotPasswordToken, Model model){
       if (forgotPasswordToken == null) {
        model.addAttribute("error", "Invalid Token");
        return "user/common/error-page";
       }
         else if(forgotPasswordToken.isUsed()){
                 model.addAttribute("error", "The token is already used");
        return "user/common/error-page";
         }
         else if(isExpired(forgotPasswordToken)){
                 model.addAttribute("error", "The token is expired");
        return "user/common/error-page";
         }else {
             return "user/common/reset-password";
         }

         
    }
}
