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
public class Cliente {
    
    private Integer id;
    private String nome;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String cep;
    
    public Cliente(){}

    public Cliente(Integer id, String nome, String logradouro, String bairro, String cidade, String cep) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }
    
    public Integer getId() {
        return id;
    }

    public Cliente setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Cliente setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Cliente setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public Cliente setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public Cliente setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getCep() {
        return cep;
    }

    public Cliente setCep(String cep) {
        this.cep = cep;
        return this;
    }
    
       
}
