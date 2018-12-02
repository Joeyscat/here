package fun.oook.here.config;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * @author Joey
 * @date 2018-12-02
 * @since 1.0
 */
public class MySQL5InnoDBDialectUtf8mb4 extends MySQL5Dialect {

    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci";
    }
}