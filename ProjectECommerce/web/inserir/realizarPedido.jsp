<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Controle Vendas</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <script>
            $(document).ready(function() {

                adicionar = function() {

                    if ($("#quantidade").val() != 0) {
                        var valorTotal = parseFloat($("#valorTotal").text());
                        var linha = $("<tr>");
                        var colunas = "";
                        var opcao = $("#produto").find("option:selected");
                        valorTotal = valorTotal + (opcao.data('preco') * $("#quantidade").val());

                        colunas += '<td style="visibility:hidden;">' + $("#produto").val() + '</td>';
                        colunas += '<td>' + opcao.data('nome') + '</td>';
                        colunas += '<td data-quantidade=' + $("#quantidade").val() + '>' + $("#quantidade").val() + '</td>';
                        colunas += '<td data-preco=' + opcao.data('preco') + '>' + opcao.data('preco') + '</td>';
                        colunas += '<td><button type="button" class="btn btn-danger btn-xs" onclick="remover(this)">Excluir <span class="glyphicon glyphicon-trash"></span></button></td>';
                        colunas += '</tr>';
                        linha.append(colunas);
                        $("#tabela").append(linha);
                        $("#quantidade").val(null);
                        $("#valorTotal").text(valorTotal);
                    }
                };

                remover = function(item) {
                    var valorTotal = parseFloat($("#valorTotal").text());

                    var tr = $(item).closest('tr');
                    var quantidade = tr.find('td[data-quantidade]').data('quantidade');
                    var preco = tr.find('td[data-preco]').data('preco');
                    valorTotal = valorTotal - (quantidade * preco);
                    $("#valorTotal").text(valorTotal);
                    tr.remove();
                }

                $("#cliente").change(function() {
                    $("#tabela tr").remove();
                    var linha = $("<tr>");
                    var colunas = "";
                    colunas += '<td style="visibility:hidden;">Código</td>';
                    colunas += '<td>Produto</td>';
                    colunas += '<td>Quantidade</td>';
                    colunas += '<td>Preço Unit.</td>';
                    colunas += '<td>Ação</td>';
                    colunas += '</tr>';
                    linha.append(colunas);
                    $("#tabela").append(linha);
                    $("#valorTotal").text(0);
                });
            });
        </script>

    </head>

    <body>
        <div class="container">
            <form action="RealizarPedidoController?acao=confirmar">

                <div class="row">
                    <div class="col-sm-12 page-header">
                        <h1 class="header">Realizar Pedido</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="cliente">Cliente</label>
                            <select class="form-control" id="cliente" name="cliente">
                                <c:forEach items="${clientes}" var="umCliente">
                                    <option value="${umCliente.id}">${umCliente.nome}</option>  
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="produto">Produto</label>
                            <select class="form-control" id="produto" name="produto">
                                <c:forEach items="${produtos}" var="umProduto">
                                    <option value="${umProduto.id}" 
                                            data-nome="${umProduto.descricao}"
                                            data-preco="${umProduto.preco}">${umProduto.descricao}</option>  
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">   
                            <label>Status</label><br>
                            <input type="text" name="txtStatus" required class="form-control" value="Pedido Realizado">
                        </div>      
                        <div class="form-group">
                            <label for="quantidade">Quantidade</label>
                            <input type="number" id="quantidade" name="quantidade" class="form-control"><br><br>
                        </div>
                        <button onclick="adicionar()" type="button" class="btn btn-primary">Adicionar Item</button>
                        <br><br>
                        <center>
                            <button type="submit" class="btn btn-success">Finalizar Venda</button>
                        </center>
                    </div>
                    <div class="col-sm-6">
                        <div class="row">
                            <h2 align="right">
                                Valor Total - R$
                                <label id="valorTotal" name="valorTotal">0</label>
                            </h2>
                        </div>
                        <div class="row">
                            <table class="table table-striped table-bordered table-hover table-condensed" id="tabela">
                                <tr>
                                    <td style="visibility:hidden;">Código</td>
                                    <td>Produto</td>
                                    <td>Quantidade</td>
                                    <td>Preço Unit.</td>
                                    <td>Ação</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </body>

</html>