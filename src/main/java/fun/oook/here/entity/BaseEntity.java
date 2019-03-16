package fun.oook.here.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * BaseEntity
 * <p>
 * {@link MappedSuperclass} 注解表明这是一个父类,其映射信息应用于从其继承的实体。
 * JAP不会为该类生成表结构
 *
 * @author Joey
 * @date 2018-12-02
 * @since 1.0
 */
@Data
@MappedSuperclass
@NoArgsConstructor
abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @CreationTimestamp
    private Timestamp createdTime;

    @Column
    @UpdateTimestamp
    private Timestamp updatedTime;

    @Column
    private String createdBy;

    @Column
    private boolean deleted;
}