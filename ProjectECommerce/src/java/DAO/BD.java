/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author luiz_
 */
public class BD {
    
    private static BD instance = new BD();
    public static BD getInstancia(){
        return instance;
    }
    private BD(){}
    
    public Connection getConexao() throws ClassNotFoundException, SQLException{
       Class.forName("com.mysql.jdbc.Driver");
        Connection conn =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmax", "root", "");
        return conn;
    }
}
