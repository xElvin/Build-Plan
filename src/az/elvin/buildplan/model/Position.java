package az.elvin.buildplan.model;

public class Position extends AbstractClass
{
    private String name;
    private Department department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Position{" +
                "name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}
