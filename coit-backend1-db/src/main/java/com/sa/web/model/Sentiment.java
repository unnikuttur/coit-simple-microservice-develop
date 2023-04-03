package com.sa.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sentiment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;
    private float polariy;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public float getPolariy() {
		return polariy;
	}
	public void setPolariy(float polariy) {
		this.polariy = polariy;
	}
    
    
    

}
