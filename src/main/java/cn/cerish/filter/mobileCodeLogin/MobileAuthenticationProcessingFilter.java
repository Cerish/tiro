package cn.cerish.filter.mobileCodeLogin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MobileAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_MOBILE_KEY = "mobile";
    public static final String SPRING_SECURITY_FORM_CODE_KEY = "code";

    private String mobileParameter = SPRING_SECURITY_FORM_MOBILE_KEY;
    private String codeParameter = SPRING_SECURITY_FORM_CODE_KEY;
    private boolean postOnly = true;

    public MobileAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/auth/sms", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String username = obtainMobile(request);
        String password = obtainCode(request);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        MobileCodeAuthenticationToken authRequest = new MobileCodeAuthenticationToken(
                username, password, null);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }

    protected String obtainCode(HttpServletRequest request) {
        return request.getParameter(codeParameter);
    }

    protected void setDetails(HttpServletRequest request,
                              MobileCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}