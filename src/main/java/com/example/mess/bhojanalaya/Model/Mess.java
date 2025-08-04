package com.example.mess.bhojanalaya.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private User admin;

    @OneToMany(mappedBy = "mess")
    private List<User> students;

    @OneToMany(mappedBy = "mess")
    private List<Menu> menus;

    @OneToMany(mappedBy = "mess")
    private List<Review> reviews;

    @OneToMany(mappedBy = "mess")
    private List<JoinRequest> joinRequests;
}
