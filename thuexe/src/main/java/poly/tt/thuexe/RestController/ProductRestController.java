package poly.tt.thuexe.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poly.tt.thuexe.Entity.Product;
import poly.tt.thuexe.Service.ProductService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
    @Autowired
    ProductService productService;


    @GetMapping()
    public List<Product> getALl(){
        return productService.findAll();
    }

    @GetMapping("{id}")
    public Product getOne(@PathVariable("id") Integer id){
        return productService.findById(id).get();
    }

    @PostMapping
    public Product create(@RequestBody Product product){
        return productService.create(product);
    }

    @PutMapping("{id}")
    public Product upadate(@PathVariable("id") Integer id, @RequestBody Product product){
        return productService.update(product);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){
        productService.deleteById(id);
    }
}
