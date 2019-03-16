package fun.oook.here.entity.shiro;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Joey
 * @version 1.0
 * @since 2019/3/13 0:03
 */
@Entity
@Data
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue
    private Integer uid;

    /**
     * 账号
     */
    @Column(unique = true)
    private String username;

    /**
     * 名称/昵称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密密码的盐
     */
    private String salt;

    /**
     * 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
     */
    private byte state;

    /**
     * 拥有的角色(一个用户具有多个角色,一个角色可赋予不同用户)
     * <p>
     * FetchType.EAGER: 立即从数据库中进行加载数据;
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "uid")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roleList;


    /**
     * 密码盐
     *
     * @return cred salt
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }
}
