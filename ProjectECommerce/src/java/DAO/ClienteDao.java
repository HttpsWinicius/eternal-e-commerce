package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteDao extends DAO{
    
    private static ClienteDao instancia = new ClienteDao();
    private Statement stmt;

    private ClienteDao() {}

    public static ClienteDao getInstancia() {
        return instancia;
    }
    
    public void gravarCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("INSERT INTO cliente (id, nome, logradouro, bairro, cidade, cep) VALUES (?,?,?,?,?,?)");
            stmt.setInt(1, cliente.getId());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getLogradouro());
            stmt.setString(4, cliente.getBairro());
            stmt.setString(5, cliente.getCidade());
            stmt.setString(5, cliente.getCep());
            
            stmt.executeUpdate();

        } finally {
            fecharConexao(conexao, stmt);
        }
    }
    
        public Cliente instanciarCliente (ResultSet rs) throws SQLException {
        
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"))
                .setNome(rs.getString("nome"))
                .setLogradouro(rs.getString("logradouro"))
                .setBairro(rs.getString("bairro"))
                .setCidade(rs.getString("cidade"))
                .setCep(rs.getString("cep"));
  
                
        return cliente;
    }
     
     public List<Cliente> obterClientes() throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement st = null;

        List<Cliente> clientes = new ArrayList<Cliente>();
        Cliente cliente = null;

        try {
            conexao = BD.getInstancia().getConexao();
            st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from cliente;");

            while (rs.next()) {
                cliente = instanciarCliente(rs);
                clientes.add(cliente);

            }
        } finally {
            fecharConexao(conexao, st);
        }
        return clientes;
    }
     
      public Cliente obterCliente(int codCliente) throws ClassNotFoundException, SQLException {

        Connection conexao = null;
        Statement st = null;

        Cliente cliente = null;

        try {
            conexao = BD.getInstancia().getConexao();
            st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from clientes where id = ?");

            rs.first();
            cliente = instanciarCliente(rs);
        } finally {
            fecharConexao(conexao, st);
        }
        return cliente;
    }
     
     public void alterar(Cliente cliente) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("UPDATE cliente SET nome=?, logradouro=?, bairro=?, cidade=?, cep=? WHERE id = ?");
            stmt.setInt(1, cliente.getId());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getLogradouro());
            stmt.setString(4, cliente.getBairro());
            stmt.setString(5, cliente.getCidade());
            stmt.setString(5, cliente.getCep());

            stmt.executeUpdate();

        } finally {
            fecharConexao(conexao, stmt);
        }
    }
     
     public void excluir(Cliente cliente) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = BD.getInstancia().getConexao();
            stmt = conexao.prepareStatement("DELETE FROM cliente WHERE id = ?");

            stmt.setInt(1, cliente.getId());

            stmt.executeUpdate();

        } finally {
            fecharConexao(conexao, stmt);
        }
    }
}
