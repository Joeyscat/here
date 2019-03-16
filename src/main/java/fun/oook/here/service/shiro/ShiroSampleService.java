package fun.oook.here.service.shiro;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

/**
 * @author Joey
 * @version 1.0
 * @since 2019/3/11 23:26
 */
@Service
public class ShiroSampleService {

    @RequiresPermissions("read")
    public String read() {
        return "reading";
    }

    @RequiresPermissions("write")
    public String write() {
        return "writing";
    }
}
