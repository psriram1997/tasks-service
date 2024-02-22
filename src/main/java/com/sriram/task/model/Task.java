package com.sriram.task.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sriram.core.model.AbstractBaseModel;
import com.sriram.task.enums.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Task extends AbstractBaseModel {

    @NotBlank(message = "Name cannot be blank")
    private String title;

    private String description;

    @NotNull(message = "Status cannot be blank")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

}
