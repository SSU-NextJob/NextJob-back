package com.nextjob.back.code.service.impl;

import com.nextjob.back.code.service.DetailCodesMapper;
import com.nextjob.back.code.service.DetailCodesService;
import com.nextjob.base.util.CamelCaseMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailCodesServiceImpl implements DetailCodesService {

    private final DetailCodesMapper detailCodesMapper;

    public DetailCodesServiceImpl(DetailCodesMapper detailCodesMapper) {
        this.detailCodesMapper = detailCodesMapper;
    }

    /**
     * 그룹코드로 상세코드 조회
     *
     * @param groupCode
     * @return
     */
    @Override
    public List<CamelCaseMap> findDetailCodeList(String groupCode) {
        return detailCodesMapper.findDetailCodeList(groupCode);
    }
}
