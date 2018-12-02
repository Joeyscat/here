package fun.oook.here.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Joey
 * @date 2018-12-02
 * @since 1.0
 */
@Entity
@Table(name = "position")
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper =true)
@NoArgsConstructor
@AllArgsConstructor
public class Position extends BaseEntity implements Serializable {

    @Getter
    @Setter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @Column
    private String tag;

    @Getter
    @Setter
    @Column
    private String address;

    @Getter
    @Setter
    @Column(nullable = false)
    private String lng;

    @Getter
    @Setter
    @Column(nullable = false)
    private String lat;

}