package web;

import az.elvin.buildplan.model.Floor;
import az.elvin.buildplan.model.Room;
import az.elvin.buildplan.model.User;
import az.elvin.buildplan.service.BuildService;
import az.elvin.buildplan.service.BuildServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

/**
 * Created by Elvin on 13.09.2019.
 */
@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends javax.servlet.http.HttpServlet
{
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException
    {
        processRequest(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException
    {
        processRequest(request, response);
    }

    protected void processRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException
    {
        String action  = null;
        String address = null;
        BuildService service = new BuildServiceImpl();

        try
        {
            if (request.getParameter("action") != null)
            {
                action = request.getParameter("action");
            }


            /*   GET FLOORS   */

            if (action.equalsIgnoreCase("getFloors"))
            {
                List<Floor> floors = service.getFloors();
                request.setAttribute("floors", floors);
                address = "WEB-INF/parseJsp/floorParse.jsp";
            }



          /*   GET ROOMS   */

            else if (action.equalsIgnoreCase("getRooms"))
            {
                int floorId = Integer.parseInt(request.getParameter("floorId"));
                List<Room> rooms = service.getRooms(floorId);
                request.setAttribute("rooms", rooms);

                List<User> users = service.getUsers(floorId);
                request.setAttribute("users", users);

                address = "WEB-INF/parseJsp/roomParse.jsp";
            }


            /*   GET USERS   */

            /*else if (action.equalsIgnoreCase("getUsers"))
            {
                int floorId = Integer.parseInt(request.getParameter("floorId"));
                List<User> users = service.getUsers(floorId);
                request.setAttribute("users", users);
                address = "WEB-INF/parseJsp/roomParse.jsp";
                System.out.println("users="+users);
            }*/


            /*   GET USERBYID   */

            else if (action.equalsIgnoreCase("getUserById"))
            {
                int userId = Integer.parseInt(request.getParameter("userId"));
                User u = service.getUserById(userId);
                request.setAttribute("u", u);
                List<Floor> floors = service.getFloors();
                request.setAttribute("floors", floors);

                int floorId = Integer.parseInt(request.getParameter("floorId"));
                List<Room> rooms = service.getRooms(floorId);
                request.setAttribute("rooms", rooms);

                address = "WEB-INF/parseJsp/modalParse.jsp";
            }


            /*   GET ROOMS COMBO   */

            else if (action.equalsIgnoreCase("getRoomsCombo"))
            {
                int floorId = Integer.parseInt(request.getParameter("floorId"));
                List<Room> rooms = service.getRooms(floorId);
                request.setAttribute("rooms", rooms);

                address = "WEB-INF/parseJsp/roomComboParse.jsp";


            }


            /*   UPDATE USER   */

            else if (action.equalsIgnoreCase("updateUser"))
            {
                int floorId = Integer.parseInt(request.getParameter("floorId"));
                int roomId  = Integer.parseInt(request.getParameter("roomId"));
                int userId  = Integer.parseInt(request.getParameter("userId"));
                String message = "";

                int userCount = service.getUserCount(roomId);

                if (userCount < 8)
                {
                    service.updateUser(floorId, roomId, userId);
                    address = "rooms.jsp";
                }
                else
                {
                    message = "Room have a max user!";
                    request.setAttribute("message", message);
                    address = "WEB-INF/parseJsp/modalParse.jsp";
                }
                //address = "rooms.jsp";

            }

        } catch (Exception exc)
        {
            exc.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
