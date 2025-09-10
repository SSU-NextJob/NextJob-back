package com.nextjob.back.auth.web;

import com.nextjob.back.auth.service.OAuth2Service;
import com.nextjob.back.user.service.UserService;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
import com.nextjob.base.util.CamelCaseMap;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        try {
            // 인가코드로 유저 정보 반환
            GoogleLoginResult userResult = oauth2Service.getGoogleUserInfo(code);
            GoogleUserInfo userInfo = userResult.getUserInfo();
            String accessToken = userResult.getAccessToken();

            // 사용자 조회
            CamelCaseMap user = userService.findUserDetailByEmail(userInfo.getEmail());

            // 사용자 없으면 회원가입 진행
            if (user == null) {
                userService.createUser(userInfo.getName(), userInfo.getEmail());
                user = userService.findUserDetailByEmail(userInfo.getEmail());
            }

            // Google 액세스 토큰 저장 및 업데이트
            userService.updateUserAccessToken(user.getInt("userId"), accessToken);

            return ApiResponse.ok(user);
        }  catch (Exception e) {
            System.err.println(e.getMessage());
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 로그아웃 처리 엔드포인트
     * 백엔드에 저장된 Google 액세스 토큰을 무효화합니다.
     *
     * @param body 로그아웃할 사용자의 ID를 포함하는 JSON
     * @return 성공 여부
     */
    @PostMapping("/google/logout")
    public ApiResponse<String> googleLogout(@RequestBody Map<String, Object> body) {
        int userId = Integer.parseInt(body.get("userId").toString());
        CamelCaseMap user = userService.findUserDetail(userId);

        if (user == null || user.isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        oauth2Service.revokeAccessToken(user);
        return ApiResponse.ok(null);
    }
}
