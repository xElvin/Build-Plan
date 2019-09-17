package az.elvin.buildplan.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by Elvin on 13.09.2019.
 */
public class DBConnect
{
    public static Connection getConnection() throws Exception
    {
        Context context = new InitialContext();
        DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/buildplan");
        Connection c = ds.getConnection();
        return c;
    }
}
