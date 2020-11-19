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
    
    public void gravarProduto(Pedido pedido) throws ClassNotFoundException, SQLException{
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try{
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("INSERT INTO pedido (id_item_pedido, precototal) VALUES (?,?);"); 
            //stmt.setInt(1, pedido.getId());
            stmt.setInt(1, pedido.getItemPedido().getId());
            stmt.setFloat(2, pedido.getPrecoTotal());
          
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
    
    
    
}
