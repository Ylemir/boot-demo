package me.boot.datajpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import me.boot.datajpa.base.SoftDeleteEntity;
import me.boot.datajpa.base.VersionId;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

/**
 * website实体
 *
 * @since 2022/09/26
 */
@Getter
@Setter
@ToString
@Accessors(chain = true, fluent = true)
@Entity
@IdClass(VersionId.class)
@Table(name = "t_website")
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "update t_website set is_deleted = true where version = ? and id = ?")
public class WebSite extends SoftDeleteEntity {

    @Id
    @Column(length = 50)
    private String id;

    @Id
    @Column(length = 10, nullable = false)
    private String version;

    @Column(length = 50)
    private String name;

    @Column(length = 500, nullable = false)
    private String url;

    private String description;
}
