package com.campuslands.modules.customers.domain.models;

public class Customer {
    int id;
    String name;
    int age;
    int iddocument;
    String password;
    String email;

    public Customer(int id, String name, int age, int iddocument, String password, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.iddocument = iddocument;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIddocument() {
        return iddocument;
    }

    public void setIddocument(int iddocument) {
        this.iddocument = iddocument;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}