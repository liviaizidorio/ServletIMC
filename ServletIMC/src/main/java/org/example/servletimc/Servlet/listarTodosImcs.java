package org.example.servletimc.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletimc.DAO.imcDAO;

import java.io.*;
import java.sql.*;

@WebServlet("/imc/listar")
public class listarTodosImcs extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            imcDAO imcDAO = new imcDAO();
            ResultSet rs = imcDAO.consultarIMCS();
            request.setAttribute("listaIMC", rs);
            request.getRequestDispatcher("/pages/listarTodosImcs.jsp").forward(request, response);
        }catch (SQLException e){
            response.sendRedirect("pages/erroBD.jsp");
        }
//        catch (SQLException e) {
//            e.printStackTrace();  // Log da exceção no console
//            request.setAttribute("erro", e.getMessage());  // Passando a mensagem de erro
//            request.getRequestDispatcher("/pages/erroBD.jsp").forward(request, response);
//        }
    }
}

