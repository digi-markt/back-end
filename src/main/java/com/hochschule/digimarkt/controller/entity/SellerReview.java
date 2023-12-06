package com.hochschule.digimarkt.controller.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SellerReview")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewID")
    private int reviewId;

    @Column(name = "BuyerID")
    private int buyerId;

    @Column(name = "SellerID")
    private int sellerId;

    @Column(name = "Rating")
    private int rating;

    @Column(name = "Comment")
    private String comment;

}
