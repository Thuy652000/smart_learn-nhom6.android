package com.example.appsmartlearn;


public class ModelVideo {
    private String name;
    private String videourl;

    public ModelVideo() {}

    public ModelVideo(String name, String videourl) {
        this.name = name;
        this.videourl = videourl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }
}
