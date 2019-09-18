package az.elvin.buildplan.service;

import az.elvin.buildplan.model.Floor;
import az.elvin.buildplan.model.Room;
import az.elvin.buildplan.model.User;

import java.util.List;

public interface BuildService
{
    List<Floor> getFloors()             throws Exception;
    List<Room>  getRooms(int floorId)   throws Exception;
    List<User>  getUsers(int floorId)   throws Exception;
    User        getUserById(int userId) throws Exception;

    int getUserCount(int roomId) throws Exception;
    void updateUser (int floorId, int roomId, int userId) throws Exception;
}