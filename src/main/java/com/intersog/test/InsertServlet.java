package com.intersog.test;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: yurec
 * Date: 06.11.12
 * Time: 14:40
 * To change this template use File | Settings | File Templates.
 */
public class InsertServlet extends javax.servlet.http.HttpServlet {

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

        Helper.insertDistanceForCities(request.getParameter("city1"), request.getParameter("city2"),
                Double.parseDouble(request.getParameter("distance")));

        PrintWriter out = response.getWriter();
        out.append("inserted");
    }
}
