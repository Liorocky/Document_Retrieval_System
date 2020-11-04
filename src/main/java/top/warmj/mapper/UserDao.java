package top.warmj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.warmj.model.entity.UserDO;

/**
 * @author WarMj
 * @ClassName UserDao.java
 * @Description TODO
 * @createTime 2020年11月04日 14:48:00
 */
@Mapper
@Repository
public interface UserDao {
    UserDO queryUserByName(String name);
}
