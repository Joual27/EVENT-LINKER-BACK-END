package org.youcode.EventLinkerAPI.config.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.youcode.EventLinkerAPI.shared.utils.serialization.BaseRegistrationDTOArgumentResolver;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final ObjectMapper objectMapper;
    private final Validator validator;

    public WebMvcConfig(ObjectMapper objectMapper, Validator validator) {
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(0 , new BaseRegistrationDTOArgumentResolver(objectMapper, validator));
    }
}
