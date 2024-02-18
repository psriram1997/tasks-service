package com.sriram.task.service.impl;

import com.sriram.core.exception.CustomException;
import com.sriram.task.dto.TaskFilters;
import com.sriram.task.model.Task;
import com.sriram.task.repository.TaskRepository;
import com.sriram.task.service.TaskService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Override
    public Page<Task> getTasks(TaskFilters requestParameters, Pageable pageable) {
        return taskRepository.findAll(requestParameters, pageable);
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Task not found");
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task changeStatus(Long id, Task task) {
        Task oldTask = getTaskById(id);
        oldTask.setStatus(task.getStatus());
        return taskRepository.save(oldTask);
    }

    private final TaskRepository taskRepository;
}
