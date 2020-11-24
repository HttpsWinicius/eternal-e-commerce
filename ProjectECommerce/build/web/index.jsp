<%-- 
    Document   : index
    Created on : 10/11/2020, 22:34:40
    Author     : luiz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bem vindo ao EternalEcommerce</h1>
        
        <a href="inserir/inserirCliente.jsp">Pesquisa Cliente</a>
        <br>
        <a href="inserir/inserirProduto.jsp">Pesquisa Produto</a>
        <br>
        <a href="inserir/inserirItemPedido.jsp">Pesquisa Item Pedido</a>
        <br>
        <a href="RealizarPedidoController?acao=preparar">Pesquisa Pedido</a>
    </body>
</html>
