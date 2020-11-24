/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import DAO.ProdutoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import model.Produto;

/**
 *
 * @author luiz
 */
public class ManterItemPedidoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, ClassNotFoundException {

        String acao = request.getParameter("acao");
        if (acao.equals("confirmarOperacao")) {
            confirmarOperacao(request, response);
        } else {
            if (acao.equals("prepararOperacao")) {
                prepararOperacao(request, response);
            }
        }
    }

    private void prepararOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            request.setAttribute("produtos", Produto.obterProdutos());
            if (!operacao.equals("Incluir")) {
                int idItemPedido = Integer.parseInt(request.getParameter("id"));
                ItemPedido itemPedido = ItemPedido.obterItemPedido(idItemPedido);
                request.setAttribute("itemVenda", itemPedido);
            }
            RequestDispatcher view = request.getRequestDispatcher("/inserir/inserirItemPedido.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
            throw e;
        } catch (IOException e) {
            throw new ServletException(e);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, ClassNotFoundException {
        String operacao = request.getParameter("operacao");
        
        float subtotal = Float.parseFloat(request.getParameter("txtSubtotal"));
        int quantidade = Integer.parseInt(request.getParameter("txtQuantidade"));
        int idProduto = Integer.parseInt(request.getParameter("slProduto"));
        String status = request.getParameter("txtStatus");
        
        try {
             Produto produto = null;
            if (idProduto != 0) {
                produto = Produto.obterProduto(idProduto);
            }
            
            
           ItemPedido itemPedido = new ItemPedido();
           itemPedido.setSubtotal(subtotal)
                   .setQuantidade(quantidade)
                   .setProduto(produto);
                             
                   
            if (operacao.equals("Incluir")) {
                itemPedido.gravar();
                
                ProdutoDao produtoDao = new ProdutoDao();  
                
                int qtdAtual = itemPedido.abaterEstoque(quantidade, idProduto);
                
                produtoDao.atualizarEstoqueAtual(qtdAtual, idProduto);  
                
            } else {
                if (operacao.equals("Editar")) {
                    itemPedido.alterar();
                } else {
                    if (operacao.equals("Excluir")) {
                        itemPedido.excluir();
                    }
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaItemPedidoController");
            view.forward(request, response);
        } catch (IOException e) {
            throw new ServletException(e);
        } catch (ServletException e) {
            throw e;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ManterItemPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterItemPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ManterItemPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterItemPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
