package az.elvin.buildplan.service;

import az.elvin.buildplan.dao.BuildDao;
import az.elvin.buildplan.dao.BuildDaoImpl;
import az.elvin.buildplan.model.Floor;
import az.elvin.buildplan.model.Reserve;
import az.elvin.buildplan.model.Room;
import az.elvin.buildplan.model.User;

import java.util.List;

public class BuildServiceImpl implements BuildService


{
    private BuildDao buildDao = new BuildDaoImpl();
    @Override
    public List<Floor> getFloors() throws Exception
    {
        return buildDao.getFloors();
    }

    @Override
    public List<Room> getRooms(int floorId) throws Exception
    {
        return buildDao.getRooms(floorId);
    }

    @Override
    public List<User> getUsers(int roomId) throws Exception
    {
        return buildDao.getUsers(roomId);
    }

    @Override
    public User getUserById(int userId) throws Exception
    {
        return buildDao.getUserById(userId);
    }

    @Override
    public int getUserCount(int roomId) throws Exception
    {
        return buildDao.getUserCount(roomId);
    }

    @Override
    public void updateUser(int floorId, int roomId, int userId) throws Exception
    {
        buildDao.updateUser(floorId, roomId, userId);
    }

    @Override
    public boolean reserve(Reserve r) throws Exception
    {
        return buildDao.reserve(r);
    }

    @Override
    public List<Reserve> getReserve(int room_id) throws Exception
    {
        return buildDao.getReserve(room_id);
    }

    @Override
    public List<Reserve> getReserves(int user_id, int room_id) throws Exception {
        return buildDao.getReserves(user_id, room_id);
    }
}
