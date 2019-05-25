package com.tordot.tour.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;


@Data
public class Message {
	@Id
	private String id;
	private String description;
	private Date date;
	private String username;

	
	
}
