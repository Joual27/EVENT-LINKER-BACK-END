package org.youcode.EventLinkerAPI.shared.utils.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;
import org.youcode.EventLinkerAPI.organizer.DTOs.OrganizerRegistrationDTO;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.BaseRegistrationDTO;
import org.youcode.EventLinkerAPI.worker.DTOs.WorkerRegistrationDTO;

import java.util.Map;

public class BaseRegistrationDTOArgumentResolver implements HandlerMethodArgumentResolver {

    private final ObjectMapper objectMapper;
    private final Validator validator;

    public BaseRegistrationDTOArgumentResolver(ObjectMapper objectMapper, Validator validator) {
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return BaseRegistrationDTO.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String userType = pathVariables.get("userType").toUpperCase();

        Class<? extends BaseRegistrationDTO> dtoClass = switch (userType) {
            case "WORKER" -> WorkerRegistrationDTO.class;
            case "ORGANIZER" -> OrganizerRegistrationDTO.class;
            default -> throw new IllegalArgumentException("Invalid userType: " + userType);
        };

        BaseRegistrationDTO dto = objectMapper.readValue(request.getInputStream(), dtoClass);

        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(dto, "dto");
        validator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(parameter, bindingResult);
        }

        return dto;
    }
}