package az.elvin.buildplan.model;

public abstract class AbstractClass
{
    private int id;
    private int active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "AbstractClass{" +
                "id=" + id +
                ", active=" + active +
                '}';
    }
}
