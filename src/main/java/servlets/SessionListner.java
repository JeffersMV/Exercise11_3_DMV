package com.dmv.servlets;

import com.dmv.dao.DaoFactory;
import com.dmv.sql.AudioDAO;
import com.dmv.sql.PhotoDAO;
import com.dmv.sql.VideoDAO;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.Connection;

public class SessionListner implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        try {
            new DaoFactory();
            Connection connection = DaoFactory.getConnection();
            AudioDAO audioDAO = new AudioDAO(connection);
            PhotoDAO photoDAO = new PhotoDAO(connection);
            VideoDAO videoDAO = new VideoDAO(connection);
            System.out.println("[MySessionListener] Session created: "+session);
            session.setAttribute("audioDAO", audioDAO);
            session.setAttribute("photoDAO", photoDAO);
            session.setAttribute("videoDAO", videoDAO);
        } catch (Exception e) {
            System.out.println("[MySessionListener] Error setting session attribute: " + e.getMessage());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        System.out.println("[MySessionListener] Session invalidated: "+session);
    }
}
