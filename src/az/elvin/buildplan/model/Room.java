package az.elvin.buildplan.model;

public class Room extends AbstractClass
{
    private String name;
    private Floor floor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", floor=" + floor +
                '}';
    }
}
