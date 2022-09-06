package com.ll.exam.app10.app.base.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 클라이언트의 요청 url이 /gen-file 이라고 시작 될 경우
        registry.addResourceHandler("/gen/**")
                // 해당 파일 경로에 요청을 전달한다.
                .addResourceLocations("file:///" + genFileDirPath + "/");
    }
}