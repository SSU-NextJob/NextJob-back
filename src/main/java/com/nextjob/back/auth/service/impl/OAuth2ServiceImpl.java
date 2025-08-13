package com.nextjob.back.auth.service.impl;

import com.nextjob.back.auth.service.OAuth2Service;
import com.nextjob.back.auth.web.GoogleLoginResult;
import com.nextjob.back.auth.web.GoogleTokenResponse;
import com.nextjob.back.auth.web.GoogleUserInfo;
import com.nextjob.back.user.service.UserService;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
import com.nextjob.base.util.CamelCaseMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class OAuth2ServiceImpl implements OAuth2Service {

    private final UserService userService;

    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @Value("${google.redirect.uri}")
    private String redirectUri;

    private final WebClient webClient;

    public OAuth2ServiceImpl(WebClient.Builder webClientBuilder, UserService userService) {
        this.webClient = webClientBuilder.baseUrl("https://oauth2.googleapis.com").build();
        this.userService = userService;
    }

    /**
     * Google 인가 코드를 사용하여 액세스 토큰을 요청하고 사용자 정보를 가져옵니다.
     *
     * @param code 프론트엔드에서 받은 Google 인가 코드
     * @return Google 사용자 정보 DTO
     */
    public GoogleLoginResult getGoogleUserInfo(String code) {
        // 1. 인가 코드로 액세스 토큰 요청
        GoogleTokenResponse tokenResponse = requestAccessToken(code);
        String accessToken = tokenResponse.getAccessToken();

        // 2. 액세스 토큰으로 사용자 정보 요청
        GoogleUserInfo userInfo = requestUserInfo(accessToken);

        return new GoogleLoginResult(userInfo, accessToken, tokenResponse.getExpiresIn());
    }

    /**
     * Google 인증 서버에 액세스 토큰을 요청하는 메서드
     *
     * @param code 인가 코드
     * @return 액세스 토큰 응답 객체
     */
    public GoogleTokenResponse requestAccessToken(String code) {
        return this.webClient.post()
                .uri("/token")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters.fromFormData("code", code)
                        .with("client_id", clientId)
                        .with("client_secret", clientSecret)
                        .with("redirect_uri", redirectUri)
                        .with("grant_type", "authorization_code"))
                .retrieve()
                .bodyToMono(GoogleTokenResponse.class)
                .block(); // 비동기 호출을 동기식으로 처리
    }

    /**
     * 액세스 토큰으로 Google 사용자 정보를 요청하는 메서드
     *
     * @param accessToken 액세스 토큰
     * @return Google 사용자 정보 DTO
     */
    public GoogleUserInfo requestUserInfo(String accessToken) {
        WebClient userInfoClient = WebClient.builder().baseUrl("https://www.googleapis.com").build();
        return userInfoClient.get()
                .uri("/oauth2/v3/userinfo")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(GoogleUserInfo.class)
                .block(); // 비동기 호출을 동기식으로 처리
    }

    /**
     * Google 액세스 토큰을 무효화하는 메서드 (로그아웃 기능)
     *
     * @param user 무효화할 사용자
     */
    public void revokeAccessToken(CamelCaseMap user) {
        String googleAccessToken = user.getString("accessToken");

        if (googleAccessToken == null) {
            throw new CustomException(ErrorCode.ACCESS_TOKEN_NOT_FOUND);
        }

        // 토큰 무효화 요청
        WebClient revocationClient = WebClient.builder().baseUrl("https://oauth2.googleapis.com").build();
        try {
            revocationClient.post()
                    .uri("/revoke")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .body(BodyInserters.fromFormData("token", googleAccessToken))
                    .retrieve()
                    .toBodilessEntity()
                    .block();

            // DB에서 Google 액세스 토큰 정보 제거
            userService.updateUserAccessToken(user.getInt("userId"), null);
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Failed to revoke Google access token: " + e.getResponseBodyAsString(), e);
        }
    }
}