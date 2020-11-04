package top.warmj.service;

import top.warmj.model.entity.UserDO;

/**
 * @author WarMj
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2020年11月04日 14:45:00
 */
public interface UserService {
    public UserDO queryUserByName(String name);
}

