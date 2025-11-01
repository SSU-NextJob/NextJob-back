package com.nextjob.back.schedule.service;

import com.nextjob.back.schedule.domain.Schedule;
import com.nextjob.back.schedule.domain.ScheduleUser;
import com.nextjob.back.schedule.web.ScheduleSearchCriteria;
import com.nextjob.base.util.CamelCaseMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ScheduleMapper {

    /* 일정 목록 조회 */
    List<CamelCaseMap> findScheduleList(ScheduleSearchCriteria scheduleSearchCriteria);

    /* 일정 생성 */
    int insertSchedule(Schedule schedule);

    /* 일정 담당자 생성 */
    int insertScheduleUser(ScheduleUser scheduleUser);

    /* 일정 수정 */
    int updateSchedule(Schedule schedule);

    /* 일정 담당자 삭제 */
    int deleteScheduleUser(Schedule schedule);

    /* 일정 상세 조회 */
    Map<String, Object> findScheduleDetail(@Param("scheduleId") int scheduleId);

    /* 일정 상세 담당자 조회 */
    List<CamelCaseMap> findScheduleDetailUserList(@Param("scheduleId") int scheduleId);

    /* 일정 삭제 */
    int deleteSchedule(@Param("scheduleId") int scheduleId);
}
