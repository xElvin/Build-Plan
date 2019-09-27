package az.elvin.buildplan.dao;

import az.elvin.buildplan.model.*;
import az.elvin.buildplan.util.DBClose;
import az.elvin.buildplan.util.DBConnect;

import java.sql.Connection;
import java.sql.Date;
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
        String sql = "SELECT r.idroom, r.name, r.type, r.max_person, r.floor_id, r.active, f.name FROM buildplandb.room r\n" +
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
                    r.setType(rs.getString("r.type"));
                    r.setMax_person(rs.getInt("r.max_person"));

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

    @Override
    public boolean reserve(Reserve r) throws Exception
    {
        boolean result = false;
        Connection         c = null;
        PreparedStatement ps =null;
        String sql = "insert into buildplandb.reserve(date, start_time, end_time, person_count, status, user_id, room_id)\n" +
                     "values(?, ?, ?, ?, 'Reserved', ?, ?)";
        try {
            c = DBConnect.getConnection();
            if (c != null)
            {
                ps = c.prepareStatement(sql);
                ps.setDate(1, new Date(r.getDate().getTime()));
                ps.setTime(2, r.getStart_time());
                ps.setTime(3, r.getEnd_time());
                ps.setInt(4,r.getPerson_count());
                ps.setInt(5, r.getUser_id());
                ps.setInt(6, r.getRoom_id());
                result = ps.execute();
            }
            else{
                System.out.println("Connection is null!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBClose.dbClose(c, ps, null);
        }
        return result;
    }

    /* get reservation for room */
    @Override
    public List<Reserve> getReserve(int room_id) throws Exception
    {
        List<Reserve> reserveList = new ArrayList<>();
        Connection         c = null;
        PreparedStatement ps = null;
        ResultSet         rs = null;
        String sql = "select idreserve, date, start_time, end_time, status, room_id from buildplandb.reserve\n" +
                     "where room_id = ? and active = 1 ";
        try
        {
            c = DBConnect.getConnection();
            if (c != null)
            {
                ps = c.prepareStatement(sql);
                ps.setInt(1, room_id);
                rs = ps.executeQuery();

                while (rs.next())
                {
                    Reserve r = new Reserve();
                    r.setId(rs.getInt("idreserve"));
                    r.setDate(rs.getDate("date"));
                    r.setStart_time(rs.getTime("start_time"));
                    r.setEnd_time(rs.getTime("end_time"));
                    r.setStatus(rs.getString("status"));
                    r.setRoom_id(rs.getInt("room_id"));

                    reserveList.add(r);
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
        return reserveList;
    }



    /* get reservation for room and user */
    @Override
    public List<Reserve> getReserves(int user_id, int room_id) throws Exception
    {
        List<Reserve> reserveList = new ArrayList<>();
        Connection         c = null;
        PreparedStatement ps = null;
        ResultSet         rs = null;
        String sql = "SELECT R.idreserve, R.date, R.start_time, R.end_time, R.person_count, O.name FROM buildplandb.reserve R\n" +
                     "inner join buildplandb.room O on R.room_id = O.idroom\n" +
                     "where R.user_id = ? and R.room_id = ? and R.active = 1 ";
        try
        {
            c = DBConnect.getConnection();
            if (c != null)
            {
                ps = c.prepareStatement(sql);
                ps.setInt(1, user_id);
                ps.setInt(2, room_id);
                rs = ps.executeQuery();

                while (rs.next())
                {
                    Reserve r = new Reserve();
                    r.setId(rs.getInt("R.idreserve"));
                    r.setDate(rs.getDate("R.date"));
                    r.setStart_time(rs.getTime("R.start_time"));
                    r.setEnd_time(rs.getTime("R.end_time"));
                    r.setPerson_count(rs.getInt("R.person_count"));
                    r.setRoom_name(rs.getString("O.name"));

                    reserveList.add(r);
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
        return reserveList;
    }

    @Override
    public void deleteReserve(int reserve_id) throws Exception {
        Connection         c = null;
        PreparedStatement ps =null;
        String sql = "update buildplandb.reserve set active = 0 \n" +
                     "where idreserve = ?";

        try {
            c = DBConnect.getConnection();
            if (c != null)
            {
                ps = c.prepareStatement(sql);
                ps.setLong(1, reserve_id);
                ps.execute();
            }
            else{
                System.out.println("Connection is null!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBClose.dbClose(c, ps, null);
        }
    }

    @Override
    public Reserve getReserveById(int reserve_id) throws Exception
    {
        Reserve r = new Reserve();
        Connection         c = null;
        PreparedStatement ps = null;
        ResultSet         rs = null;
        String sql = "SELECT R.idreserve, R.date, R.start_time, R.end_time, R.person_count, O.name FROM buildplandb.reserve R\n" +
                    "inner join buildplandb.room O on R.room_id = O.idroom\n" +
                    "where R.idreserve = ? and R.active = 1";
        try
        {
            c = DBConnect.getConnection();
            if (c != null)
            {
                ps = c.prepareStatement(sql);
                ps.setInt(1, reserve_id);
                rs = ps.executeQuery();

                if (rs.next())
                {
                    r.setId(rs.getInt("R.idreserve"));
                    r.setDate(rs.getDate("R.date"));
                    r.setStart_time(rs.getTime("R.start_time"));
                    r.setEnd_time(rs.getTime("R.end_time"));
                    r.setPerson_count(rs.getInt("R.person_count"));
                    r.setRoom_name(rs.getString("O.name"));
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
        return r;
    }

    @Override
    public void updateReserve(int reserve_id, Reserve reserve) throws Exception
    {
        Connection         c = null;
        PreparedStatement ps =null;
        String sql = "update buildplandb.reserve set date = ?, start_time = ?, end_time = ?, person_count = ? \n" +
                     "where idreserve = ? ";
        try {
            c = DBConnect.getConnection();
            if (c != null)
            {
                ps = c.prepareStatement(sql);
                ps.setDate(1, new Date(reserve.getDate().getTime()));
                ps.setTime(2, reserve.getStart_time());
                ps.setTime(3, reserve.getEnd_time());
                ps.setInt(4,  reserve.getPerson_count());
                ps.setInt(5,  reserve_id);
                ps.execute();
            }
            else{
                System.out.println("Connection is null!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBClose.dbClose(c, ps, null);
        }
    }
}
