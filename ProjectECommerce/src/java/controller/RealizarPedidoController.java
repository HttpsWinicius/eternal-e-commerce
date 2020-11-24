/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

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

public class RealizarPedidoController extends HttpServlet {

      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        if (acao.equals("preparar")) {
            preparar(request, response);
        } else {
            if (acao.equals("confirmar")) {
                confirmarOperacao(request, response);
            }
        }
    }

    public void preparar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            
            request.setAttribute("produtos", Produto.obterProdutos());
            request.setAttribute("clientes", Cliente.obterClientes());
      
            RequestDispatcher view = request.getRequestDispatcher("inserir/realizarPedido.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
            throw e;
        } catch (IOException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RealizarPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RealizarPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String operacao = request.getParameter("operacao");
        
        //int idPedido = Integer.parseInt(request.getParameter("txtCodigo"));
        float subtotal = Float.parseFloat(request.getParameter("txtPrecoUnitario"));
        int quantidade = Integer.parseInt(request.getParameter("qtdProduto"));
        
        float precoTotal = Float.parseFloat(request.getParameter("txtPreco"));
        String status = request.getParameter("txtStatus");
        
        int idProduto = Integer.parseInt(request.getParameter("slProduto"));
        
        try{
            Produto produto = null;
            if (idProduto != 0) {
                produto = Produto.obterProduto(idProduto);
            }
            
            Pedido pedido = new Pedido();
            pedido.setPrecoTotal(precoTotal)
                    .setStatus(status)
                    .setProduto(produto);
            
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setSubtotal(subtotal)
                   .setQuantidade(quantidade)
                   .setProduto(produto);
            
             if(operacao.equals("Incluir")){
                 pedido.gravar();
             }else{
                 if(operacao.equals("Editar")){
                     pedido.alterar();
                 }else{
                     if(operacao.equals("Excluir")){
                         pedido.excluir();
                     }
                 }
             }
             RequestDispatcher view = request.getRequestDispatcher("PesquisaVendaController");
             view.forward(request, response);
         }catch(IOException e){
             throw new ServletException(e);
         }catch(SQLException e){
             throw new ServletException(e);
         }catch(ClassNotFoundException e){
             throw new ServletException(e);
         }catch(ServletException e){
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
        processRequest(request, response);
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
        processRequest(request, response);
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
