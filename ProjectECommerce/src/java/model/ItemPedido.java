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
public class ItemPedido {
    
    private Integer id;
    private float subtotal;
    private Produto produto;

    public ItemPedido(){}

    public ItemPedido(Integer id, float subtotal, Produto produto) {
        this.id = id;
        this.subtotal = subtotal;
        this.produto = produto;
    }
    
    public Integer getId() {
        return id;
    }

    public ItemPedido setId(Integer id) {
        this.id = id;
        return this;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public ItemPedido setSubtotal(float subtotal) {
        this.subtotal = subtotal;
        return this;
    }

    public Produto getProduto() {
        return produto;
    }

    public ItemPedido setProduto(Produto produto) {
        this.produto = produto;
        return this;
    }
    
    
}
