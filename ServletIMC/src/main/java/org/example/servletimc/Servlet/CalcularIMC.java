package org.example.servletimc.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "calcular-imc", value = "/calcular-imc")
public class CalcularIMC extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String a = request.getParameter("altura");
            String p = request.getParameter("peso");

            double altura = Double.parseDouble(a);
            double peso = Double.parseDouble(p);

            double imc = peso/(altura * altura);

            // Adiciona o IMC como atributo para ser usado na JSP
            request.setAttribute("imc", imc);

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);


        } catch (NumberFormatException e) {
            // Redireciona para uma página de erro
            response.sendRedirect(request.getContextPath() + "/erro.jsp");
        }
    }
}
