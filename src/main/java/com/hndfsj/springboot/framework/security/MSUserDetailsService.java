package com.hndfsj.springboot.framework.security;

import com.hndfsj.springboot.admin.domain.User;
import com.hndfsj.springboot.admin.service.RoleService;
import com.hndfsj.springboot.admin.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <pre>
 * TODO：
 * </pre>
 *
 * @author MS
 * @date 2019/7/29
 */
@Service
public class MSUserDetailsService<T extends User> implements UserDetailsService {
    @Resource
    UserService userService;
    @Resource
    RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        User user=null;
//        try {
           user= userService.selectOne(new User(){{setUsername(username);}});
//        } catch (Exception e) {
//            throw new UsernameNotFoundException("用户名不能为空");
//        }
        return new MSUserDetails(user,null);
    }
}
