package model;

import DAO.ProdutoDao;
import java.sql.SQLException;
import java.util.List;

public class Produto {
    
    private Integer id;
    private String descricao;
    private float preco;

    public Produto(){}

    public Produto(Integer id, String descricao, float preco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
    } 
    
    public Integer getId() {
        return id;
    }

    public Produto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Produto setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public float getPreco() {
        return preco;   
    }

    public Produto setPreco(float preco) {
        this.preco = preco;
        return this;
    }

    public static Produto obterProduto(int codProduto) throws ClassNotFoundException, SQLException {
        return ProdutoDao.getInstancia().obterProduto(codProduto);
    }

    public static List<Produto> obterProdutos() throws ClassNotFoundException, SQLException {
        return ProdutoDao.getInstancia().obterProdutos();
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        ProdutoDao.getInstancia().gravarProduto(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException {
        ProdutoDao.getInstancia().alterar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        ProdutoDao.getInstancia().excluir(this);
    }
    
    
    
    
          
}
