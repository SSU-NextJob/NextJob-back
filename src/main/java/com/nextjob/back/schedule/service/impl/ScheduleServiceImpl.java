package com.nextjob.back.schedule.service.impl;

import com.nextjob.back.schedule.domain.Schedule;
import com.nextjob.back.schedule.domain.ScheduleUser;
import com.nextjob.back.schedule.service.ScheduleMapper;
import com.nextjob.back.schedule.service.ScheduleService;
import com.nextjob.back.schedule.web.ScheduleSearchCriteria;
import com.nextjob.base.util.CamelCaseMap;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 일정 목록 조회
     *
     * @param
     * @return
     */
    @Override
    public List<CamelCaseMap> findScheduleList(ScheduleSearchCriteria scheduleSearchCriteria) {
        return scheduleMapper.findScheduleList(scheduleSearchCriteria);
    }

    /**
     * 일정 생성
     *
     * @param scheduleSearchCriteria
     * @return
     */
    @Override
    public Map<String, Object> insertSchedule(ScheduleSearchCriteria scheduleSearchCriteria) {
        Map<String, Object> result = new HashMap<>();
        try {
            Date startDate = formatter.parse(scheduleSearchCriteria.getStartDate());
            Date endDate = formatter.parse(scheduleSearchCriteria.getEndDate());

            Schedule schedule = new Schedule();
            schedule.setWorkspaceId(scheduleSearchCriteria.getWorkspaceId());
            schedule.setTitle(scheduleSearchCriteria.getTitle());
            schedule.setContent(scheduleSearchCriteria.getContent());
            schedule.setStartDate(startDate);
            schedule.setEndDate(endDate);
            schedule.setUserId(scheduleSearchCriteria.getUserId());

            int insertedSchedule = scheduleMapper.insertSchedule(schedule);

            for(int i = 0; i < scheduleSearchCriteria.getAssignees().length; i++) {
                ScheduleUser scheduleUser = new ScheduleUser();
                scheduleUser.setScheduleId(schedule.getScheduleId());
                scheduleUser.setUserId(scheduleSearchCriteria.getAssignees()[i]);
                int userInsertedCount = scheduleMapper.insertScheduleUser(scheduleUser);
            }
            result.put("insertedSchedule", insertedSchedule);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 일정 수정
     *
     * @param scheduleSearchCriteria
     * @return
     */
    @Override
    public Map<String, Object> updateSchedule(ScheduleSearchCriteria scheduleSearchCriteria) {
        Map<String, Object> result = new HashMap<>();

        try {
            Date startDate = formatter.parse(scheduleSearchCriteria.getStartDate());
            Date endDate = formatter.parse(scheduleSearchCriteria.getEndDate());

            Schedule schedule = new Schedule();
            schedule.setScheduleId(scheduleSearchCriteria.getScheduleId());
            schedule.setTitle(scheduleSearchCriteria.getTitle());
            schedule.setContent(scheduleSearchCriteria.getContent());
            schedule.setStartDate(startDate);
            schedule.setEndDate(endDate);
            int updatedSchedule = scheduleMapper.updateSchedule(schedule);

            //기존 일정 담당자 삭제
            int userDeletedCount = scheduleMapper.deleteScheduleUser(schedule);

            for(int i = 0; i < scheduleSearchCriteria.getAssignees().length; i++) {
                ScheduleUser scheduleUser = new ScheduleUser();
                scheduleUser.setScheduleId(schedule.getScheduleId());
                scheduleUser.setUserId(scheduleSearchCriteria.getAssignees()[i]);
                int userUpdatedCount = scheduleMapper.insertScheduleUser(scheduleUser);
            }

            result.put("updatedSchedule", updatedSchedule);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 일정 상세 조회
     *
     * @param scheduleId
     * @return
     */
    @Override
    public Map<String, Object> findScheduleDetail(int scheduleId) {
        Map<String, Object> result = scheduleMapper.findScheduleDetail(scheduleId);
        if(!result.isEmpty()) {
            result.put("assignees", scheduleMapper.findScheduleDetailUserList(scheduleId));
        }
        return result;
    }

    /**
     * 일정 삭제
     *
     * @param scheduleId
     * @return
     */
    @Override
    public Map<String, Object> deleteSchedule(int scheduleId) {
        Map<String, Object> result = new HashMap<>();
        int scheduleDeletedCount = scheduleMapper.deleteSchedule(scheduleId);

        Schedule schedule = new Schedule();
        schedule.setScheduleId(scheduleId);
        int scheduleUserDeletedCount = scheduleMapper.deleteScheduleUser(schedule);
        return result;
    }
}
