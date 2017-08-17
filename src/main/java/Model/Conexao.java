
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author itagiba
 */
public class Conexao {

       public static Connection getConnection() throws Exception {
        try{
            Connection conn;

            Class.forName("com.mysql.jdbc.Driver");
//         conn = DriverManager.getConnection("jdbc:mysql://mysql129560-drogariamodelo.jelasticlw.com.br/Farmacia","root","DBOycn72511");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/Farmacia","root","");
            
            
            conn.setCatalog("Farmacia");
            
            return conn;
        }catch(Exception e){
            throw new Exception(e.getMessage());
            }
        }
    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception{
        close(conn,stmt,rs);
    }

    public static void closeConnection(Connection conn, Statement stmt) throws Exception{
        close(conn,stmt,null);
    }

    public static void closeConnection(Connection conn) throws Exception{
        close(conn,null,null);
    }
private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception{
    try{
        if( rs != null) rs.close();
        if (stmt !=null) stmt.close();
        if(conn !=null) conn.close();
    } catch(Exception e){
        throw new Exception(e.getMessage());
          }
        }

    public static Connection getConection() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

