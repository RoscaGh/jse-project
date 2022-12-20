package oop;

public class Manager extends Employed{
    private int teamMember;

    public Manager() {
    }
    public Manager(String personal_code, String name, int teamMember,double salary) {
        this.setPersonal_code(personal_code);
        this.setName(name);
        this.teamMember = teamMember;
        this.setSalary(salary);
    }
    @Override
    public String toString() {
        return "Manager{" +
                "personal_code='" + getPersonal_code() + '\'' +
                "firstLastName='" + getName() + '\'' +
                "team_Manager='" + getTeamMember() + '\'' +
                "salary='" + getSalary() + '\'' +
                '}';
    }
    @Override
    public String toLine() {
        return getPersonal_code() + "|"
                + getName() + "|2|"
                + getTeamMember() + "|"
                + getSalary();
    }
    public int getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(int teamMember) {
        this.teamMember = teamMember;
    }
}
