package poly.tt.thuexe.AdminController;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import poly.tt.thuexe.Entity.Customer;
import poly.tt.thuexe.Entity.Report;
import poly.tt.thuexe.Model.ReportModel;
import poly.tt.thuexe.Service.CustomerService;
import poly.tt.thuexe.Service.ReportService;
import poly.tt.thuexe.Service.SessionService;

@Controller
@RequestMapping("admin/reports")
public class ReportAdminController {
    @Autowired
    ReportService reportService;
    @Autowired
    CustomerService customerService;

    @Autowired
    SessionService session;

    @GetMapping("add")
    public String add(ModelMap modelMap){
        ReportModel model = new ReportModel();
        List<Customer> list = customerService.findAll();
        modelMap.addAttribute("customers", list);
        model.setIsEdit(false);
        modelMap.addAttribute("report", new ReportModel());
        return "admin/report/addReport";
    }
    @GetMapping("edit/{id}")
    public ModelAndView edit(ModelMap modelMap, @PathVariable("id") Long id) {
        Optional<Report> customer = reportService.findById(id);
        ReportModel customerModel = new ReportModel();
        if (customer.isPresent()) {
            Report orders = customer.get();
            List<Customer> list = customerService.findAll();
            modelMap.addAttribute("customers", list);

            BeanUtils.copyProperties(orders, customerModel);
            customerModel.setUsername(orders.getCustomer().getUsername());

            customerModel.setIsEdit(true);
            List<Customer> lists = customerService.findAll();
            modelMap.addAttribute("customers", lists);


            modelMap.addAttribute("report", customerModel);
            return new ModelAndView("admin/report/addReport", modelMap);
        }
        modelMap.addAttribute("message", "Không tìm thấy");
        return  new ModelAndView("forward:/admin/reports",modelMap);
    }
    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id , ModelMap modelMap){
        reportService.deleteById(id);

        modelMap.addAttribute("message" ,"Xóa thành công");
        return new ModelAndView("forward:/admin/reports",modelMap);
    }
    @PostMapping("/saveOrUpdate")
    public ModelAndView save(ModelMap modelMap, @jakarta.validation.Valid @ModelAttribute("report") ReportModel reportModel, BindingResult result){
        if (result.hasErrors()){
            modelMap.addAttribute("error", "Thất bại");
            List<Customer> list = customerService.findAll();
            modelMap.addAttribute("customers", list);
            return new ModelAndView( "admin/report/addReport",modelMap);
        }else {
            List<Customer> list = customerService.findAll();
            modelMap.addAttribute("customers", list);

            Report entity = new Report();
            Customer customer = new Customer();
            BeanUtils.copyProperties(reportModel, entity);
            customer.setUsername(reportModel.getUsername());
            entity.setCustomer(customer);

            reportService.save(entity);
            modelMap.addAttribute("message", "Thành công");


            return new ModelAndView("forward:/admin/reports");
        }
    }
    @RequestMapping("/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw,
                                @RequestParam("p") Optional<Integer> p) {
        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Report> page = reportService.findAllByReportNameLike("%"+kwords+"%", pageable);
        model.addAttribute("page",page);
        return "admin/report/list";

    }
    @RequestMapping("")
    public String list(ModelMap modelMap, @ModelAttribute("report") @RequestParam("p") Optional<Integer> p){
        Pageable pageable = PageRequest.of(p.orElse(0),5);
        Page<Report> page = reportService.findAll(pageable);
        modelMap.addAttribute("page", page);

        return "admin/report/list";
    }
}
