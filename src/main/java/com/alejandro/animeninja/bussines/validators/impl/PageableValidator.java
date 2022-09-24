package com.alejandro.animeninja.bussines.validators.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.data.domain.Pageable;

import com.alejandro.animeninja.bussines.annotation.PageableConstraint;

public class PageableValidator implements ConstraintValidator<PageableConstraint,Pageable> {

	@Override
	public boolean isValid(Pageable value, ConstraintValidatorContext context) {
		if(value.getPageSize() < 24 || value.getPageNumber() < 0) {
			System.out.println("aaaaaa");
			return false;
		}
		System.out.println("aaaaaaB");
		return true;
	}



}
