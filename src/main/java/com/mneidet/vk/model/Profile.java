package com.mneidet.vk.model;

public class Profile {

    private String firstName;
    private String lastName;
    private String lastSmallProfilePhotoLink;
    private String lastBigProfilePhotoLink;
    private String VkId;

    public String getLastBigProfilePhotoLink() {
        return lastBigProfilePhotoLink;
    }

    public void setLastBigProfilePhotoLink(String lastBigProfilePhotoLink) {
        this.lastBigProfilePhotoLink = lastBigProfilePhotoLink;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastSmallProfilePhotoLink() {
        return lastSmallProfilePhotoLink;
    }

    public void setLastSmallProfilePhotoLink(String lastSmallProfilePhotoLink) {
        this.lastSmallProfilePhotoLink = lastSmallProfilePhotoLink;
    }

    public String getVkId() {
        return VkId;
    }

    public void setVkId(String vkId) {
        VkId = vkId;
    }

}
