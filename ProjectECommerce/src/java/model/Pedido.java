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
public class Pedido {
    
    private Integer id;
    private ItemPedido itemPedido;
    private float precoTotal;

    public Pedido(){}
    
    public Integer getId() {
        return id;
    }

    public Pedido setId(Integer id) {
        this.id = id;
        return this;
    }

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public Pedido setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
        return this;
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    public Pedido setPrecoTotal(float precoTotal) {
        this.precoTotal = precoTotal;
        return this;
    }

    public Pedido(Integer id, ItemPedido itemPedido, float precoTotal) {
        this.id = id;
        this.itemPedido = itemPedido;
        this.precoTotal = precoTotal;
    }
    
    
    
}
