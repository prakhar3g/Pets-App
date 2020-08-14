package com.gemini.event.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name="event")
public class Event {



    @Id
    @Column(name="event_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eventId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "description")
    private String eventDescription;


    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "organiser_id")
    private EventOrganiser organiser;

    @Column(name = "event_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private java.util.Date eventDate;



    @Column(name = "end_date", nullable = true)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private java.util.Date endDate;

    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private java.util.Date createDate;

    @Column(name = "created_by")
    private String createdBy;




    public Event() {
    }

    public Event(int eventId) {
        this.eventId = eventId;
    }

    public Event(String organiserId,String eventName, String eventDescription, java.util.Date eventDate,
                 java.util.Date endDate, java.util.Date createDate, String createdBy) {
        this.organiser= new EventOrganiser(organiserId);
        this.eventName=eventName;
        this.eventDescription=eventDescription;
        this.eventDate=eventDate;
        this.endDate=endDate;
        this.createDate=createDate;
        this.createdBy=createdBy;



    }



    public int getEventId() {
        return eventId;
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

    public EventOrganiser getOrganiser() {
        return organiser;
    }

    public void setOrganiser(EventOrganiser organiser) {
        this.organiser = organiser;
    }

    public java.util.Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(java.util.Date eventDate) {
        this.eventDate = eventDate;
    }

    public java.util.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


}
