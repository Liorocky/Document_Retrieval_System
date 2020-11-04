package top.warmj.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author WarMj
 * @ClassName ShiroConfig.java
 * @Description TODO
 * @createTime 2020年11月04日 14:40:00
 */
@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean (@Qualifier("getSecurityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);

        //添加shiro的内置过滤器
        /*
            anon: 无需认证
            authc: 必须认证才能访问
            user: 必须拥有 记住我 功能才能访问
            perms: 拥有对某个资源的权限才能访问
            role: 拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/", "authc");
        filterMap.put("/index", "authc");
        filterMap.put("/retrieve-document", "authc");
        filterMap.put("/show-file-box", "authc");
        filterMap.put("/upload-document", "authc");
        filterMap.put("/fileBox", "authc");
        filterMap.put("/file", "authc");
        filterMap.put("/relation", "authc");
        filterMap.put("/tag", "authc");

        filterMap.put("/logout", "logout");

        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录的页面
        bean.setLoginUrl("/toLogin");

        return bean;
    }

    //DefaultWebSecuitryManager
    @Bean
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //关联Realm
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 设置session 过期时间 1天
        sessionManager.setGlobalSessionTimeout(60000 * 1 * 60);
        return sessionManager;
    }

    //创建realm对象
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
