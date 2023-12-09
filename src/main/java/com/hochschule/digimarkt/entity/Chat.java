package com.hochschule.digimarkt.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Chat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ChatID")
    private int chatId;

    @Column(name = "BuyerID")
    private int buyerId;

    @Column(name = "SellerID")
    private int sellerId;

    @Column(name = "Messages")
    private String messages;

    @Column(name = "StatusID")
    private int statusId;

}
