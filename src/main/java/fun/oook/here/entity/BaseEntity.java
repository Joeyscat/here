package fun.oook.here.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * BaseEntity
 *
 * {@link MappedSuperclass} 注解表明这是一个父类,其映射信息应用于从其继承的实体。
 * JAP不会为该类生成表结构
 *
 * @author Joey
 * @date 2018-12-02
 * @since 1.0
 */
@MappedSuperclass
abstract class BaseEntity implements Serializable {



    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Column
    @CreationTimestamp
    @Getter
    @Setter
    private Timestamp createdTime;

    @Column
    @UpdateTimestamp
    @Getter
    @Setter
    private Timestamp updatedTime;

    @Column
    @Getter
    @Setter
    private String createdBy;

    @Column
    @Getter
    @Setter
    private boolean deleted;
}