package com.nextjob.back.code.service;

import com.nextjob.base.util.CamelCaseMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DetailCodesMapper {

    /* 그룹코드로 상세코드 조회 */
    List<CamelCaseMap> findDetailCodeList(String groupCode);
}
