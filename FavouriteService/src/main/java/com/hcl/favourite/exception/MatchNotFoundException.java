package com.hcl.favourite.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND , reason="Match not found...!!")
public class MatchNotFoundException extends Exception {

	private static final long serialVersionUID = -4694320752139895587L;

}

