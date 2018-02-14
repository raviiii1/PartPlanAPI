package com.party.plan.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/settings")
public class SettingsController {
	
	@PostMapping("/addNewMember")
    public String addNewMember() {
		 return "addNewMember";
    }
	
	@PostMapping("/removeMember")
    public String removeMember() {
		 return "removeMember";
    }
}
