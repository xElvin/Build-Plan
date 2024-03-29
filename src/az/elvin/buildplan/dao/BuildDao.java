package az.elvin.buildplan.dao;

import az.elvin.buildplan.model.Floor;
import az.elvin.buildplan.model.Reserve;
import az.elvin.buildplan.model.Room;
import az.elvin.buildplan.model.User;

import java.util.List;

public interface BuildDao
{
    List<Floor> getFloors()             throws Exception;
    List<Room>  getRooms(int floorId)   throws Exception;
    List<User>  getUsers(int floorId)   throws Exception;
    User        getUserById(int userId) throws Exception;

    int getUserCount(int roomId) throws Exception;
    void updateUser (int floorId, int roomId, int userId) throws Exception;

    boolean reserve(Reserve r) throws Exception;
    List<Reserve> getReserve(int room_id) throws Exception;
    List<Reserve> getReserves(int user_id, int room_id) throws Exception;

    void deleteReserve(int reserve_id) throws Exception;

    Reserve getReserveById(int reserve_id) throws Exception;

    void updateReserve(int reserve_id, Reserve reserve) throws Exception;
}
