package com.nextjob.back.code.web;

import com.nextjob.back.code.service.GroupCodesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/group")
public class GroupCodesController {

    private final GroupCodesService groupCodesService;

    public GroupCodesController(GroupCodesService groupCodesService) {
        this.groupCodesService = groupCodesService;
    }


}
