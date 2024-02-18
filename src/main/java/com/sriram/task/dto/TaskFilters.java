package com.sriram.task.dto;

import com.sriram.task.enums.TaskStatus;
import com.sriram.task.model.Task;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskFilters implements Serializable, Specification<Task> {

    private TaskStatus status;

    @Override
    public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder) {
        if(!ObjectUtils.isEmpty(status)){
            return criteriaBuilder.equal(root.get("status"), status);
        }
        return null;
    }
}
