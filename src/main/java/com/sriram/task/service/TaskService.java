package com.sriram.task.service;

import com.sriram.task.dto.TaskFilters;
import com.sriram.task.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    Page<Task> getTasks(TaskFilters requestParameters, Pageable pageable);

    Task getTaskById(Long id);

    Task createTask(Task task);

    Task changeStatus(Long id, Task task);
}
