/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cliente;
import model.ItemPedido;

/**
 *
 * @author luiz
 */
public class ItemPedidoDao {
    
    
    
    
    
    
    
    
    public ItemPedido instanciarItemPedido (ResultSet rs) throws SQLException {
        ItemPedido ip = new ItemPedido(rs.getInt("id"),
                rs.getFloat("subtotal"),
                null
        );
        return ip;
    }
    
}
