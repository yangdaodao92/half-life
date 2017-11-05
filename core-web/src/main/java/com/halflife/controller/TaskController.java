package com.halflife.controller;

import com.halflife.document.CurrentTask;
import com.halflife.document.Task;
import com.halflife.service.TaskService;
import com.halflife.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("task")
public class TaskController {
	@Autowired
	private TaskService taskService;

	@RequestMapping
	public String init() {
		return "/task/task";
	}

	@RequestMapping("list/today")
	public String listToday(Model model) {
		model.addAttribute("listToday", taskService.listToday());

		return "/task/today";
	}

	@RequestMapping("list/week")
	public String listWeek(Model model) {
		model.addAttribute("listWeek", taskService.listWeek());

		return "/task/week";
	}

	@RequestMapping("list/all")
	public String listAll(Model model) {

		return "/task/task";
	}


	@RequestMapping("add")
	@ResponseBody
	public ResultVo add(@RequestBody Task task, HttpSession session) throws IOException {
		System.out.println(session.getAttribute("userId"));
		taskService.add(task);
		return new ResultVo();
	}

	@RequestMapping("moveToToday")
	@ResponseBody
	public ResultVo moveToToday(@RequestBody CurrentTask currentTask) throws IOException {
		taskService.moveToToday(currentTask);
		return new ResultVo();
	}

	@RequestMapping("removeFromToday")
	@ResponseBody
	public ResultVo removeFromToday(@RequestBody CurrentTask currentTask) throws IOException {
		taskService.removeFromToday(currentTask);

		return new ResultVo();
	}

}