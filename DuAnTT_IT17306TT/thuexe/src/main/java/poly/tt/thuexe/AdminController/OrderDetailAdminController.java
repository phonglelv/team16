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
import poly.tt.thuexe.Entity.OrderDetail;
import poly.tt.thuexe.Entity.Product;
import poly.tt.thuexe.Model.OrderDetailModel;
import poly.tt.thuexe.Model.OrderModel;
import poly.tt.thuexe.Service.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/orderdetails")
public class OrderDetailAdminController {
     @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    OrderService orderService;
    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;
    @Autowired
    SessionService session;


    @GetMapping("add")
    public String add(ModelMap modelMap) {
        OrderDetailModel dto = new OrderDetailModel();
        List<Order> orders = orderService.findAll();
        List<Product> products = productService.findAll();

        modelMap.addAttribute("orders", orders);
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("orderDetail", dto);

        return "admin/order/addOrderDetail";
    }
    @GetMapping("edit/{id}")
    public ModelAndView edit(ModelMap modelMap, @PathVariable("id") Long id){
        Optional<OrderDetail> customer = orderDetailService.findById(id);
        OrderDetailModel customerModel = new OrderDetailModel();
        if (customer.isPresent()){
            OrderDetail orderDetail = customer.get();

            List<Order> orders = orderService.findAll();
            List<Product> products = productService.findAll();

            modelMap.addAttribute("orders", orders);
            modelMap.addAttribute("products", products);

            BeanUtils.copyProperties(orderDetail, customerModel);
            customerModel.setProductId(orderDetail.getProduct().getId());
            customerModel.setOrderId(orderDetail.getOrder().getId());

            customerModel.setIsEdit(true);


            modelMap.addAttribute("orderDetail",customerModel);
            return new ModelAndView("admin/order/addOrderDetail",modelMap);

        }
        modelMap.addAttribute("message", "Không tìm thấy");
        return  new ModelAndView("forward:/admin/orderdetails",modelMap);

    }
    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(ModelMap modelMap, @Valid @ModelAttribute("orderDetail") OrderDetailModel orderModel, BindingResult result) {
        // Validate or perform any necessary checks on orderModel
        if (result.hasErrors()){

            modelMap.addAttribute("error", "Thất bại");
            List<Order> orders = orderService.findAll();
            List<Product> products = productService.findAll();

            modelMap.addAttribute("orders", orders);
            modelMap.addAttribute("products", products);

            return "admin/order/addOrderDetail";
        }else{


        // Create new OrderDetail entity
        OrderDetail entity = new OrderDetail();
        BeanUtils.copyProperties(orderModel, entity);

        // Fetch the corresponding Order and Product from the database based on IDs
        Order order = orderService.findById(orderModel.getOrderId()).orElse(null);
        Product product = productService.findById(orderModel.getProductId()).orElse(null);

        if (order != null && product != null) {
            // Set the Order and Product for the OrderDetail
            entity.setOrder(order);
            entity.setProduct(product);

            // Save or update the OrderDetail
            orderDetailService.save(entity);
            modelMap.addAttribute("message", "Thành công");

        } else {
            modelMap.addAttribute("message", "Lỗi: Đơn hàng hoặc sản phẩm không tồn tại");
        }

        // Redirect to the order details page
        return "redirect:/admin/orderdetails";
        }
    }


    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id , ModelMap modelMap){
        orderDetailService.deleteById(id);

        modelMap.addAttribute("message" ,"Xóa thành công");
        return new ModelAndView("forward:/admin/orderdetails",modelMap);
    }
    @RequestMapping("/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw,
                                @RequestParam("p") Optional<Integer> p) {
        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<OrderDetail> page = orderDetailService.findByNameProduct("%"+kwords+"%", pageable);
        model.addAttribute("page",page);
        return "admin/order/listOrderDetail";

    }

    @RequestMapping("")
    public String list(ModelMap modelMap, @RequestParam("p") @ModelAttribute("orderDetail")Optional<Integer> p){
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<OrderDetail> page = orderDetailService.findAll(pageable);
        modelMap.addAttribute("page", page);

        return "admin/order/listOrderDetail";
    }

}
