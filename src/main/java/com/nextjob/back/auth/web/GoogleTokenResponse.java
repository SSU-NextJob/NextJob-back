// GoogleTokenResponse.java - 액세스 토큰 응답 DTO
package com.nextjob.back.auth.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// Lombok을 사용하여 Getter, Setter, toString 등을 자동 생성합니다.
@Data
public class GoogleTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("id_token")
    private String idToken;
}