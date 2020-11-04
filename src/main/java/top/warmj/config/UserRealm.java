package top.warmj.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import top.warmj.model.entity.UserDO;
import top.warmj.service.UserService;

/**
 * @author WarMj
 * @ClassName UserRelam.java
 * @Description TODO
 * @createTime 2020年11月04日 14:40:00
 */

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        UserDO user = userService.queryUserByName(userToken.getUsername());
        if (user == null) { //无此用户
            return null;
        }

        //密码认证
        return new SimpleAuthenticationInfo("", user.getPwd(), "");
    }
}
