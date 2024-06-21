package com.org.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.org.binding.Contact;

@Service
public class ContactService {

	private String SAVE_AND_UPDATE_URL= "http://localhost:8080/saveAndUpdate";
	private String VIEW_BY_EMAIL_URL = "http://localhost:8080/view/{email}";
	private String DELETE_BY_EMAIL_URL = "http://localhost:8080/delete/{email}";
	private String VIEW_ALL_URL = "http://localhost:8080/view";
		
	public Contact saveAndUpdate(Contact contact)
	{
		WebClient client = WebClient.create();
		Contact con = client.post().uri(SAVE_AND_UPDATE_URL).bodyValue(contact).retrieve().bodyToMono(Contact.class).block();
		return con;
	}
	
	public Contact view(String email)
	{
		WebClient client = WebClient.create();
		Contact contact = client.get().uri(VIEW_BY_EMAIL_URL,email).retrieve().bodyToMono(Contact.class).block();
		return contact;
	}
	
	public String delete(String email)
	{
		WebClient client = WebClient.create();
		String msg = client.get().uri(DELETE_BY_EMAIL_URL,email).retrieve().bodyToMono(String.class).block();
		return msg;
	}
	
	public List<Contact> viewAll()
	{
		WebClient client = WebClient.create();
		List<Contact> contacts = client.get().uri(VIEW_ALL_URL).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(new ParameterizedTypeReference<List<Contact>>() {}).block();
		return contacts;
	}
}
