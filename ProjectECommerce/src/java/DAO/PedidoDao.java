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
    
    public void gravarProduto(Pedido pedido) throws ClassNotFoundException, SQLException{
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try{
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("INSERT INTO pedido (id, id_item_pedido, precototal) VALUES (?,?,?);"); 
            stmt.setInt(1, pedido.getId());
            stmt.setInt(2, pedido.getItemPedido().getId());
            stmt.setFloat(3, pedido.getPrecoTotal());
          
            stmt.executeUpdate();
        }finally{
            fecharConexao(conexao, stmt);
        }
    }
    
    public Produto instanciarProduto(ResultSet rs) throws SQLException {
        
        Produto produto = new Produto();
        produto.setDescricao(rs.getString("descricao"))
                .setPreco(rs.getFloat("preco"))
                .setStatus(rs.getString("status"))
                .setId(rs.getInt("id"));
        
        return produto;
    }
}
