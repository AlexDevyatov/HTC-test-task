package com.example.android.htc_test_task;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Алексей on 20.11.2017.
 */

public class Employee implements Comparable<Employee> {
    private String name;
    @SerializedName("phone_number")
    private String phoneNumber;
    private List<String> skills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(employee.phoneNumber) : employee.phoneNumber != null)
            return false;
        return skills != null ? skills.equals(employee.skills) : employee.skills == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (skills != null ? skills.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(name + " ");
        result.append(phoneNumber + " ");
        for (String skill: skills) {
            result.append(skill + " ");
        }
        return result.toString();
    }

    @Override
    public int compareTo(Employee o) {
        return name.compareTo(o.name);
    }
}
