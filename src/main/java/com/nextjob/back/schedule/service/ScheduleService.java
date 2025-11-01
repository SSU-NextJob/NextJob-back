package com.nextjob.back.schedule.service;

import com.nextjob.back.schedule.web.ScheduleSearchCriteria;
import com.nextjob.base.util.CamelCaseMap;

import java.util.List;
import java.util.Map;

public interface ScheduleService {

    /* 일정 목록 조회 */
    List<CamelCaseMap> findScheduleList(ScheduleSearchCriteria scheduleSearchCriteria);

    /* 일정 생성 */
    Map<String, Object> insertSchedule(ScheduleSearchCriteria scheduleSearchCriteria);

    /* 일정 수정 */
    Map<String, Object> updateSchedule(ScheduleSearchCriteria scheduleSearchCriteria);

    /* 일정 상세 조회 */
    Map<String, Object> findScheduleDetail(int scheduleId);

    /* 일정 삭제 */
    Map<String, Object> deleteSchedule(int scheduleId);
}
