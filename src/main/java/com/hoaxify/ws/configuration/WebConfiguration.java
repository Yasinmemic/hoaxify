package com.hoaxify.ws.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    AppConfiguration appConfiguration;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:./" + appConfiguration.getUploadPath() + "/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
    }

    @Bean
    CommandLineRunner createStorageDirectories() {
        return (args -> {
            createFolder(appConfiguration.getUploadPath());
            createFolder(appConfiguration.getAttachmentStoragePath());
            createFolder(appConfiguration.getProfileStoragePath());
        });
    }

    private void createFolder(String path) {
        File file = new File(path);
        boolean folderExist = file.exists() && file.isDirectory();
        if (!folderExist) {
            file.mkdir();
        }
    }
}
