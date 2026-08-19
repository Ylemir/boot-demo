package me.boot.datajpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import me.boot.datajpa.base.AuditEntity;
import me.boot.datajpa.constant.Gender;
import me.boot.datajpa.converter.StrListConverter;
import me.boot.datajpa.generator.AutoUUID;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Formula;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true, fluent = true)
@Table(
    name = "t_user",
    uniqueConstraints = @UniqueConstraint(name = "uk_user_name", columnNames = "name")
)
@DynamicInsert
public class User extends AuditEntity {

    @Id
    @AutoUUID
    @Column(length = 50)
    private String id;

    @Column(length = 30, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'UNKNOWN'")
    @Column(length = 10)
    private Gender gender;

    @Column(name = "birthday")
    private LocalDate birth;

    @Convert(converter = StrListConverter.class)
    @Column(columnDefinition = "text")
    private List<String> roles;

    private boolean online;

   @Formula("cast(strftime('%Y.%m%d', 'now') - strftime('%Y.%m%d', birthday) as 'int')")
   private Integer age;
}