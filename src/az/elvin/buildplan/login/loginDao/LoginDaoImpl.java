package az.elvin.buildplan.login.loginDao;

import az.elvin.buildplan.login.loginModel.Login;
import az.elvin.buildplan.util.DBClose;
import az.elvin.buildplan.util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Elvin on 06.08.2019.
 */
public class LoginDaoImpl implements LoginDao
{

    @Override
    public Login login(String username, String password) throws Exception
    {
        Login login = new Login();

        Connection         c = null;
        PreparedStatement ps = null;
        ResultSet         rs = null;
        String sql = "SELECT idLogin, username, password FROM buildplandb.login\n" +
                     "where active = 1 and username = ? and password = ? ";

        try
        {
            c = DBConnect.getConnection();
            if (c != null)
            {
                ps = c.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if (rs.next())
                {
                    login.setId(rs.getInt("idLogin"));
                    login.setUsername(rs.getString("username"));
                    login.setPassword(rs.getString("password"));
                }
                else
                    login = null;
            }
            else
                System.out.println("Connection is null!");
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            DBClose.dbClose(c, ps, rs);
        }
        return login;
    }
}
