package com.hochschule.digimarkt.controller.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "Download")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Download {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DownloadID")
    private int downloadId;

    @Column(name = "BuyerID")
    private int buyerId;

    @Column(name = "MediaID")
    private int mediaId;

    @Column(name = "DownloadDate")
    private Timestamp downloadDate;

}
