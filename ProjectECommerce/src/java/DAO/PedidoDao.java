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
import model.ItemPedido;
import model.Pedido;
import model.Produto;

/**
 *
 * @author luiz
 */
public class PedidoDao extends DAO{
    
    private static PedidoDao instancia = new PedidoDao();
    private Statement stmt;

    private PedidoDao() {}

    public static PedidoDao getInstancia() {
        return instancia;
    }
    
    public void gravarPedido(Pedido pedido) throws ClassNotFoundException, SQLException{
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try{
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("INSERT INTO pedido (id_item_pedido, precototal) VALUES (?,?);"); 
            //stmt.setInt(1, pedido.getId());
            stmt.setInt(1, pedido.getItemPedido().getId());
            stmt.setFloat(2, pedido.getPrecoTotal());
            stmt = conexao.prepareStatement("INSERT INTO pedido (id, id_item_pedido, precototal) VALUES (?,?,?);"); 
            stmt.setInt(1, pedido.getId());
            if (pedido.getItemPedido() == null) {
                stmt.setNull(2, Types.INTEGER);
            } else {
                stmt.setInt(2, pedido.getItemPedido().getId());
            }
            stmt.setFloat(3, pedido.getPrecoTotal());
        
            stmt.executeUpdate();
        }finally{
            fecharConexao(conexao, stmt);
        }
    }
    
    
    
    public Pedido instanciarPedido(ResultSet rs) throws SQLException {
        
        Pedido pedido = new Pedido();
        pedido.setId(rs.getInt("id"))
                .setPrecoTotal(rs.getFloat("precototal"))
                .setItemPedido(null);
        
        return pedido;
    }
    
    public List<Pedido> obterPedidos() throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement st = null;

        List<Pedido> pedidos = new ArrayList<Pedido>();
        Pedido pedido = null;

        try {
            conexao = BD.getInstancia().getConexao();
            st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from pedido");

            while (rs.next()) {
                pedido = instanciarPedido(rs);
                pedidos.add(pedido);
            }
        } finally {
            fecharConexao(conexao, st);
        }
        return pedidos;

    }

    public Pedido obterPedido(int idPedido) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement st = null;

        Pedido pedido = null;

        try {
            conexao = BD.getInstancia().getConexao();
            st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from pedido where id = ?;");

            rs.first();
            pedido = instanciarPedido(rs);

        } finally {
            fecharConexao(conexao, st);
        }
        return pedido;
    }
    
    public void alterar(Pedido pedido) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("UPDATE pedido SET id_item_pedido=?, precototal=? WHERE id = ?");
            stmt.setInt(1, pedido.getItemPedido().getId());
            stmt.setFloat(2, pedido.getPrecoTotal());
            stmt.setFloat(3, pedido.getId());

            stmt.executeUpdate();

        } finally {
            fecharConexao(conexao, stmt);
        }
    }

    public void excluir(Pedido pedido) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("DELETE FROM pedido WHERE id = ?");

            stmt.setInt(1, pedido.getId());

            stmt.executeUpdate();

        } finally {
            fecharConexao(conexao, stmt);
        }
    }
    
}
