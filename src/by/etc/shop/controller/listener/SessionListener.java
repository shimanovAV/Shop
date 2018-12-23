package by.etc.shop.controller.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

public class SessionListener implements HttpSessionListener {

    private static final String LANGUAGE = "language";

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        String currentLanguage = CurrentLanguage.LANGUAGE.getLanguage();
        session.setAttribute(LANGUAGE, currentLanguage);
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent event){}


}
