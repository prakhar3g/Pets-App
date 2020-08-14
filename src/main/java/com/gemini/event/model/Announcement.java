package com.gemini.event.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="announcements")
public class Announcement {

    @Id
    @Column(name = "ann_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int annId;

    @Column(name = "ann_text")
    private String announcementText;


    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private java.util.Date createDate;

    public Announcement(){

    }

    public Announcement(int annId) {
        this.annId = annId;
    }

    public int getAnnId() {
        return annId;
    }

    public void setAnnId(int annId) {
        this.annId = annId;
    }

    public String getAnnouncementText() {
        return announcementText;
    }

    public void setAnnouncementText(String announcementText) {
        this.announcementText = announcementText;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
