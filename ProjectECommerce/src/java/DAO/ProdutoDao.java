/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Pedido;
import model.Produto;

/**
 *
 * @author luiz
 */
public class ProdutoDao {
    
    
    
    
    
    
    
    
    
   public Produto instanciarProduto (ResultSet rs) throws SQLException {
        Produto p = new Produto (rs.getInt("id"),
                rs.getString("descricao"),
                rs.getFloat("preco")
        );
        return p;
    }
    
    
    
    
}
