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
@Data
@Entity
@Table(name = "position")
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper =true)
@NoArgsConstructor
public class Position extends BaseEntity implements Serializable {

    @Column(nullable = false)
    private String name;

    @Column
    private String tag;

    @Column
    private String address;

    @Column(nullable = false)
    private String lng;

    @Column(nullable = false)
    private String lat;

}