package com.hndfsj.springboot.config;

import com.hndfsj.springboot.admin.domain.User;
import com.hndfsj.springboot.admin.service.UserService;
import com.hndfsj.springboot.framework.security.LoginAuthenticationProvider;
import com.hndfsj.springboot.framework.security.LoginSuccessHandler;
import com.hndfsj.springboot.framework.security.MSUserDetailsService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * <pre>
 * TODO：
 * </pre>
 *
 * @author MS
 * @date 2019/7/24
 */
@Configurable
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    LoginAuthenticationProvider loginAuthenticationProvider;
    @Resource
    LoginSuccessHandler loginSuccessHandler;

    @Resource
    private MSUserDetailsService<User> mSUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//                http
//                .authorizeRequests()
//                .antMatchers("/static/**","/register").permitAll()
////                .antMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login");
        loginSuccessHandler.setDefaultTargetUrl("/register");
        http
                .authorizeRequests()
                .antMatchers("/static/**","/register").permitAll()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(loginSuccessHandler)
                .failureUrl("/login?error").permitAll()
                .and()
                .logout().logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies()
                .permitAll()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //  auth.authenticationProvider(loginAuthenticationProvider);
          auth.userDetailsService(mSUserDetailsService);
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("testgly").password(new BCryptPasswordEncoder().encode("111111")).roles();
    }
}
