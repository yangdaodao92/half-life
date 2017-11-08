package com.halflife.service;

import com.halflife.bean.CurrentTask;
import com.halflife.bean.Task;
import com.halflife.repository.CurrentTaskRepository;
import com.halflife.repository.TaskRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private CurrentTaskRepository currentTaskRepository;

	public void add(Task task) {
		taskRepository.save(task);
	}

	public List<Task> listWeek() {
		return taskRepository.findAll();
	}

	public List<Task> listToday() {
		List<CurrentTask> currentTaskList = currentTaskRepository.findAll();
		List<ObjectId> objectIdList = currentTaskList.stream().map(CurrentTask::getCurrentTaskId).collect(Collectors.toList());
		return taskRepository.findByIdIn(objectIdList);
	}

	public void moveToToday(CurrentTask currentTask) {
		currentTaskRepository.save(currentTask);
	}

	public void removeFromToday(CurrentTask currentTask) {
		taskRepository.delete(currentTask.getCurrentTaskId());
	}

}
