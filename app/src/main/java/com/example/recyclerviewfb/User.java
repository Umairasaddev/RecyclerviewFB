package com.example.recyclerviewfb;

public class User {
    private String  name,email,age;

    public User() {
    }

    public User(String name, String email, String age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }
}
