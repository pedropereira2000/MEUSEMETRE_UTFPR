
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
    
    public Connection conectar(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/tst?useTimezone=true&serverTimezone=UTC","root","Pedro@0704");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
}

