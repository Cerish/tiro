package cn.cerish.filter.usernamePasswordLogin;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cerish.filter.mobileCodeLogin.MobileCodeAuthenticationToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;


public class UserPwdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    // ~ Static fields/initializers
    // =====================================================================================

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    public static final String SPRING_SECURITY_FORM_ROLETYPE_KEY = "roleType";

    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    private String roletypeParameter = SPRING_SECURITY_FORM_ROLETYPE_KEY;
    private boolean postOnly = true;

    // ~ Constructors
    // ===================================================================================================
    // 设置拦截路径
    public UserPwdAuthenticationFilter() {
        super(new AntPathRequestMatcher("/auth/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String roletype = obtainTRoletype(request);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        if (roletype == null) {
            roletype = "";
        }

        username = username.trim();

        AbstractAuthenticationToken
                authRequest = new UserPwdAuthenticationToken(username, password, null);
        HashMap hashMap = new HashMap();
        hashMap.put("roletype", roletype);
        authRequest.setDetails(hashMap);
        /*UserPwdAuthenticationToken authRequest = new UserPwdAuthenticationToken(
                username, password);*/

        // Allow subclasses to set the "details" property
//        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(passwordParameter);
    }

    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(usernameParameter);
    }

    protected String obtainTRoletype(HttpServletRequest request) {
        return request.getParameter(roletypeParameter);
    }

    protected void setDetails(HttpServletRequest request,
                              AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setRoletypeParameter(String roletypeParameter) {
        Assert.hasText(roletypeParameter, "Authtype parameter must not be empty or null");
        this.roletypeParameter = roletypeParameter;
    }

    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.usernameParameter = usernameParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getUsernameParameter() {
        return usernameParameter;
    }

    public final String getPasswordParameter() {
        return passwordParameter;
    }
    public final String getRoletypeParameter() {
        return roletypeParameter;
    }

}