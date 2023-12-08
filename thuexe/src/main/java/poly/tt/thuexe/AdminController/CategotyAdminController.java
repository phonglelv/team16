package poly.tt.thuexe.AdminController;

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

import jakarta.validation.Valid;
import poly.tt.thuexe.Entity.Category;
import poly.tt.thuexe.Model.CategotyModel;
import poly.tt.thuexe.Service.CategotyServiceImpl;
import poly.tt.thuexe.Service.SessionService;

@Controller
@RequestMapping("admin/categories")
public class CategotyAdminController {
    @Autowired
     CategotyServiceImpl categotyService;
    @Autowired
    SessionService session;
     @GetMapping("add")
     public String add(Model model){

          model.addAttribute("category", new CategotyModel());
         return "admin/category/addCate";
     }

@GetMapping("edit/{id}")
public ModelAndView edit(ModelMap modelMap, @PathVariable("id") Integer id){
    Optional<Category> categories = categotyService.findById(id);

    CategotyModel categotyModel = new CategotyModel();
    if (categories.isPresent()){
        Category category = categories.get();

        BeanUtils.copyProperties(category,categotyModel);
        categotyModel.setIsEdit(true);

        modelMap.addAttribute("category",categotyModel);
        return new ModelAndView("admin/category/addCate",modelMap);

    }
    modelMap.addAttribute("message", "Không tìm thấy");
        return  new ModelAndView("forward:/admin/categories",modelMap);
}

@GetMapping("delete/{id}")
public ModelAndView delete(@PathVariable("id") Integer id , ModelMap modelMap){
         categotyService.deleteById(id);

         modelMap.addAttribute("message" ,"Xóa thành công");
         return new ModelAndView("forward:/admin/categories",modelMap);
}
    @PostMapping("/saveOrUpdate")
    public ModelAndView save(ModelMap modelMap, @Valid  @ModelAttribute("category")CategotyModel model, BindingResult result){
        if (result.hasErrors()){
            modelMap.addAttribute("error", "Thất bại");
            return new ModelAndView( "admin/category/addCate",modelMap);
        }else {
            Category entity = new Category();
            BeanUtils.copyProperties(model, entity);

            categotyService.save(entity);
            modelMap.addAttribute("message", "Thành công");


            return new ModelAndView("forward:/admin/categories", modelMap);
        }
    }
    @RequestMapping("/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw,
                                @RequestParam("p") Optional<Integer> p) {
        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Category> page = categotyService.findAllByNameLike("%"+kwords+"%", pageable);
        model.addAttribute("page",page);
        return "admin/category/list";

    }

    @RequestMapping("")
     public String list(ModelMap model, @RequestParam("p") Optional<Integer> p){

          Pageable pageable = PageRequest.of(p.orElse(0),5);
          Page<Category> page = categotyService.findAll(pageable);
          model.addAttribute("page",page);

          return "admin/category/list";
     }
}
