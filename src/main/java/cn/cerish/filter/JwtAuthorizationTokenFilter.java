package cn.cerish.filter;

import cn.cerish.util.JwtTokenUtils;
import cn.cerish.util.UserServiceUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private UserServiceUtils userServiceUtils;


    @Value("${jwt.token_header}")
    private String tokenHeader;
    @Value("${jwt.prefix}")
    private String prefix;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        final String requestHeader = request.getHeader(this.tokenHeader);
        String username = null;
        int roleId = 0;
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith(this.prefix)) {
            authToken = requestHeader.substring(this.prefix.length());
            try {
                Claims claims = jwtTokenUtils.getClaimsFromToken(authToken);
                username = claims.get("username").toString();
                roleId = (int) claims.get("roleId");
            }catch (Exception e){

            }
        }
        // 登录成功，还没设置Authentication
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userServiceUtils.getUserDetails(username, roleId);

            if (jwtTokenUtils.validateToken(authToken)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        chain.doFilter(request, response);
    }
}
