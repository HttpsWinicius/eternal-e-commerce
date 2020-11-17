package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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
            stmt = conexao.prepareStatement("INSERT INTO cliente (nome, logradouro, bairro, cidade, cep) VALUES (?,?,?,?,?)");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getLogradouro());
            stmt.setString(3, cliente.getBairro());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getCep());
            
            stmt.executeUpdate();

        } finally {
            fecharConexao(conexao, stmt);
        }
    }

    
        public Cliente instanciarCliente(ResultSet rs) throws SQLException {
        Cliente c = new Cliente(rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("logradouro"),
                rs.getString("bairro"),
                rs.getString("cidade"),
                rs.getString("cep")
        );
        return c;
    }
}
