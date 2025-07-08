package com.nextjob.back.code.service.impl;

import com.nextjob.back.code.service.GroupCodesMapper;
import com.nextjob.back.code.service.GroupCodesService;
import org.springframework.stereotype.Service;

@Service
public class GroupCodesServiceImpl implements GroupCodesService {

    private final GroupCodesMapper groupCodesMapper;

    public GroupCodesServiceImpl(GroupCodesMapper groupCodesMapper) {
        this.groupCodesMapper = groupCodesMapper;
    }
}
