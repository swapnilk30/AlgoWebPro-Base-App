package com.algowebpro.common.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

	@Builder.Default
	private LocalDateTime timestamp = LocalDateTime.now();
	private String message;
	private String details;
	private String errorCode;

	
	
}
