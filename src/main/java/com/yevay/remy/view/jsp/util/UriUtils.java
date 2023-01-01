package com.yevay.remy.view.jsp.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.function.BiFunction;

@Configuration
public class UriUtils {

    @Bean
    public BiFunction<String, Integer, String> replaceOrAddParamInt() {
        return (paramName, newValue) -> ServletUriComponentsBuilder.fromCurrentRequest()
                .replaceQueryParam(paramName, newValue)
                .toUriString();
    }
}
