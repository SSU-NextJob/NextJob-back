package com.nextjob.back.user.web;

import com.nextjob.back.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    /**
     * 사용자 목록 조회
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String findUser(Model model) {
        return null;
    }

    /**
     * 사용자 상세 조회
     *
     * @param userSearchCriteria
     * @param model
     * @return
     */
    public Model findUserDetail(UserSearchCriteria userSearchCriteria, Model model) {
        model.addAttribute("userDetail", userService.findUserDetail(userSearchCriteria));
        return model;
    }


}
