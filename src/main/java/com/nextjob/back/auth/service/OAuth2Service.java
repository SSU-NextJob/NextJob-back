package com.nextjob.back.auth.service;

import com.nextjob.back.auth.web.GoogleLoginResult;
import com.nextjob.back.auth.web.GoogleTokenResponse;
import com.nextjob.back.auth.web.GoogleUserInfo;
import com.nextjob.base.util.CamelCaseMap;

public interface OAuth2Service {

    /* Google 인가 코드를 사용하여 액세스 토큰을 요청하고 사용자 정보를 가져옵니다. */
    GoogleLoginResult getGoogleUserInfo(String code);

    /* Google 인증 서버에 액세스 토큰을 요청하는 메서드 */
    GoogleTokenResponse requestAccessToken(String code);

    /* 액세스 토큰으로 Google 사용자 정보를 요청하는 메서드 */
    GoogleUserInfo requestUserInfo(String accessToken);

    /* Google 액세스 토큰을 무효화하는 메서드 (로그아웃 기능) */
    void revokeAccessToken(CamelCaseMap user);
}