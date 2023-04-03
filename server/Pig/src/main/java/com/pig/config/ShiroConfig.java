package com.pig.config;

import com.pig.custom.CustomRealm;
import com.pig.interceptor.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Blackke
 */
@Configuration
public class ShiroConfig {
    /**
     * 配置shiroFilter工厂
     */
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        //新建拦截过滤器的工厂类
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        // 添加自己的过滤器到ShiroFilterFactory里，并且取名为jwt
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", new JwtFilter());
        filterFactoryBean.setFilters(filterMap);
        //配置拦截规则，所有请求都通过我们自己的JWT Filter即可
          /*添加Shiro的内置过滤器
          /*anon：无需认证就可以访问
          authc：必须认证了才能让问
          user：必须拥有，记住我功能，才能访问
          perms：拥有对某个资源的权限才能访问
          role：拥有某个角色权限才能访问*/
        Map<String, String> filterRuleMap = new LinkedHashMap<>();
        filterRuleMap.put("/user/login", "anon");
        filterRuleMap.put("/user/register", "anon");
        filterRuleMap.put("/**/show/**", "anon");
        filterRuleMap.put("/swagger-ui/**", "anon");
        filterRuleMap.put("/**", "jwt");
        filterFactoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return filterFactoryBean;
    }

    /**
     * 配置web相关的SecurityManager
     *
     * @param customRealm 使用@Qualifier()按名称注入参数
     * @return DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("customRealm") CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm对象
        securityManager.setRealm(customRealm);
        //关闭shiro自带的session存储,实现无状态Token
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    /**
     * 配置自定义的 realm对象
     *
     * @return CustomRealm
     */
    @Bean("customRealm")
    public CustomRealm getRealm() {
        //这里不需要配置密码比对器了，默认即可
        return new CustomRealm();
    }
}
