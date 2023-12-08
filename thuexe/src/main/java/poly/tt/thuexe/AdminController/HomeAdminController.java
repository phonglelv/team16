package poly.tt.thuexe.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.tt.thuexe.Service.CustomerService;
import poly.tt.thuexe.Service.OrderDetailService;
import poly.tt.thuexe.Service.ReportService;

@Controller
public class HomeAdminController {
    @Autowired
    CustomerService customerService;
@Autowired
    ReportService reportService;
    @Autowired
    OrderDetailService orderDetailService;
     @RequestMapping({"/","/home/index"})
    public String home(){
            return "redirect:/home";
    }

    @RequestMapping("admin")
    public String admin(Model model) {

            long registeredUsersCount = customerService.getTotalCustomers();
            model.addAttribute("registeredUsersCount", registeredUsersCount);
        double totalOrderAmount = orderDetailService.getTotalOrderAmountByOrderId();
        model.addAttribute("totalOrderAmount", totalOrderAmount);
        long registeredReportCount = reportService.getTotalReports();
        model.addAttribute("registeredReportsCount", registeredReportCount);
     return "admin/home/index";
    }
}
