package oop;

public class Programmer extends Employed {
    private String language;

    public Programmer() {
    }

    public Programmer(String personal_code, String name, String language,double salary) {
        this.setPersonal_code(personal_code);
        this.setName(name);
        this.language = language;
        this.setSalary(salary);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    @Override
    public String toString() {
        return "Programmer{" +
                "personal_code='" + getPersonal_code() + '\'' +
                "firstLastName='" + getName() + '\'' +
                "language='" + getLanguage() + '\'' +
                "salary='" + getSalary() + '\'' +
                '}';
    }
    @Override
    public String toLine() {
        return getPersonal_code() + "|"
                + getName() + "|3|"
                + getLanguage() + "|"
                + getSalary();
    }
}
