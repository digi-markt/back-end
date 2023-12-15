package com.hochschule.digimarkt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Entity
@Table(name = "Media")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MediaID")
    private int mediaId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private double price;

    @Column(name = "Flag")
    private Boolean flag;

    @Column(name = "Image")
    private byte[] image;

    @Column(name = "SellerID")
    private int sellerId;

    @Column(name = "ApprovalAdminID")
    private int approvalAdminId;

    @Column(name = "ApprovalAdminName")
    private String approvalAdminName;

    @Column(name = "MediaCategoryID")
    private int mediaCategoryId;

    @Column(name = "Created_on")
    private Timestamp createdOn;

    @Column(name = "Modified_on")
    private Timestamp modifiedOn;

    @Column(name = "StatusID")
    private int statusId;

}
