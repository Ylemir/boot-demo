package me.boot.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * field difference
 *
 * @since 2024/01/07
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldDifference {

    private String property;

    private Object oldValue;

    private Object newValue;

}
