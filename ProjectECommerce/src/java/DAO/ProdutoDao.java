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
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Produto;

/**
 *
 * @author luiz
 */
public class ProdutoDao extends DAO {

    private static ProdutoDao instancia = new ProdutoDao();
    private Statement stmt;

    public ProdutoDao() {}

    public static ProdutoDao getInstancia() {
        return instancia;
    }

    public void gravarProduto(Produto produto) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("INSERT INTO produto (descricao, preco, estoque_atual) VALUES (?,?,?);");
            stmt.setString(1, produto.getDescricao());
            stmt.setFloat(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoqueAtual());
            stmt.executeUpdate();
        } finally {
            fecharConexao(conexao, stmt);
        }
    }

    public Produto instanciarProduto(ResultSet rs) throws SQLException {

        Produto produto = new Produto();
        produto.setDescricao(rs.getString("descricao"))
                .setEstoqueAtual(rs.getInt("estoque_atual"))
                .setPreco(rs.getFloat("preco"));

        return produto;
    }

    public List<Produto> obterProdutos() throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement st = null;

        List<Produto> produtos = new ArrayList<Produto>();
        Produto produto = null;

        try {
            conexao = BD.getInstancia().getConexao();
            st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from produto");

            while (rs.next()) {
                produto = instanciarProduto(rs);
                produtos.add(produto);
            }
        } finally {
            fecharConexao(conexao, st);
        }
        return produtos;

    }

    public Produto obterProduto(int idProduto) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement st = null;

        Produto produto = null;

        try {
            conexao = BD.getInstancia().getConexao();
            st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from produto where id = ?;");

            rs.first();
            produto = instanciarProduto(rs);

        } finally {
            fecharConexao(conexao, st);
        }
        return produto;
    }

    public void alterar(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("UPDATE produto SET descricao = ?, preco=?, estoque_atual=? WHERE id = ?");
            stmt.setString(1, produto.getDescricao());
            stmt.setFloat(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoqueAtual());
            stmt.setInt(4, produto.getId());
            
            stmt.executeUpdate();

        } finally {
            fecharConexao(conexao, stmt);
        }
    }
    
     public void atualizarEstoqueAtual(int qtdAtual, int idProduto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("UPDATE produto SET estoque_atual=? WHERE id = ?");
 
            stmt.setInt(1, qtdAtual);
            stmt.setInt(2, idProduto);

            stmt.executeUpdate();

        } finally {
            fecharConexao(conexao, stmt);
        }
    }

    public void excluir(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("DELETE FROM produto WHERE id = ?");

            stmt.setInt(1, produto.getId());

            stmt.executeUpdate();

        } finally {
            fecharConexao(conexao, stmt);
        }
    }
}
