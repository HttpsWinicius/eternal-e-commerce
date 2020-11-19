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
import model.Cliente;
import model.Produto;

/**
 *
 * @author luiz
 */
public class ProdutoDao extends DAO{
        
    private static ProdutoDao instancia = new ProdutoDao();
    private Statement stmt;

    private ProdutoDao() {}

    public static ProdutoDao getInstancia() {
        return instancia;
    }
    
    public void gravarProduto(Produto produto) throws ClassNotFoundException, SQLException{
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try{
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("INSERT INTO produto (descricao, preco, status) VALUES (?,?,?);"); 
            //stmt.setInt(1, produto.getId());
            stmt.setString(1, produto.getDescricao());
            stmt.setFloat(2, produto.getPreco());
            stmt.setString(3, produto.getStatus());
           
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
