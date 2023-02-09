package com.daxiong.mall.config;

import com.daxiong.mall.common.Constant;
import com.daxiong.mall.filter.AdminInterceptor;
import com.daxiong.mall.filter.UserInterceptor;
import io.lettuce.core.internal.LettuceLists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc 配置类
 */
@Configuration
public class MallWebMvcConfig implements WebMvcConfigurer {
    /**
     * 加上@Bean注解，放入IoC容器中
     * @return
     */
    @Bean
    public AdminInterceptor getAdminInterceptor() {
        return new AdminInterceptor();
    }
    @Bean
    public UserInterceptor getUserInterceptor() {
        return new UserInterceptor();
    }
    /**
     * 添加WebMvc项目的拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 用户拦截器
        registry.addInterceptor(getUserInterceptor())
                .addPathPatterns(LettuceLists.newList("/customer/**", "/cart/**", "/order/**"))
                .excludePathPatterns("/order/pay"); // 支付订单页面可以不需要登录用户（别人代付的情况）
        // 管理员拦截器
        registry.addInterceptor(getAdminInterceptor())
                .addPathPatterns(LettuceLists.newList("/admin/category/*", "/admin/product/*", "/admin/order/*"));
    }

    /**
     * 资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 后台页面
        registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/static/admin/");
        // 转发图片请求
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + Constant.FILE_UPLOAD_DIR);
    }
}
