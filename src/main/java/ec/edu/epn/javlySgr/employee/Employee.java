package ec.edu.epn.javlySgr.employee;

public class Employee {
    private String name;
    private String lastName;
    private String role;
    private String password;
    private String username;

    public Employee(String name, String lastName, String role, String password, String username) {
        this.name = name;
        this.lastName = lastName;
        this.role = role;
        this.password = password;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
