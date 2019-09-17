package az.elvin.buildplan.model;

public class Floor extends AbstractClass
{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "name='" + name + '\'' +
                '}';
    }
}
