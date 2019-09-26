package az.elvin.buildplan.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Elvin on 13.09.2019.
 */
public class DBClose
{
    public static void dbClose(Connection c, PreparedStatement ps, ResultSet rs) throws Exception
    {
        if (c != null)
        {
            c.close();
        }

        if (ps != null)
        {
            ps.close();
        }

        if (rs != null)
        {
            rs.close();
        }
    }

}
