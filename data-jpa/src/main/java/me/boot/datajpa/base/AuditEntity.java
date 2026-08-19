package me.boot.datajpa.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 审计基类
 *
 * @since 2022/09/26
 */
@ToString
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false, columnDefinition = "timestamp")
    private LocalDateTime createTime;

    @CreatedBy
    @Column(nullable = false, updatable = false, length = 30)
    private String creator;

    @LastModifiedDate
    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime updateTime;

    @LastModifiedBy
    @Column(nullable = false, length = 30)
    private String updater;
}
