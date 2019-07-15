package com.xiaowu.security.core.social;

import com.xiaowu.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * SpringSocial配置文件
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

//    @Autowired
//    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 注册一个bean
     * xiaowuSocialSecurityConfig
     * @return
     */
    @Bean
    public SpringSocialConfigurer xiaowuSocialSecurityConfig(){
        String filterProcessUrl = securityProperties.getSocial().getFilterProcessesUrl();
        XiaoWuSpringSocialConfigurer xiaoWuSpringSocialConfigurer = new XiaoWuSpringSocialConfigurer(filterProcessUrl);
        // 设置我们自己的注册页
        xiaoWuSpringSocialConfigurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        return xiaoWuSpringSocialConfigurer;
//        return new SpringSocialConfigurer;
    }

    /**
     * 用来处理注册流程的工具类
     * 1. 注册时，如何拿到social的信息
     * 2. 注册完成了，传给springsocial
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
    }

}
