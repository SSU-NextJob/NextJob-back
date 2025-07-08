package com.nextjob.back.code.service;

import com.nextjob.base.util.CamelCaseMap;

import java.util.List;

public interface DetailCodesService {

    /* 그룹코드로 상세코드 조회 */
    List<CamelCaseMap> findDetailCodeList(String groupCode);
}
