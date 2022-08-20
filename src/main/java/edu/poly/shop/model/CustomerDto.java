package edu.poly.shop.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
	
	
	private Long customerId;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String phone;
	
	private Date registeredDate;
	
	private Boolean isEdit;
	
	
}
