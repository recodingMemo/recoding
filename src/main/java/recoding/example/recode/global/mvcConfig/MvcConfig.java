package recoding.example.recode.global.mvcConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import recoding.example.recode.global.tokenverify.JwtInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${custom.jwt.secretKey}")
    private String secretKey;

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor(secretKey))
//                .addPathPatterns("/**")  // 이 인터셉터를 모든 경로에 적용합니다.
                .excludePathPatterns(
                        "/api/v1/company/code-search",
                        "/api/v1/company/check-username",
                        "/api/v1/company/check-businessNumber",
                        "/api/v1/company/check-name",
                        "/api/v1/company/join",
                        "/api/v1/company/id-search",
                        "/api/v1/company/pw-search",
                        "/api/v1/company/pw-modify",
                        "/api/v1/email/send",
                        "/api/v1/member/login",
                        "/api/v1/member/user-manages",
                        "/api/v1/member/delete",
                        "/api/v1/member/modify",
                        "/api/v1/member/modify-password",
                        "/api/v1/test/test"
                );  // 이 경로들에는 적용하지 않습니다.

    }
//
//    @Value("${myapp.image-path}")
//    private String imagePath;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/images/**")
//                .addResourceLocations(imagePath);
//    }
}