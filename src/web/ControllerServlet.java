package web;

import az.elvin.buildplan.model.Floor;
import az.elvin.buildplan.model.Reserve;
import az.elvin.buildplan.model.Room;
import az.elvin.buildplan.model.User;
import az.elvin.buildplan.service.BuildService;
import az.elvin.buildplan.service.BuildServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

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
                    message = "updated";
                    response.addHeader("message", message);
                    service.updateUser(floorId, roomId, userId);
                    address = "rooms.jsp";
                }
                else
                {
                    message = "not updated";
                    response.addHeader("message", message);
                    address = "WEB-INF/parseJsp/modalParse.jsp";
                }
            }


            /*   RESERVE   */

            else if (action.equalsIgnoreCase("reserve"))
            {
                String message = "";

                String date = request.getParameter("date");
                String start_time = request.getParameter("start_time");
                String end_time = request.getParameter("end_time");
                String personCount = request.getParameter("person_count");
                String roomId = request.getParameter("room_id");
                String userId = request.getParameter("user_id");

                int count = 0;

                if (date != null && !date.isEmpty() && start_time != null && !start_time.isEmpty() && end_time != null && !end_time.isEmpty() && personCount != null && !personCount.isEmpty())
                {
                    Date date1 = new java.sql.Date(df.parse(date).getTime());
                    Time start_time1 = new java.sql.Time(dateFormat.parse(start_time).getTime());
                    Time end_time1 = new java.sql.Time(dateFormat.parse(end_time).getTime());

                    int person_count = Integer.parseInt(personCount);
                    int room_id = Integer.parseInt(roomId);
                    int user_id = Integer.parseInt(userId);

                    Reserve reserve = new Reserve();
                    reserve.setDate(date1);
                    reserve.setStart_time(start_time1);
                    reserve.setEnd_time(end_time1);
                    reserve.setPerson_count(person_count);
                    reserve.setRoom_id(room_id);
                    reserve.setUser_id(user_id);

                    List<Reserve> reserves = service.getReserve(room_id);
                    for (Reserve r : reserves)
                    {
                        if (date1.compareTo(r.getDate()) == 0)
                        {
                            System.out.println("gunler eynidir");
                            if (start_time1.compareTo(r.getEnd_time()) > 0 || end_time1.compareTo(r.getStart_time()) < 0)
                            {
                                System.out.println("saatlar eyni deyil");
                            }
                            else
                            {
                                System.out.println("saatlar eynidir");
                                count++;
                            }
                        }
                        else
                        {
                            System.out.println("gunler eyni deyil");
                        }
                    }
                    if (count == 0)
                    {
                        System.out.println("elave etmek olar!!!!!!");

                        service.reserve(reserve);

                        message = "reserved";
                        response.addHeader("message", message);
                    }
                    else
                    {
                        message = "not reserved";
                        response.addHeader("message", message);
                    }
                }

                address = "rooms.jsp";
            }


            else if (action.equalsIgnoreCase("getReserves"))
            {
                int user_id = Integer.parseInt(request.getParameter("user_id"));
                int room_id = Integer.parseInt(request.getParameter("room_id"));

                List<Reserve> reserves = service.getReserves(user_id, room_id);
                request.setAttribute("reserves", reserves);
                address = "WEB-INF/parseJsp/infoModalParse.jsp";
            }

        } catch (Exception exc)
        {
            exc.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
