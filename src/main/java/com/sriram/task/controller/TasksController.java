package com.sriram.task.controller;

import com.sriram.core.response.BaseRestResponse;
import com.sriram.core.response.PageableResponse;
import com.sriram.task.dto.TaskFilters;
import com.sriram.task.enums.TaskStatus;
import com.sriram.task.model.Task;
import com.sriram.task.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/task")
@AllArgsConstructor
@CrossOrigin("*")
public class TasksController {

    @GetMapping
    public PageableResponse<Task> getTasks(@ModelAttribute TaskFilters requestParameters, @PageableDefault
        Pageable pageable) {
        return new PageableResponse<>(true, HttpStatus.OK.toString(),
            taskService.getTasks(requestParameters, pageable));
    }

    @GetMapping("/{id}")
    public BaseRestResponse<Task> getTaskById(@PathVariable Long id) {
        BaseRestResponse<Task> response = new BaseRestResponse<>();
        response.setOk(true);
        response.setResult(taskService.getTaskById(id));
        return response;
    }

    @PostMapping
    public BaseRestResponse<Task> createTask(@RequestBody Task task) {
        BaseRestResponse<Task> response = new BaseRestResponse<>();
        response.setOk(true);
        response.setCode(HttpStatus.CREATED.toString());
        response.setResult(taskService.createTask(task));
//        try {
//            response.setResult(taskService.createTask(task));
//        }catch (Exception e) {
//            log.error("error {}", e.getMessage());
////            response.setError(e.getMessage());
//            response.setOk(false);
//        }
        return response;
    }

    @PatchMapping("/{id}/status")
    public BaseRestResponse<Task> changeStatus(@PathVariable Long id, @RequestBody Task request) {
        BaseRestResponse<Task> response = new BaseRestResponse<>();
        response.setOk(true);
        response.setResult(taskService.changeStatus(id, request));
        return response;
    }

    private TaskService taskService;
}
