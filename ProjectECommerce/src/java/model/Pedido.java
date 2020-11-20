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
    private ItemPedido itemPedido;
    private float precoTotal;

    public Pedido(){}
    
    public Integer getId() {
        return id;
    }

    public Pedido setId(Integer id) {
        this.id = id;
        return this;
    }

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public Pedido setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
        return this;
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    public Pedido setPrecoTotal(float precoTotal) {
        this.precoTotal = precoTotal;
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
