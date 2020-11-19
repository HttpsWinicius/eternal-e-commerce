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
import model.Cliente;
import model.ItemPedido;

/**
 *
 * @author luiz
 */
public class ItemPedidoDao extends DAO{
    public static ItemPedidoDao instancia = new ItemPedidoDao();
    
    private Statement stmt;
    
    public static ItemPedidoDao getInstancia(){
        return instancia;
    }
    
     private ItemPedidoDao(){}
     
     public void gravarItemPedido(ItemPedido itemPedido) throws SQLException, ClassNotFoundException{
         Connection conexao = null;
         PreparedStatement stmt = null;
         
        try {
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("INSERT INTO item_venda (id, id_produto, subtotal) VALUES (?,?,?)");
            stmt.setInt(1, itemPedido.getId());
            if (itemPedido.getProduto() == null) {
                stmt.setNull(2, Types.INTEGER);
            } else {
                stmt.setInt(2, itemPedido.getProduto().getId());
            }
            stmt.setFloat(3,itemPedido.getSubtotal());
          
            stmt.executeUpdate();
            
        } finally{
            fecharConexao(conexao, stmt);
        } 
    }
     
     public ItemPedido instanciarItemPedido(ResultSet rs) throws SQLException {
        ItemPedido item = new ItemPedido(
                rs.getInt("id"),
                rs.getFloat("subtotal"),
                null
        );
        return item;
    }
     
}
