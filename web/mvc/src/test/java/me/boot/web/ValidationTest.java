package me.boot.web;

import com.google.common.collect.ImmutableList;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.groups.Default;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.group.GroupSequenceProvider;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;
import org.junit.jupiter.api.Test;

/**
 * ValidationTest
 *
 * @since 2024/05/13
 **/
public class ValidationTest {

    // ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).configure()
    //     .failFast(false).buildValidatorFactory();

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();


    @Test
    public void test() {

        TaskFlow taskFlow = TaskFlow.of(ImmutableList.of(new Task(),
            Task.of(Collections.singletonList("adc"), Collections.emptyList(), "mate",
                Collections.singletonList(TaskRole.of("te", "er"))),
            Task.of(Collections.emptyList(), null, "mate2",
                Collections.singletonList(new TaskRole())),
            Task.of(null, Collections.singletonList(" "), "mate3",
                Collections.singletonList(TaskRole.of("te", null)))));
        System.err.println(taskFlow);
        Set<ConstraintViolation<TaskFlow>> violations = validator.validate(taskFlow);

        violations.forEach(violation -> {
            System.out.printf("%s - %s - %s%n", violation.getRootBeanClass().getName(),
                violation.getPropertyPath(), violation.getMessage());
        });
    }


    @Data
    @AllArgsConstructor(staticName = "of")
    public static class TaskFlow {

        @NotEmpty
        List<@Valid Task> tasks;

    }

    @Data
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    @GroupSequenceProvider(value = TaskGroupSequenceProvider.class)
    public static class Task {

        @NotEmpty(groups = TaskGroup.class, message = "输入与输出至少需要一个")
        List<@NotBlank String> inputs;

        List<@NotBlank String> outputs;

        @NotBlank
        String name;

        List<@Valid TaskRole> roles;


        @AssertTrue
        public boolean hasBI() {
            return CollectionUtils.isNotEmpty(inputs) || CollectionUtils.isNotEmpty(outputs);
        }

    }

    @Data
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    @GroupSequenceProvider(value = RoleGroupSequenceProvider.class)
    public static class TaskRole {

        String type;

        @NotBlank(groups = RoleGroup.class, message = "显性类型，内容不能为空")
        String content;

    }


    public interface RoleGroup extends Default {

    }

    public interface TaskGroup extends Default {

    }

    public static class TaskGroupSequenceProvider implements DefaultGroupSequenceProvider<Task> {

        @Override
        public List<Class<?>> getValidationGroups(Class<?> clazz, Task task) {
            if (task != null && CollectionUtils.isEmpty(task.inputs) && CollectionUtils.isEmpty(
                task.outputs)) {
                System.err.println(task);
                return ImmutableList.of(TaskGroup.class, Task.class);
            }
            return ImmutableList.of(Task.class);
        }
    }

    public static class RoleGroupSequenceProvider implements
        DefaultGroupSequenceProvider<TaskRole> {

        @Override
        public List<Class<?>> getValidationGroups(Class<?> clazz, TaskRole role) {
            if (role != null && role.getType() != null) {
                System.err.println(role);
                return ImmutableList.of(RoleGroup.class, TaskRole.class);
            }
            return ImmutableList.of(TaskRole.class);
        }
    }
}
