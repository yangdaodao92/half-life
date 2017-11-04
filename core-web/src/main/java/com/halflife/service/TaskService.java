package com.halflife.service;

import com.halflife.bean.Task;
import com.halflife.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;

	public void add(Task task) {
		taskRepository.save(task);
	}

}
