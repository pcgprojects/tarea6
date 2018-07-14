package pe.edu.upc.tarea6.models;

import android.os.Bundle;

import java.util.List;

/**
 * Created by roy on 14/07/2018.
 */

public class Employee {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public Employee() {
    }

    public Employee(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public Employee setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Employee setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Employee setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("name", this.getUsername());
        bundle.putString("password", this.getPassword());
        bundle.putString("firstName", this.getFirstName());
        bundle.putString("lastName", this.getLastName());
        return bundle;
    }

    public static class Builder{
        private Employee employee;
        private List<Employee> employees;

        public Builder(Employee employee){ this.employee = employee;}

        public Builder(List<Employee> employees) {
            this.employees = employees;
        }

        public static Builder from(Bundle bundle){
            Employee employee = new Employee();
            employee.setUsername(bundle.getString("username"))
                    .setPassword(bundle.getString("password"))
                    .setLastName(bundle.getString("lastName"))
                    .setFirstName(bundle.getString("firstName"));
            return new Builder(employee);
        }
        public Employee build() {
            return this.employee;
        }

        public List<Employee> buildAll() {
            return this.employees;
        }
    }
}
