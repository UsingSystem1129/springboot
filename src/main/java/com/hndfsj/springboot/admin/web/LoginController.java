package com.hndfsj.springboot.admin.web;

import com.hndfsj.springboot.framework.base.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <pre>
 * TODO：
 * </pre>
 *
 * @author MS
 * @date 2019/7/25
 */
@Controller
public class LoginController extends BaseController  {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Map<String, Object> model) {

        model.put("message","welcome");
        return "login";
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(Map<String, Object> model) {
        model.put("message","welcome");
        return "register";
    }


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        return "index";
    }

}
