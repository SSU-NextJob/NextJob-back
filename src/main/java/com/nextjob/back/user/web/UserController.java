package com.nextjob.back.user.web;

import com.nextjob.back.image.service.ImageService;
import com.nextjob.back.project.service.ProjectService;
import com.nextjob.back.project.web.ProjectSearchCriteria;
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
import java.util.Objects;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final ProjectService projectService;
    private final ImageService imageService;

    public UserController(UserService userService, ProjectService projectService, ImageService imageService) {
        this.userService = userService;
        this.projectService = projectService;
        this.imageService = imageService;
    }

    /**
     * 사용자 목록 조회
     *
     * @param userType 사용자 개발 분야
     * @param search 사용자 검색
     * @return 사용자 개발 분야와 검색어 조건에 해당하는 사용자 목록을 반환
     */
    @GetMapping
    public ApiResponse<List<CamelCaseMap>> findUserList(
            @RequestParam(required = false) String userType,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
        userSearchCriteria.setUserType(userType);
        userSearchCriteria.setSearch(search);
        userSearchCriteria.setOffset((page - 1) * pageSize);
        userSearchCriteria.setLimit(pageSize);

        List<CamelCaseMap> userList = userService.findUserList(userSearchCriteria);
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
     * 사용자 정보 수정
     *
     * @param body(name, techStack, description, profileImage) 사용자 정보
     * @return userId에 해당하는 사용자 정보 수정 성공 여부
     */
    @PatchMapping("/me")
    public ApiResponse<CamelCaseMap> updateUser(@RequestBody Map<String, Object> body) {
        int userId = Integer.parseInt(body.get("userId").toString());
        String name = body.get("name").toString();
        System.out.println(name);
        String techStack = body.get("techStack").toString();
        System.out.println(techStack);
        String description = body.get("description").toString();
        System.out.println(description);
        String userType = body.get("userType").toString();
        System.out.println(userType);
        String profileImageUrl = body.get("profileImageUrl").toString();
        System.out.println(profileImageUrl);

        CamelCaseMap user = userService.findUserDetail(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }

        String pastProfileImageUrl = user.get("profileImage").toString();

        userService.updateUser(userId, name, techStack, description, userType, profileImageUrl);
        user = userService.findUserDetail(userId);

        // 이미지 변경되었으면 이전 이미지 S3에서 삭제처리
        if (!Objects.equals(user.getString("profileImage"), pastProfileImageUrl)) {
            imageService.deleteImage(pastProfileImageUrl);
        }

        return ApiResponse.ok(null);
    }

    /**
     * 사용자 노출 여부 수정
     *
     * @param body(userId, isVisible) 사용자 정보
     * @return userId에 해당하는 사용자 노출 여부 수정 성공 여부
     */
    @PatchMapping("/visibility")
    public ApiResponse<CamelCaseMap> updateUserVisibility(@RequestBody Map<String, Object> body) {
        int userId = Integer.parseInt(body.get("userId").toString());
        Boolean isVisible = Boolean.parseBoolean(body.get("isVisible").toString());

        CamelCaseMap user = userService.findUserDetail(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }

        userService.updateUserVisibility(userId, isVisible);

        return ApiResponse.ok(null);
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

    /**
     * 내 프로젝트 제안 시 보여줄 내가 생성한 프로젝트 목록 조회
     *
     * @param userId 로그인한 사람의 userId
     * @return 로그인한 사람이 생성한 프로젝트 목록을 List로 반환
     */
    @GetMapping("/{userId}/projects")
    public ApiResponse<List<CamelCaseMap>> findCreateProjectList(@PathVariable("userId") int userId) {
        ProjectSearchCriteria projectSearchCriteria = new ProjectSearchCriteria();
        projectSearchCriteria.setUserId(String.valueOf(userId));
        List<CamelCaseMap> createProjectList = projectService.findCreateProjectList(projectSearchCriteria);
        return ApiResponse.ok(createProjectList);
    }


}
