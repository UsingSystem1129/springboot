package com.hndfsj.springboot.framework.security;

import com.hndfsj.springboot.admin.domain.User;
import com.hndfsj.springboot.admin.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 自定义登录验证
 * <pre>
 * TODO：
 * </pre>
 *
 * @author MS
 * @date 2019/7/24
 */
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("start login");
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (StringUtils.isBlank(username)) {
            throw new BadCredentialsException("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            throw new BadCredentialsException("密码不能为空");
        }
        User user = userService.selectOne(new User() {{
            setUsername(username);
            setPassword(password);
        }});
        return new UsernamePasswordAuthenticationToken(user,password,null);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
