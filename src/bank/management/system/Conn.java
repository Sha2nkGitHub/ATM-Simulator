/*
 * Five steps in JDBC connectivity : 
 * 1. Register the Driver
 * 2. Create Connection
 * 3. Create Statement
 * 4. Execute Query
 * 5. Close Connection
 */

package bank.management.system;

import java.sql.*;

public class Conn {
    private Connection c;
    public Statement s;

    public Conn() {
        // mySQL is an external entity, so we need to handle exceptions(runtine errors)
        try {
            // Class.forName(com.mysql.cj.jdbc.Driver);
            c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root", "Sha2nk");
            s = c.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
