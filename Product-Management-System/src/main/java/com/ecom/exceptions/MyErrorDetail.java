package com.ecom.exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyErrorDetail {

	private LocalDateTime timestamp;
	private String message;
	private String description;
	
	
}
