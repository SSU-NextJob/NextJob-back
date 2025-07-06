package com.nextjob.back.user.web;

import com.nextjob.back.user.service.UserService;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
import com.nextjob.base.util.CamelCaseMap;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 사용자 목록 조회
     *
     * @param userType 사용자 개발 분야
     * @param search 사용자 검색
     * @return 사용자 개발 분야와 검색어 조건에 해당하는 사용자 목록을 반환
     */
    @GetMapping
    public ApiResponse<List<CamelCaseMap>> findUserList(@RequestParam(required = false) String userType, @RequestParam(required = false) String search) {
        List<CamelCaseMap> userList = userService.findUserList(userType, search);
        if(ObjectUtils.isEmpty(userList)) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        return ApiResponse.ok(userList);
    }

    /**
     * 사용자 상세 조회
     *
     * @param userId 사용자의 userId
     * @return userId에 해당하는 사용자 상세 정보를 반환
     */
    @GetMapping("/{userId}")
    public ApiResponse<Map<String, Object>> findUserDetail(@PathVariable("userId") int userId) {
        CamelCaseMap user = userService.findUserDetail(userId);
        if(ObjectUtils.isEmpty(user)) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        return ApiResponse.ok(user);
    }

    /**
     * 프로젝트 제안(사용자한테 요청)
     *
     * @param userId 제안 받을 사람의 userId
     * @param body projectId를 담고 있음
     * @return 프로젝트 제안 테이블에 insert 후 알림 테이블에 insert한 결과를 boolean 타입으로 반환
     */
    @PostMapping("/{userId}/suggest")
    public ApiResponse<CamelCaseMap> suggestToUser(@PathVariable("userId") int userId, @RequestBody Map<String, Object> body) {
        Map<String, Object> result = new HashMap<>();
        int projectId = Integer.parseInt(body.get("projectId").toString());
        boolean data = userService.suggestToUser(userId, projectId);

        result.put("success", data);
        return ApiResponse.ok(null);
    }


}
