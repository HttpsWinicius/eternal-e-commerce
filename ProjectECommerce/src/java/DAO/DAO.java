package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
    
    public void fecharConexao(Connection conexao, Statement stmt) throws SQLException{
        if(stmt != null){
            stmt.close();
        }
        if(stmt != null){
            stmt.close();
        }
    }
}
