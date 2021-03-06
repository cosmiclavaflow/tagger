package com.music.tagger.security;

import com.music.tagger.persistence.entity.User;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("myAuthenticationSuccessHandler")
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MyCustomLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Setter(onMethod=@__({@Autowired}))
    private ActiveUserStore activeUserStore;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        addWelcomeCookie(gerUserName(authentication), response);
        redirectStrategy.sendRedirect(request, response, "/homepage"/*"/homepage.html?user=" + authentication.getName()*/);

        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.setMaxInactiveInterval(30 * 60);
            String username;
            if (authentication.getPrincipal() instanceof User) {
                username = ((User)authentication.getPrincipal()).getEmail();
            }
            else {
                username = authentication.getName();
            }

            LoggedUser user = new LoggedUser(username, activeUserStore);
            session.setAttribute("user", user);
        }
        clearAuthenticationAttributes(request);
    }

    private String gerUserName(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getFirstName();
    }

    private void addWelcomeCookie(String user, HttpServletResponse response) {
        Cookie welcomeCookie = getWelcomeCookie(user);
        response.addCookie(welcomeCookie);
    }

    private Cookie getWelcomeCookie(String user) {
        Cookie welcomeCookie = new Cookie("welcome", user);
        welcomeCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
        return welcomeCookie;
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
