package com.gemini.event.dto;


import java.util.Date;

public class EventDetails {

    private String organiserId;
    private String eventName;
    private String eventDescription;
    private Date eventDate;
    private Date endDate;
    private Date createDate;
    private String createdBy;

    public EventDetails(String organiserId,String eventName, String eventDescription, Date eventDate,
                        Date endDate, Date createDate, String createdBy) {
        super();
        this.organiserId=organiserId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.endDate = endDate;
        this.createDate=createDate;
        this.createdBy = createdBy;
    }


    public String getOrganiserId() {
        return organiserId;
    }

    public void setOrganiserId(String organiserId) {
        this.organiserId = organiserId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }



    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}