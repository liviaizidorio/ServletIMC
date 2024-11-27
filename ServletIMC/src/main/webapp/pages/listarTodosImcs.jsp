<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <title>Listar IMCs</title>
<%--    <link rel="stylesheet" type="text/css" href="../css/style.css">--%>
</head>
<body>
<h1>IMCs Cadastrados</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Cpf</th>
        <th>Altura</th>
        <th>Peso</th>
        <th>Imc</th>
    </tr>

    <% ResultSet IMCRs = (ResultSet) request.getAttribute("listaIMC");
        if (IMCRs.next()){
            do {%>
    <tr>
        <td><%=IMCRs.getInt("id")%></td>
        <td><%=IMCRs.getString("nome")%></td>
        <td><%=IMCRs.getString("descricao")%></td>
        <td><%=IMCRs.getDouble("preco")%></td>
        <td><%=IMCRs.getInt("quantidade")%></td>
    </tr>

    <%}while (IMCRs.next());
    }%>
    <%--Mostrar os registros da tabela IMC--%>

</table>
</body>
</html>
