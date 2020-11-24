/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import DAO.PedidoDao;
import DAO.ProdutoDao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author luiz
 */
public class Pedido {
    
    private Integer id;
    private float precoTotal;
    private String status;
    private int idProduto;
    private int idCliente;
    
    private Produto produto;
    private Cliente cliente;
    
    public Pedido(){}
   
    public Integer getId() {
        return id;
    }

    public Pedido setId(Integer id) {
        this.id = id;
        return this;
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    public Pedido setPrecoTotal(float precoTotal) {
        this.precoTotal = precoTotal;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Pedido setStatus(String status) {
        this.status = status;
        return this;
    }

    public Produto getProduto() {
        return produto;
    }

    public Pedido setProduto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public Pedido setIdProduto(int idProduto) {
        this.idProduto = idProduto;
        return this;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public Pedido setIdCliente(int idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pedido setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public static Pedido obterPedido(int idPedido) throws ClassNotFoundException, SQLException {
        return PedidoDao.getInstancia().obterPedido(idPedido);
    }

    public static List<Pedido> obterPedidos() throws ClassNotFoundException, SQLException {
        return PedidoDao.getInstancia().obterPedidos();
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        PedidoDao.getInstancia().gravarPedido(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException {
        PedidoDao.getInstancia().alterar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        PedidoDao.getInstancia().excluir(this);
    }
    
}
