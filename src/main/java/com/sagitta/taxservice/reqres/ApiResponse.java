package com.sagitta.taxservice.reqres;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiResponse <T, E> implements Serializable {
	
	private String message;
	private T data;
	private E errors;
}