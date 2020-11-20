/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.ItemPedido;

/**
 *
 * @author luiz
 */
public class ItemPedidoDao extends DAO {

    public static ItemPedidoDao instancia = new ItemPedidoDao();

    private Statement stmt;

    public static ItemPedidoDao getInstancia() {
        return instancia;
    }

    private ItemPedidoDao() {
    }

    public void gravarItemPedido(ItemPedido itemPedido) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("INSERT INTO item_venda (id, id_produto, subtotal, quantidade) VALUES (?,?,?,?)");
            stmt.setInt(1, itemPedido.getId());
            if (itemPedido.getProduto() == null) {
                stmt.setNull(1, Types.INTEGER);
            } else {
                stmt.setInt(1, itemPedido.getProduto().getId());
            }
            stmt.setFloat(2,itemPedido.getSubtotal());         
            stmt.setFloat(3, itemPedido.getSubtotal());
            stmt.setFloat(4, itemPedido.getQuantidade());
            stmt.executeUpdate();

        } finally {
            fecharConexao(conexao, stmt);
        }
    }
  
     
    public ItemPedido instanciarItemPedido (ResultSet rs) throws SQLException {
        
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(rs.getInt("id"))
                .setSubtotal(rs.getFloat("subtotal"))
                .setProduto(null);
                
        return itemPedido;
    }

    public List<ItemPedido> obterItensPedidos() throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement st = null;

        List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
        ItemPedido itemPedido = null;

        try {
            conexao = BD.getInstancia().getConexao();
            st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from item_pedido");

            while (rs.next()) {
                itemPedido = instanciarItemPedido(rs);
                itensPedido.add(itemPedido);
            }
        } finally {
            fecharConexao(conexao, stmt);
        }
        return itensPedido;
    }

    public ItemPedido obterItemPedido(int idItemPedido) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement st = null;

        ItemPedido itemPedido = null;

        try {
            conexao = BD.getInstancia().getConexao();
            st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from item_pedido where id = ?;");
            rs.first();

            itemPedido = instanciarItemPedido(rs);
        } finally {
            fecharConexao(conexao, stmt);
        }
        return itemPedido;
    }

    public void excluir(ItemPedido itemPedido) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("DELETE FROM item_pedido WHERE id=?");

            stmt.setInt(1, itemPedido.getId());

            stmt.executeUpdate();

        } finally {
            fecharConexao(conexao, stmt);
        }
    }

    public void alterar(ItemPedido itemPedido) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("UPDATE item_pedido  SET id_produto=?, subtotal=?, quantidade=? WHERE id=?");
            stmt.setInt(1, itemPedido.getProduto().getId());
            stmt.setFloat(2, itemPedido.getSubtotal());
            stmt.setFloat(3, itemPedido.getQuantidade());

            stmt.setInt(4, itemPedido.getId());

            stmt.executeUpdate();

        } finally {
            fecharConexao(conexao, stmt);
        }
    }
}
