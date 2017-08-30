package com.alo.lagosgitusers;

/**
 * Created by alo on 8/23/17.
 */

public class ListItem {

    private String userName;
    private String gitHubProfile;
    private String imageUrl;


    public ListItem(String userName, String gitHubProfile, String imageUrl) {
        this.userName = userName;
        this.gitHubProfile = gitHubProfile;
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGitHubProfile() {
        return gitHubProfile;
    }

    public void setGitHubProfile(String gitHubProfile) {
        this.gitHubProfile = gitHubProfile;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
