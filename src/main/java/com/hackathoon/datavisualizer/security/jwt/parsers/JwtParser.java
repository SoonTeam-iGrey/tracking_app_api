package com.hackathoon.datavisualizer.security.jwt.parsers;

import javax.servlet.http.HttpServletRequest;

public interface JwtParser {

    String parse(HttpServletRequest request);

}
