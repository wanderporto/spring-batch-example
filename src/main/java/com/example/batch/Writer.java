package com.example.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.User;
import com.example.repository.UserRepository;

@Component
public class Writer implements ItemWriter<User>{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void write(List<? extends User> users) throws Exception {
		System.out.println("Data saved for Users:" + users);
		userRepository.saveAll(users);
		
	}

}
