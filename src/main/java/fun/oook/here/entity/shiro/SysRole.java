package fun.oook.here.entity.shiro;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Joey
 * @version 1.0
 * @since 2019/3/13 0:16
 */
@Entity
@Data
public class SysRole implements Serializable {

    /**
     * 编号
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 角色标识程序中判断使用,如"admin",这个是唯一的
     */
    private String role;

    /**
     * 角色描述,UI界面使用
     */
    private String description;

    /**
     * 是否可用,如果不可用将不能添加给用户
     */
    private Boolean available = Boolean.FALSE;


    /**
     * 角色--权限 关系:多对多关系
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<SysPermission> permissions;

    /**
     * 用户 - 角色关系定义 一个角色对应多个用户
     */
    @ManyToMany
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<UserInfo> userInfos;
}
