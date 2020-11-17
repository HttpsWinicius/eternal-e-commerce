/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author luiz_
 */
public class DatabaseLocator {
    
    //padrao Singleton, garante a existência de apenas uma instância de uma classe (linha 21/24)
    private static DatabaseLocator instance = new DatabaseLocator();
    public static DatabaseLocator getInstance(){
        return instance;
    }
    private DatabaseLocator(){} //construção do construtor
    
    public Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn =
                DriverManager.getConnection("jdbc:mysql://localhost/eternal", "root", "");
        return conn;
    }
}
