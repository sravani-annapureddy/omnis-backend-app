
package com.aja.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentRequestDto {
	
	private Long OrderId;
	private String paymentMethod;
	private Double amount;
	private String paymentStatus;
	private String transactionReference;
	
	

}
