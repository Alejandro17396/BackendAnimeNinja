package com.alejandro.animeninja.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.services.BonusServices;

@RestController
@RequestMapping("/bonuses")
public class BonusController {
	
	@Autowired
	private BonusServices bonusServices;
	
	@GetMapping
	public List<Bonus> getAll(){
		return bonusServices.getAll();
	}

}
