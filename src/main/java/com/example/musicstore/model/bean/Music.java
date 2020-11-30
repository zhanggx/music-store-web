package com.example.musicstore.model.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Date;
import java.sql.Timestamp;

@Table(name = "music")
public class Music {
    @Id
    private Integer id;
    private String name;

    private Integer albumId;
    @Transient
    private String albumName;
    private Integer singerId;
    @Transient
    private String singerName;

    @Transient
    private String timeLengthText;

    private Integer timeLength;

    @Transient
    private String fileUrl;
    private String filePath;
    private Integer fileSize;
    @Transient
    private String fileSizeText;
    private String description;

    private Integer recommendIndex;

    @JsonIgnore
    @Transient
    private String albumPicturePath;
    @Transient
    private String albumPictureUrl;
    /**
     * 创建时间
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp timeStamp;

    public Music(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Integer getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(Integer timeLength) {
        this.timeLength = timeLength;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTimeLengthText() {
        return timeLengthText;
    }

    public void setTimeLengthText(String timeLengthText) {
        this.timeLengthText = timeLengthText;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSizeText() {
        return fileSizeText;
    }

    public void setFileSizeText(String fileSizeText) {
        this.fileSizeText = fileSizeText;
    }

    public Integer getRecommendIndex() {
        return recommendIndex;
    }

    public void setRecommendIndex(Integer recommendIndex) {
        this.recommendIndex = recommendIndex;
    }

    public String getAlbumPicturePath() {
        return albumPicturePath;
    }

    public void setAlbumPicturePath(String albumPicturePath) {
        this.albumPicturePath = albumPicturePath;
    }

    public String getAlbumPictureUrl() {
        return albumPictureUrl;
    }

    public void setAlbumPictureUrl(String albumPictureUrl) {
        this.albumPictureUrl = albumPictureUrl;
    }
}
