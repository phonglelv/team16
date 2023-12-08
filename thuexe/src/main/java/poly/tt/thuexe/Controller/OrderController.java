package poly.tt.thuexe.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import poly.tt.thuexe.Entity.Order;
import poly.tt.thuexe.Service.OrderService;

@Controller
public class OrderController {
       @Autowired
       OrderService orderService;
	 @RequestMapping("/order/checkout")
	 public String checkout() {
		 return "user/order/checkout";
	 }
	 
	 @RequestMapping("/order/list")
	 public String list(Model model, HttpServletRequest request) {
		 String username = request.getRemoteUser();
		 model.addAttribute("orders", orderService.findByUsername1(username));
		
		 return "user/order/list";
	 }
	 
	 
	 
	  @RequestMapping("/order/detail/{id}")
	    public String detail(@PathVariable("id") Long id, Model model) {
	        Order order = orderService.findById(id).get();
	        model.addAttribute("order", order);
	        model.addAttribute("orderDetails", order.getOrderDetails()); // Thêm orderDetails vào model
	        return "user/order/detail";
	    }
	  
	  @RequestMapping("/order/detail/delete/{id}")
	  public String deleteOrder(@PathVariable("id") Long id) {
	      // Implement the logic to delete the order with the given id
	      orderService.deleteOrderById(id);
	      return "redirect:/order/list"; // Redirect to the list of orders after deletion
	  }
}
