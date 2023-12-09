package com.hochschule.digimarkt.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int userId;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Email")
    private String email;

    @Column(name = "RoleID")
    private int roleId;

    @Column(name = "Permission")
    private String permission;

    @Column(name = "Rating")
    private int rating;

    @Column(name = "Create_at")
    private Timestamp createdAt;

    @Column(name = "Modified_at")
    private Timestamp modifiedAt;

}
