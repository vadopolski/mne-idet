package com.mneidet.model;

public class ClientProfile {

    private long id;
    private String name;
    private String id_vk;
    private String description;
    private String smallPhotoLink;
    private String bigPhotoLink;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_vk() {
        return id_vk;
    }

    public void setId_vk(String id_vk) {
        this.id_vk = id_vk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSmallPhotoLink() {
        return smallPhotoLink;
    }

    public void setSmallPhotoLink(String smallPhotoLink) {
        this.smallPhotoLink = smallPhotoLink;
    }

    public String getBigPhotoLink() {
        return bigPhotoLink;
    }

    public void setBigPhotoLink(String bigPhotoLink) {
        this.bigPhotoLink = bigPhotoLink;
    }
}
