package com.example.reservation_api.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "RESOURCES")
public class Resource {
    @Id @GeneratedValue
    private Long id;
    private String name;

    public Long getId() { return id; }
    public String getName() { return name; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}
