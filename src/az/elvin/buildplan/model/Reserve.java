package az.elvin.buildplan.model;

import java.sql.Time;
import java.util.Date;

public class Reserve extends AbstractClass
{
    private Date date;
    private Time start_time;
    private Time end_time;
    private int person_count;
    private String status;
    private int user_id;
    private int room_id;
    private String room_name;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public int getPerson_count() {
        return person_count;
    }

    public void setPerson_count(int person_count) {
        this.person_count = person_count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "date=" + date +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", person_count=" + person_count +
                ", status='" + status + '\'' +
                ", user_id=" + user_id +
                ", room_id=" + room_id +
                ", room_name=" + room_name +
                '}';
    }
}
