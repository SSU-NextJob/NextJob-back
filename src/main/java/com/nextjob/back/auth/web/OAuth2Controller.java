package com.nextjob.back.auth.web;

import com.nextjob.back.auth.service.OAuth2Service;
import com.nextjob.back.user.service.UserService;
import com.nextjob.base.util.CamelCaseMap;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/oauth2")
public class OAuth2Controller {

    private final OAuth2Service oauth2Service;
    private final UserService userService;

    public OAuth2Controller(OAuth2Service oauth2Service, UserService userService) {
        this.oauth2Service = oauth2Service;
        this.userService = userService;
    }

    /**
     * 프론트엔드로부터 인가 코드를 받아 로그인 처리를 시작하는 엔드포인트
     *
     * @param code Google 인가 코드
     * @return 로그인한 사용자 정보
     */
    @GetMapping("/google/callback")
    public ApiResponse<CamelCaseMap> googleLogin(@RequestParam("code") String code) {
        System.out.println("[0] 인가코드: " + code);
        // 인가코드로 유저 정보 반환
        GoogleUserInfo userInfo = oauth2Service.getGoogleUserInfo(code);
        System.out.println("[2] 사용자 정보: " + userInfo);

        // 사용자 조회
        CamelCaseMap user = userService.findUserDetailByEmail(userInfo.getEmail());

        // 사용자 없으면 회원가입 진행
        if (user == null) {
            userService.createUser(userInfo.getName(), userInfo.getEmail());
            user = userService.findUserDetailByEmail(userInfo.getEmail());
        }

        return ApiResponse.ok(user);
    }

}
