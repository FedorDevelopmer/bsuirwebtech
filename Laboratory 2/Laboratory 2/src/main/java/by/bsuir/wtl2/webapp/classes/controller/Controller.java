package by.bsuir.wtl2.webapp.classes.controller;

import by.bsuir.wtl2.webapp.classes.controller.logic.CommandHandler;
import by.bsuir.wtl2.webapp.classes.controller.logic.ICommand;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageName;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageNames;
import by.bsuir.wtl2.webapp.classes.dao.connection.ConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Controller extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        try{
            ConnectionPool.getInstance().initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }

    private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException,
                                                                                         IOException {
        ICommand command;
        PageName resultRedirectPage = PageNames.MAIN_PAGE;
        CommandHandler handler = CommandHandler.getInstance();
        if(!(request.getParameter("command") == null)) {
            if(request.getParameter("command").equals("redirect")){
                String redirectPage = request.getParameter("page");
                String isRedirect = request.getParameter("redirect");
                request.getSession().setAttribute("input_error",null);
                if(isRedirect.equals("true")){
                    response.sendRedirect(redirectPage);
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(redirectPage);
                    dispatcher.forward(request,response);
                }
                return;
            }
            command = handler.getCommandByName(request.getParameter("command"));
            resultRedirectPage = command.completeCommand(request,response,getServletContext());
        }else {
            command = handler.getCommandByName("main");
            resultRedirectPage = command.completeCommand(request,response,getServletContext());
        }
        if(resultRedirectPage.isRedirect()){
            response.sendRedirect(resultRedirectPage.getName());
        }else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(resultRedirectPage.getName());
            dispatcher.forward(request,response);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try{
            ConnectionPool.getInstance().destroy();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
