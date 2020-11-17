/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.ItemPedido;
import model.Pedido;

/**
 *
 * @author luiz
 */
public class PedidoDao {
    
    
    
    
     public Pedido instanciarPedido (ResultSet rs) throws SQLException {
        Pedido p = new Pedido (rs.getInt("id"),
                null,
                rs.getFloat("precoTotal")
        );
        return p;
    }
    
}
