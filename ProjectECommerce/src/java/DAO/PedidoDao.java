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
            stmt = conexao.prepareStatement("INSERT INTO pedido (precototal, status, id_produto, id_cliente) VALUES (?,?,?,?);"); 
            stmt.setFloat(1, pedido.getPrecoTotal());
            stmt.setString(2, pedido.getStatus());
            if (pedido.getProduto() == null) {
                stmt.setNull(3, Types.INTEGER);
            } else {
                stmt.setInt(3, pedido.getProduto().getId());
            }
            
            if (pedido.getCliente() == null) {
                stmt.setNull(4, Types.INTEGER);
            } else {
                stmt.setInt(4, pedido.getCliente().getId());
            }
            stmt.executeUpdate();
        }finally{
            fecharConexao(conexao, stmt);
        }
    }
    
    public Pedido instanciarPedido(ResultSet rs) throws SQLException {
        
        Pedido pedido = new Pedido();
        pedido.setPrecoTotal(rs.getFloat("precototal"))
                .setStatus(rs.getString("status"))
                .setIdProduto(rs.getInt("id_produto"))
                .setIdCliente(rs.getInt("id_cliente"))
                .setProduto(null)
                .setCliente(null);     
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
            stmt = conexao.prepareStatement("UPDATE pedido SET precototal=?, status=?, id_produto=?, id_cliente WHERE id = ?");
            stmt.setFloat(1, pedido.getPrecoTotal());
            stmt.setString(2, pedido.getStatus());
            stmt.setInt(3, pedido.getProduto().getId());
            stmt.setInt(4, pedido.getCliente().getId());
           
            stmt.setFloat(5, pedido.getId());

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
