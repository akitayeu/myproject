package com.samsolutions.kitayeu.myproject.config;

import com.samsolutions.kitayeu.myproject.converters.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DepartmentToDtoConverter());
        registry.addConverter(new DtoToDepartmentConverter());
        registry.addConverter(new RoleToDtoConverter());
        registry.addConverter(new DtoToRoleConverter());
        registry.addConverter(new EmployeeToDtoConverter());
        registry.addConverter(new DtoToUserConverter());
        registry.addConverter(new UserToDtoConverter());
    }
}
