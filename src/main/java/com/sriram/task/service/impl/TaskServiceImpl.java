package com.sriram.task.service.impl;

import com.sriram.task.repository.TaskRepository;
import com.sriram.task.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
}
