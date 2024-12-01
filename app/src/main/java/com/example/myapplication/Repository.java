//package com.example.myapplication;
//
//public class Repository {
//    private String repoName;
//    private String repoOwner;
//    private String repoDescription;
//    private int repoStars;
//    private String avatarUrl;
//
//    public Repository(String repoName, String repoOwner, String repoDescription, int repoStars, String avatarUrl) {
//        this.repoName = repoName;
//        this.repoOwner = repoOwner;
//        this.repoDescription = repoDescription;
//        this.repoStars = repoStars;
//        this.avatarUrl = avatarUrl;
//    }
//
//    public String getRepoName() {
//        return repoName;
//    }
//
//    public String getRepoOwner() {
//        return repoOwner;
//    }
//
//    public String getRepoDescription() {
//        return repoDescription;
//    }
//
//    public int getRepoStars() {
//        return repoStars;
//    }
//
//    public String getAvatarUrl() {
//        return avatarUrl;
//    }
//}

package com.example.myapplication;

public class Repository {
    private String name;
    private String owner;
    private String description;
    private int stars;

    public Repository(String name, String owner, String description, int stars) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.stars = stars;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public int getStars() {
        return stars;
    }
}
