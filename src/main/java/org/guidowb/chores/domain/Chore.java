package org.guidowb.chores.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Chore {

    private @Id @GeneratedValue long id;
    private String name;
    private String description;
    private Long assignedTo;
    private Date nextDue;
    private Boolean[] dueDays = new Boolean[7];

    public long getId() {
    	return id;
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Long userId) {
		this.assignedTo = userId;
	}

	public Date getNextDue() {
		return nextDue;
	}

	public void setNextDue(Date nextDue) {
		this.nextDue = nextDue;
	}    

	public Boolean[] getDueDays() {
		return dueDays;
	}

	public void setDueDays(Boolean[] dueDays) {
		this.dueDays = dueDays;
	}
}
