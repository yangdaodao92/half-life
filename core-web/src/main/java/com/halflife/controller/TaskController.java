package com.halflife.controller;

import com.halflife.bean.Task;
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

	@RequestMapping("list")
	public String list() {

		return "/task/task";
	}

	@RequestMapping("add")
	@ResponseBody
	public ResultVo add(@RequestBody Task task, Model model, HttpSession session) throws IOException {
		System.out.println(session.getAttribute("userId"));
		taskService.add(task);
		return new ResultVo();
	}

}
