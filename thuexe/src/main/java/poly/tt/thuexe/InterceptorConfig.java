package poly.tt.thuexe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import poly.tt.thuexe.Interceptor.Globallnterceptor;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    Globallnterceptor globallnterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globallnterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/rest/**","/admin**","/assets/**");
    }
}
