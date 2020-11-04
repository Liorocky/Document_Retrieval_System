package top.warmj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.warmj.mapper.UserDao;
import top.warmj.model.entity.UserDO;
import top.warmj.service.UserService;

/**
 * @author WarMj
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2020年11月04日 14:47:00
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDO queryUserByName(String name) {
        return userDao.queryUserByName(name);
    }

}
