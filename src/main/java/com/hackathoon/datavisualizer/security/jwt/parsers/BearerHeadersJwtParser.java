package com.hackathoon.datavisualizer.security.jwt.parsers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Primary
@Component
public class BearerHeadersJwtParser implements JwtParser {

    @Override
    public String parse(final HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.isNotEmpty(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }

}