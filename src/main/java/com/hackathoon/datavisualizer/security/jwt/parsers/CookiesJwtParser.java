package com.hackathoon.datavisualizer.security.jwt.parsers;

import com.hackathoon.datavisualizer.exception.AuthCookieMissingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Stream;
import javax.servlet.http.Cookie;

@Component
public class CookiesJwtParser implements JwtParser {

    @Value("${app.authCookieName}")
    private String authCookieName;
    private String exceptionMessage;

    private String getExceptionMessage() {
        if (exceptionMessage == null) {
            this.exceptionMessage = String.format("The cookie %s is missing.", authCookieName);
        }
        return exceptionMessage;
    }

    @Override
    public String parse(final HttpServletRequest request) {
        return Optional.ofNullable(request.getCookies())
                .map(Stream::of)
                .orElseThrow(() -> new AuthCookieMissingException("The cookies are missing!"))
                .filter(cookie -> authCookieName.equals(cookie.getName()))
                .findAny()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthCookieMissingException(getExceptionMessage()));
    }

}