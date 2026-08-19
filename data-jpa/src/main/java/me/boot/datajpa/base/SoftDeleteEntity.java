package me.boot.datajpa.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@MappedSuperclass
public class SoftDeleteEntity extends AuditEntity {

    @Column(name = "is_deleted")
    private boolean deleted;

}
