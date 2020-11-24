/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.ItemPedidoDao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author luiz
 */
public class ItemPedido {

    private Integer id;
    private float subtotal;
    private float quantidade;

    private Produto produto;

    public ItemPedido() {
    }

    public int abaterEstoque(int quantidade, int idProduto) throws SQLException, ClassNotFoundException {
        produto.obterProduto(idProduto);

        int qtdEstoque = produto.getEstoqueAtual();

        int novoEstoque = quantidade - qtdEstoque;

        return novoEstoque;
    }

    public ItemPedido(float subtotal, float quantidade, Produto produto) {
        this.subtotal = subtotal;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public ItemPedido setQuantidade(float quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public ItemPedido setId(Integer id) {
        this.id = id;
        return this;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public ItemPedido setSubtotal(float subtotal) {
        this.subtotal = subtotal;
        return this;
    }

    public Produto getProduto() {
        return produto;
    }

    public ItemPedido setProduto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public static ItemPedido obterItemPedido(int idItemPedido) throws ClassNotFoundException, SQLException {
        return ItemPedidoDao.getInstancia().obterItemPedido(idItemPedido);
    }

    public static List<ItemPedido> obterItensPedidos() throws ClassNotFoundException, SQLException {
        return ItemPedidoDao.getInstancia().obterItensPedidos();
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        ItemPedidoDao.getInstancia().gravarItemPedido(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        ItemPedidoDao.getInstancia().excluir(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException {
        ItemPedidoDao.getInstancia().alterar(this);
    }

}
