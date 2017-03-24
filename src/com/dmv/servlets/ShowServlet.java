package com.dmv.servlets;

import com.dmv.dao.DaoException;
import com.dmv.dto.AudioDTO;
import com.dmv.dto.PhotoDTO;
import com.dmv.dto.VideoDTO;
import com.dmv.sql.AudioDAO;
import com.dmv.sql.PhotoDAO;
import com.dmv.sql.VideoDAO;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ShowServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (Objects.equals(request.getParameter("p"), "audio")) {
            AudioDAO audioDAO = (AudioDAO) request.getSession().getAttribute("audioDAO");
            List<AudioDTO> audioDTOList = null;
            try {
                audioDTOList = audioDAO.getAll();
            } catch (DaoException e) {
                e.printStackTrace();
            }
            request.setAttribute("dtoList", audioDTOList);

        } else if (Objects.equals(request.getParameter("p"), "video")) {
            VideoDAO videoDAO = (VideoDAO) request.getSession().getAttribute("videoDAO");
            List<VideoDTO> videoDTOList = null;
            try {
                videoDTOList = videoDAO.getAll();
            } catch (DaoException e) {
                e.printStackTrace();
            }
            request.setAttribute("dtoList", videoDTOList);

        } else if (Objects.equals(request.getParameter("p"), "photo")) {
            PhotoDAO photoDAO = (PhotoDAO) request.getSession().getAttribute("photoDAO");
            List<PhotoDTO> photoDTOList = null;
            try {
                photoDTOList = photoDAO.getAll();
            } catch (DaoException e) {
                e.printStackTrace();
            }
            request.setAttribute("dtoList", photoDTOList);

        } else if (Objects.equals(request.getParameter("action"), "ЗАПИСАТЬСЯ")) {
            try {
                Email email = new SimpleEmail();
                email.setHostName("smtp.gmail.com");
                email.setSmtpPort(587);
                email.addTo("jeffersmv@mail.com");
                email.setFrom(request.getParameter("e-mail"));
                email.setSubject("DMV");
                email.setMsg("Имя: " + request.getParameter("firstName") +", телефон: " +request.getParameter("phone"));
                email.send();
            } catch (EmailException e) {
                e.printStackTrace();
            }


        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);
        System.out.println(request.getParameter("p"));
    }

}
