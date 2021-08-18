package com.amr.project.security.handler;

import com.amr.project.model.entity.User;
import com.amr.project.security.oauth.CustomOAuth2User;
import com.amr.project.service.abstracts.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;

    public OAuth2LoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getName();
        User user = userService.findByEmail(email).orElse(null);

        if(user == null) {
            response.sendRedirect("/registration");
        } else {
            response.sendRedirect("/");
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
