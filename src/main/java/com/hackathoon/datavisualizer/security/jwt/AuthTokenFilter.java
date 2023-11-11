package com.hackathoon.datavisualizer.security.jwt;

import com.hackathoon.datavisualizer.exception.AuthTokenMissingException;
import com.hackathoon.datavisualizer.security.details.UserDetailsServiceImpl;
import com.hackathoon.datavisualizer.security.jwt.parsers.JwtParser;
import com.licenta.datavisualizer.Mappings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.licenta.datavisualizer.Mappings.PUBLIC_API;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;
    private final List<JwtParser> jwtParsers;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String servletPath = request.getServletPath();
        return servletPath.startsWith(Mappings.AUTH) || servletPath.startsWith(PUBLIC_API);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (shouldNotFilter(request)) {
                return;
            }
            String jwt = parseRequest(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.info("Is failed due to the request for: " + request.getServletPath());
            log.error("Cannot set user authentication: {}", e.getMessage(), e);
        }
        filterChain.doFilter(request, response);
    }

    public String parseRequest(HttpServletRequest request) {
        AuthTokenMissingException exceptionResult = new AuthTokenMissingException("Something went wrong while parsing your token.");
        for (JwtParser parser: jwtParsers) {
            try {
                String result = parser.parse(request);
                if (result != null) {
                    return parser.parse(request);
                }
            } catch (AuthTokenMissingException missingException) {
                exceptionResult = missingException;
            }
        }
        throw exceptionResult;
    }

}
