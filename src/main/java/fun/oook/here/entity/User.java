package fun.oook.here.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @author Joey
 * @version 1.0
 * @since 2018/12/3 19:30
 */
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
}