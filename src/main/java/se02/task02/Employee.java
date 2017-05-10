package se02.task02;

public class Employee {
    private String name;
    private String position;
    private int age;
    private Workspace workspace;

    public Employee(String name, String position, int age, StationeryPack sp) {
        this.name = name;
        this.position = position;
        this.age = age;
        workspace = Workspace.getInstance(sp);
    }

    public int calcStationeryPrice() {
        return workspace.calcWorkspaceStationeryPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
