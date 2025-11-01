package com.nextjob.back.schedule.web;

import com.nextjob.back.schedule.service.ScheduleService;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
import com.nextjob.base.util.CamelCaseMap;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 일정 목록 조회
     *
     * @param
     * @return
     */
    @GetMapping
    public ApiResponse<List<CamelCaseMap>> findScheduleList(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        ScheduleSearchCriteria scheduleSearchCriteria = new ScheduleSearchCriteria();
        scheduleSearchCriteria.setStartDate(startDate);
        scheduleSearchCriteria.setEndDate(endDate);
        List<CamelCaseMap> scheduleList = scheduleService.findScheduleList(scheduleSearchCriteria);

        if(ObjectUtils.isEmpty(scheduleList)) {
            scheduleList = new ArrayList<>();
        }
        return ApiResponse.ok(scheduleList);
    }

    /**
     * 일정 생성
     *
     * @param scheduleSearchCriteria
     * @return
     */
    @PostMapping("/insert")
    public ApiResponse<Map<String, Object>> insertSchedule(@RequestBody ScheduleSearchCriteria scheduleSearchCriteria) {
        Map<String, Object> result = new HashMap<>();
        result = scheduleService.insertSchedule(scheduleSearchCriteria);
        return ApiResponse.ok(result);
    }

    /**
     * 일정 수정
     *
     * @param scheduleId
     * @param scheduleSearchCriteria
     * @return
     */
    @PutMapping("/{scheduleId}")
    public ApiResponse<Map<String, Object>> updateSchedule(
            @PathVariable("scheduleId") int scheduleId,
            @RequestBody ScheduleSearchCriteria scheduleSearchCriteria) {
        Map<String, Object> result = new HashMap<>();
        scheduleSearchCriteria.setScheduleId(scheduleId);
        result = scheduleService.updateSchedule(scheduleSearchCriteria);
        return ApiResponse.ok(result);
    }

    /**
     * 일정 상세 조회 API
     *
     * @param scheduleId
     * @return
     */
    @GetMapping("/{scheduleId}")
    public ApiResponse<Map<String, Object>> findScheduleDetail(
            @PathVariable("scheduleId") int scheduleId) {
        Map<String, Object> result = scheduleService.findScheduleDetail(scheduleId);
        if (result.isEmpty()) {
            throw new CustomException(ErrorCode.SCHEDULE_NOT_FOUND);
        }
        return ApiResponse.ok(result);
    }

    /**
     * 일정 삭제
     *
     * @param scheduleId
     * @return
     */
    @DeleteMapping("/{scheduleId}")
    public ApiResponse<Map<String, Object>> deleteSchedule(
            @PathVariable("scheduleId") int scheduleId) {
        Map<String, Object> result = scheduleService.deleteSchedule(scheduleId);
        return ApiResponse.ok(result);
    }

}
