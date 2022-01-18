package by.itacademy.javaenterprise.seledtsova.config.handler;

import by.itacademy.javaenterprise.seledtsova.dto.UserLogin;
import by.itacademy.javaenterprise.seledtsova.entity.RoleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebUrlSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebUrlSuccessHandler.class);

    private final Map<RoleType, String> roleTargetUrlMap = new HashMap<>() {{
        put(RoleType.ROLE_ADMINISTRATOR, "/users/allusers");
        put(RoleType.ROLE_SALE_USER, "/orders/show");
        put(RoleType.ROLE_CUSTOMER_USER, "/items");
    }};

    private String getRedirectionUrl(RoleType name) {
        return roleTargetUrlMap.get(name);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        UserLogin userLogin = (UserLogin) authentication.getPrincipal();
        RoleType role = userLogin.getUser().getRole().getName();
        logger.info(userLogin.getUser().getRole().getName() + " got is connected ");
        String redirectionUrl = getRedirectionUrl(role);
        response.sendRedirect(redirectionUrl);
    }
}
