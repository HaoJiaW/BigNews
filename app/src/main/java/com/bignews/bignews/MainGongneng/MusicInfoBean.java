package com.bignews.bignews.MainGongneng;

/**
 * 音乐信息Bean
 */
public class MusicInfoBean {

    private String singer;
    private String musicName;
    private String imageURl;
    private String musicUrl;
    private String mpF;

    public MusicInfoBean(String singer, String musicName, String imageURl, String musicUrl, String mpF) {
        this.singer = singer;
        this.musicName = musicName;
        this.imageURl = imageURl;
        this.musicUrl = musicUrl;
        this.mpF = mpF;
    }


    public String getMpF() {
        return mpF;
    }

    public void setMpF(String mpF) {
        this.mpF = mpF;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public MusicInfoBean(String singer, String musicName, String imageURl, String musicUrl) {
        this.singer = singer;
        this.musicName = musicName;
        this.imageURl = imageURl;
        this.musicUrl = musicUrl;
    }

    public String getImageURl() {
        return imageURl;
    }

    public void setImageURl(String imageURl) {
        this.imageURl = imageURl;
    }

    public MusicInfoBean(String singer, String musicName, String imageURl) {
        this.singer = singer;
        this.musicName = musicName;
        this.imageURl = imageURl;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public MusicInfoBean(String singer, String musicName) {
        this.singer = singer;
        this.musicName = musicName;
    }
}