/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author luiz
 */
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
}
