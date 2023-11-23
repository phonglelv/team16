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
import poly.tt.thuexe.Entity.Customer;
import poly.tt.thuexe.Entity.Order;
import poly.tt.thuexe.Model.CustomerModel;
import poly.tt.thuexe.Model.OrderModel;
import poly.tt.thuexe.Service.CustomerService;
import poly.tt.thuexe.Service.OrderService;
import poly.tt.thuexe.Service.SessionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/orders")
public class OrderAdminController {
    @Autowired
    OrderService orderService;
    @Autowired
    CustomerService customerService;

    @Autowired
    SessionService session;


    @GetMapping("add")
    public String add(ModelMap modelMap){
        OrderModel dto = new OrderModel();
        List<Customer> list = customerService.findAll();
        modelMap.addAttribute("customers", list);
        dto.setIsEdit(false);
        modelMap.addAttribute("order", dto);
        modelMap.addAttribute("order", new OrderModel());
        return "admin/order/addOrder";
    }
    @GetMapping("edit/{id}")
    public ModelAndView edit(ModelMap modelMap, @PathVariable("id") Long id){
        Optional<Order> customer = orderService.findById(id);
        OrderModel customerModel = new OrderModel();
        if (customer.isPresent()){
            Order orders = customer.get();
            List<Customer> list = customerService.findAll();
            modelMap.addAttribute("customers", list);

            BeanUtils.copyProperties(orders, customerModel);
            customerModel.setUsername(orders.getCustomer().getUsername());

            customerModel.setIsEdit(true);
            List<Customer> lists = customerService.findAll();
            modelMap.addAttribute("customers", lists);

            modelMap.addAttribute("order",customerModel);
            return new ModelAndView("admin/order/addOrder",modelMap);

        }
        modelMap.addAttribute("message", "Không tìm thấy");
        return  new ModelAndView("forward:/admin/orders",modelMap);

    }
    @PostMapping("/saveOrUpdate")
    public ModelAndView save(ModelMap modelMap,  @Valid @ModelAttribute("order") OrderModel orderModel, BindingResult result){

       if (result.hasErrors()){
           modelMap.addAttribute("error", "Thất bại");
           List<Customer> list = customerService.findAll();
           modelMap.addAttribute("customers", list);
           return new ModelAndView( "admin/order/addOrder",modelMap);
       }else{


        List<Customer> list = customerService.findAll();
        modelMap.addAttribute("customers", list);

            Order entity = new Order();
            Customer customer = new Customer();
            BeanUtils.copyProperties(orderModel, entity);
            customer.setUsername(orderModel.getUsername());
             entity.setCustomer(customer);

            orderService.save(entity);
            modelMap.addAttribute("message", "Thành công");


            return new ModelAndView("forward:/admin/orders");
       }
    }

    @RequestMapping("/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw,
                                @RequestParam("p") Optional<Integer> p) {
        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Order> page = orderService.findByUsername("%"+kwords+"%", pageable);
        model.addAttribute("page",page);
        return "admin/order/list";

    }
    @RequestMapping("detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Order order = orderService.findById(id).get();
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", order.getOrderDetails()); // Thêm orderDetails vào model
        return "admin/order/detail";
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id , ModelMap modelMap){
        orderService.deleteById(id);

        modelMap.addAttribute("message" ,"Xóa thành công");
        return new ModelAndView("forward:/admin/orders",modelMap);
    }
    @RequestMapping("")
    public String list(ModelMap modelMap, @RequestParam("p") @ModelAttribute("order")Optional<Integer> p){
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Order> page = orderService.findAll(pageable);
        modelMap.addAttribute("page", page);

        return "admin/order/list";
    }
}
