package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Classe factory de conexão com o banco
public class ConnectionFactory {
    //Atributo para controlar o Singleton
    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:banco_de_dados/parcial.db";
    
    public static Connection getConnection(){
        
        try {
            
            //Se não houver conexão, ele realiza.
            if(instance == null) {
                instance = DriverManager.getConnection(URL);
            }
            
        } catch(SQLException e){
            throw new RuntimeException("Whoops!! Erro ao conectar com o banco de dados...", e);
        }
        return instance;
        
    }
    
    public static void closeConnection() {
        
        try {
            
            if(instance != null) {
                instance.close();
                instance = null;
            }
            
        } catch(SQLException e) {
            throw new RuntimeException("Whoops!! Erro ao fechar o banco de dados...", e);
        }
        
    }
}
