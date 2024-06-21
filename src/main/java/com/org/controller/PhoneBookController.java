package com.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.binding.Contact;
import com.org.service.ContactService;

@Controller
public class PhoneBookController {
	
	@Autowired
	ContactService service;

	@GetMapping("/")
	public String loadForm()
	{
		return "index";
	}
	
	@GetMapping("/register")
	public String register(Model model)
	{
		model.addAttribute("contact",new Contact());
		return "register";
	}
	
	@PostMapping(value="/save")
	public String saveAndUpdate(@ModelAttribute("contact") Contact contact, Model model)
	{
		System.out.println(contact);
		Contact con = service.saveAndUpdate(contact);
		if(con != null)
		{
			model.addAttribute("status","Contact saved...");
			return "register";
		}
		model.addAttribute("status","Something went wrong!!!");
		return "register";
	}
	
	@GetMapping("/loadUpdatePage")
	public String loadUpdatePage()
	{
		return "update";
	}
	
	@PostMapping("/update")
	public String update(@RequestParam String email, Model model)
	{
		Contact contact = service.view(email);
		model.addAttribute("contact",contact);
		return "register";
	}
	
	@GetMapping("/loadDeletePage")
	public String loadDeletePage()
	{
		return "delete";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam String email, Model model)
	{
		String msg = service.delete(email);
		model.addAttribute("msg",msg);
		return "delete";
	}
	
	@GetMapping("/loadViewPage")
	public String loadViewPage()
	{
		return "view";
	}
	
	@PostMapping("/view")
	public String viewContact(@RequestParam String email, Model model)
	{
		Contact contact = service.view(email);
		model.addAttribute("contact",contact);
		return "viewContact";
	}
	
	@GetMapping("/loadViewAllPage")
	public String loadViewAllPage(Model model)
	{
		List<Contact> contacts = service.viewAll();
		model.addAttribute("contacts",contacts);
		return "viewAll";
	}
}
