package com.gemini.event.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


import java.io.Serializable;
import java.util.Date;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="organiser")
public class EventOrganiser {


    @Id
    @Column(name = "organiser_id")
    private String organiserId;

    @Column(name = "organiser_name")
    private String organiserName;

    @JsonIgnore
    @OneToMany(mappedBy = "organiser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Event> eventList;


    public EventOrganiser() {
    }

    public EventOrganiser(String organiserId) {
        this.organiserId = organiserId;
    }



    public String getOrganiserId() {
        return organiserId;
    }


    public void setOrganiserId(String organiserId) {
        this.organiserId = organiserId;
    }

    public String getOrganiserName() {
        return organiserName;
    }

    public void setOrganiserName(String organiserName) {
        this.organiserName = organiserName;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }


}
