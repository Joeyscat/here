package fun.oook.here.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Joey
 * @version 1.0
 * @since 2018/12/3 19:30
 */
@Entity
@Table(name = "user")
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper =true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements Serializable {

    @Getter
    @Setter
    @Column(nullable = false)
    private String uid;

    @Getter
    @Setter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(nullable = false)
    private String password;

    @Getter
    @Setter
    @Column
    private String phone;

    @Getter
    @Setter
    @Column
    private String email;
}