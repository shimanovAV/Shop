package by.etc.shop.controller.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    private static final String LANGUAGE = "language";

    private static final String DEFAULT_LOCALE = "ru";

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        session.setAttribute(LANGUAGE, DEFAULT_LOCALE);
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent event){}

}
