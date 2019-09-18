package az.elvin.buildplan.dao;

import az.elvin.buildplan.model.*;
import az.elvin.buildplan.util.DBClose;
import az.elvin.buildplan.util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BuildDaoImpl implements BuildDao
{
    @Override
    public List<Floor> getFloors() throws Exception
    {
        List<Floor> floorList = new ArrayList<>();
        Connection         c = null;
        PreparedStatement ps = null;
        ResultSet         rs = null;
        String sql = "SELECT idfloor, name, active FROM buildplandb.floor\n" +
                     "where active = 1";
        try
        {
            c = DBConnect.getConnection();
            if (c != null)
            {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next())
                {
                    Floor f = new Floor();
                    f.setId(rs.getInt("idfloor"));
                    f.setName(rs.getString("name"));
                    f.setActive(rs.getInt("active"));

                    floorList.add(f);
                }
            }
            else
            {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        } finally
        {
            DBClose.dbClose(c, ps, rs);
        }
        return floorList;
    }

    @Override
    public List<Room> getRooms(int floorId) throws Exception
    {
        List<Room> roomList = new ArrayList<>();
        Connection         c = null;
        PreparedStatement ps = null;
        ResultSet         rs = null;
        String sql = "SELECT r.idroom, r.name, r.floor_id, r.active, f.name FROM buildplandb.room r\n" +
                    "inner join buildplandb.floor f on f.idfloor = r.floor_id\n" +
                    "where r.floor_id = ? and r.active = 1";
        try
        {
            c = DBConnect.getConnection();
            if (c != null)
            {
                ps = c.prepareStatement(sql);
                ps.setInt(1, floorId);
                rs = ps.executeQuery();

                while (rs.next())
                {
                    Room r = new Room();
                    r.setId(rs.getInt("r.idroom"));
                    r.setName(rs.getString("r.name"));

                    Floor f = new Floor();
                    f.setId(rs.getInt("r.floor_id"));
                    f.setName(rs.getString("f.name"));

                    r.setFloor(f);

                    roomList.add(r);
                }
            }
            else
            {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        } finally
        {
            DBClose.dbClose(c, ps, rs);
        }
        return roomList;
    }

    @Override
    public List<User> getUsers(int roomId) throws Exception
    {
        List<User> userList = new ArrayList<>();
        Connection         c = null;
        PreparedStatement ps = null;
        ResultSet         rs = null;
        String sql = "SELECT u.idusers, u.name, u.surname, u.email, u.phone, u.department_id, u.position_id, u.floor_id, u.room_id, u.active,\n" +
                    "d.name, p.name, f.name, r.name FROM buildplandb.users u\n" +
                    "inner join buildplandb.department d on u.department_id = d.iddepartment\n" +
                    "inner join buildplandb.position p on u.position_id = p.idposition\n" +
                    "inner join buildplandb.floor f on u.floor_id = f.idfloor\n" +
                    "inner join buildplandb.room r on u.room_id = r.idroom\n" +
                    "where u.floor_id = ? and u.active = 1 ";
        try
        {
            c = DBConnect.getConnection();
            if (c != null)
            {
                ps = c.prepareStatement(sql);
                ps.setInt(1,roomId);
                rs = ps.executeQuery();

                while (rs.next())
                {
                    User u = new User();
                    u.setId(rs.getInt("u.idusers"));
                    u.setName(rs.getString("u.name"));
                    u.setSurname(rs.getString("u.surname"));
                    u.setMail(rs.getString("u.email"));
                    u.setPhone(rs.getString("u.phone"));
                    u.setActive(rs.getInt("u.active"));

                    Floor f = new Floor();
                    f.setId(rs.getInt("u.floor_id"));
                    f.setName(rs.getString("f.name"));

                    Room r = new Room();
                    r.setId(rs.getInt("u.room_id"));
                    r.setName(rs.getString("r.name"));

                    Department d = new Department();
                    d.setId(rs.getInt("u.department_id"));
                    d.setName(rs.getString("d.name"));

                    Position p = new Position();
                    p.setId(rs.getInt("u.position_id"));
                    p.setName(rs.getString("p.name"));

                    u.setFloor(f);
                    u.setRoom(r);
                    u.setDepartment(d);
                    u.setPosition(p);

                    userList.add(u);
                }
            }
            else
            {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        } finally
        {
            DBClose.dbClose(c, ps, rs);
        }
        return userList;
    }

    @Override
    public User getUserById(int userId) throws Exception
    {
        User u = new User();
        Connection         c = null;
        PreparedStatement ps = null;
        ResultSet         rs = null;
        String sql = "SELECT u.idusers, u.name, u.surname, u.email, u.phone, u.department_id, u.position_id, u.floor_id, u.room_id, u.active,\n" +
                    "d.name, p.name, f.name, r.name FROM buildplandb.users u\n" +
                    "inner join buildplandb.department d on u.department_id = d.iddepartment\n" +
                    "inner join buildplandb.position p on u.position_id = p.idposition\n" +
                    "inner join buildplandb.floor f on u.floor_id = f.idfloor\n" +
                    "inner join buildplandb.room r on u.room_id = r.idroom\n" +
                    "where u.idusers = ? and u.active = 1 ";

        try
        {
            c = DBConnect.getConnection();
            if (c != null)
            {
                ps = c.prepareStatement(sql);
                ps.setInt(1, userId);
                rs = ps.executeQuery();

                if (rs.next())
                {
                    u.setId(rs.getInt("u.idusers"));
                    u.setName(rs.getString("u.name"));
                    u.setSurname(rs.getString("u.surname"));
                    u.setMail(rs.getString("u.email"));
                    u.setPhone(rs.getString("u.phone"));
                    u.setActive(rs.getInt("u.active"));

                    Floor f = new Floor();
                    f.setId(rs.getInt("u.floor_id"));
                    f.setName(rs.getString("f.name"));

                    Room r = new Room();
                    r.setId(rs.getInt("u.room_id"));
                    r.setName(rs.getString("r.name"));

                    Department d = new Department();
                    d.setId(rs.getInt("u.department_id"));
                    d.setName(rs.getString("d.name"));

                    Position p = new Position();
                    p.setId(rs.getInt("u.position_id"));
                    p.setName(rs.getString("p.name"));

                    u.setFloor(f);
                    u.setRoom(r);
                    u.setDepartment(d);
                    u.setPosition(p);
                }
            }
        } catch (Exception exc)
        {
            exc.printStackTrace();
        }
        return u;
    }

    @Override
    public int getUserCount(int roomId) throws Exception
    {
        Connection         c = null;
        PreparedStatement ps = null;
        ResultSet         rs = null;
        int count = 0;

        String sql = "select count(idusers) as total from buildplandb.users where room_id = ? ";

        try
        {
            c = DBConnect.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, roomId);
                rs = ps.executeQuery();

                if (rs.next())
                {
                    count = rs.getInt("total");
                }
            }

        } catch (Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            DBClose.dbClose(c, ps, rs);
        }
        return count;
    }

    @Override
    public void updateUser(int floorId, int roomId, int userId) throws Exception
    {
        Connection         c = null;
        PreparedStatement ps = null;

        String sql = "update buildplandb.users u set u.floor_id = ?, u.room_id = ? \n" +
                     "where u.idusers = ?";
        try
        {
            c = DBConnect.getConnection();
            if (c != null)
            {
                ps = c.prepareStatement(sql);
                ps.setInt(1, floorId);
                ps.setInt(2, roomId);
                ps.setInt(3, userId);
                ps.execute();
            }
        } catch (Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            DBClose.dbClose(c, ps, null);
        }
    }
}
