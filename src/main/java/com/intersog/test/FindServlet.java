package com.intersog.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: yurec
 * Date: 06.11.12
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
public class FindServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {
            double distanceValue = Helper.findDistanceForCities(request.getParameter("city1"),
                    request.getParameter("city2"));
            out.append(String.valueOf(distanceValue));
        } catch (Exception e) {
            out.append(e.getMessage());
            response.setStatus(500);
        }
    }
}
