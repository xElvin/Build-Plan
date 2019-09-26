package az.elvin.buildplan.login.loginDao;

import az.elvin.buildplan.login.loginModel.Login;
import az.elvin.buildplan.model.User;
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
        User u = new User();

        Connection         c = null;
        PreparedStatement ps = null;
        ResultSet         rs = null;
        String sql = "SELECT l.idLogin, l.username, l.password, l.user_id, u.name, u.surname FROM buildplandb.login l "+
                     "left join buildplandb.users u on l.user_id = u.idusers "+
                     "where l.active = 1 and username = ? and password = ? ";

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
                    login.setId(rs.getInt("l.idLogin"));
                    login.setUsername(rs.getString("l.username"));
                    login.setPassword(rs.getString("l.password"));
                    login.setUser_id(rs.getInt("l.user_id"));

                    u.setName(rs.getString("u.name"));
                    u.setSurname(rs.getString("u.surname"));

                    login.setUser(u);
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
