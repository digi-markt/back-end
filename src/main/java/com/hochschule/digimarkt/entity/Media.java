package com.hochschule.digimarkt.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "media")
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
    private String image;

    @Column(name = "SellerID")
    private int sellerId;

    //@Column(name = "ApprovalAdminID")
    //private int approvalAdminId;

    /*@Column(name = "ApprovalAdminName")
    private String approvalAdminName;*/

    /*@Column(name = "MediaCategoryID")
    private int mediaCategoryId;*/

    @Column(name = "Created_on")
    private Date createdOn;

    @Column(name = "Modified_on")
    private Date modifiedOn;

    @Column(name = "StatusID")
    private int statusId;

    @Column(name = "Free")
    private boolean isFree;

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    /*public int getApprovalAdminId() {
        return approvalAdminId;
    }

    public void setApprovalAdminId(int approvalAdminId) {
        this.approvalAdminId = approvalAdminId;
    }*/

   /* public String getApprovalAdminName() {
        return approvalAdminName;
    }

    public void setApprovalAdminName(String approvalAdminName) {
        this.approvalAdminName = approvalAdminName;
    }*/

    /*public int getMediaCategoryId() {
        return mediaCategoryId;
    }

    public void setMediaCategoryId(int mediaCategoryId) {
        this.mediaCategoryId = mediaCategoryId;
    }*/

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
