package com.wzs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/css/**","/js/**","/"
                        ,"/user/**","/mNotice/getHotTemp"
                        ,"/mNotice/getNoticeById","/search","/topic/getTopicList","/mGuess/getMEvid"
                        ,"/comment/getCommentsOfNotice");
    }
}
