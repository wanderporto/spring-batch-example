package com.example.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.model.User;

@Component
public class Processor implements ItemProcessor<User, User> {

	private static final Map<String, String> DEPT_NAME = new HashMap<>();

	public Processor() {
		DEPT_NAME.put("001", "tecnology");
		DEPT_NAME.put("002", "Operations");
		DEPT_NAME.put("003", "accounts");
	}

	@Override
	public User process(User item) throws Exception {
		String deptCod = item.getDept();
		String dept = DEPT_NAME.get(deptCod);
		item.setDept(dept);
		System.out.println(String.format("converted from [%s] to [%s]", deptCod, dept));
		return item;
	}

}
