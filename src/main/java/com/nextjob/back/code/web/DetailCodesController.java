package com.nextjob.back.code.web;

import com.nextjob.back.code.service.DetailCodesService;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
import com.nextjob.base.util.CamelCaseMap;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/detail")
public class DetailCodesController {

    private final DetailCodesService detailCodesService;

    public DetailCodesController(DetailCodesService detailCodesService) {
        this.detailCodesService = detailCodesService;
    }

    /**
     * 그룹코드로 상세코드 조회
     *
     * @param groupCode 상세코드를 가져올 그룹코드
     * @return 그룹코드로 조회된 상세코드 리스트 반환
     */
    @GetMapping("/codes")
    public ApiResponse<List<CamelCaseMap>> findDetailCodeList(@RequestParam String groupCode) {
        List<CamelCaseMap> detailCodeList = detailCodesService.findDetailCodeList(groupCode);
        if(ObjectUtils.isEmpty(detailCodeList)) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        return ApiResponse.ok(detailCodeList);
    }
}
