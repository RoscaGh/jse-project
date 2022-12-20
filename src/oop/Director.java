package oop;

public class Director extends Employed{
    public Director(String personal_code, String name, String company_name,double salary) {
        this.setPersonal_code(personal_code);
        this.setName(name);
        this.company_name = company_name;
        this.setSalary(salary);
    }

    public Director() {
    }

    private String company_name;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    @Override
    public String toString() {
        return "Director{" +
                "personal_code='" + getPersonal_code() + '\'' +
                "firstLastName='" + getName() + '\'' +
                "company_name='" + getCompany_name() + '\'' +
                "salary='" + getSalary() + '\'' +
                '}';
    }
    @Override
    public String toLine() {
        return getPersonal_code() + "|"
                + getName() + "|1|"
                + getCompany_name() + "|"
                + getSalary();
    }
}
