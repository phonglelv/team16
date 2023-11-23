package poly.tt.thuexe.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.tt.thuexe.Service.CategotyServiceImpl;


@Component
public class Globallnterceptor implements HandlerInterceptor {
    @Autowired
    CategotyServiceImpl categoryService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        request.setAttribute("cates", categoryService.findAll());
    }
}

