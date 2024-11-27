<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Resultado IMC</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>Resultado do IMC</h1>
<%--<h2>O seu IMC é:</h2>--%>
<%
    // Verifica se o atributo "imc" existe
    Object imcObj = request.getAttribute("imc");
    if (imcObj != null) {
        Double imcA = (Double) imcObj;
        out.print(String.format("O seu imc é: %.2f", imcA));
    }
    %>
<img src="img.jpeg" alt="">
<% if(imcObj == null) {
        out.print("IMC não calculado ou ocorreu um erro.");
    }
%>
</body>
</html>
