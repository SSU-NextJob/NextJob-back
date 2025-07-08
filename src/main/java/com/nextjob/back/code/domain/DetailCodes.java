package com.nextjob.back.code.domain;

import com.nextjob.base.domain.Domain;

public class DetailCodes extends Domain {

    /* 그룹코드 */
    private String groupCode;

    /* 상세코드 */
    private String detailCode;

    /* 코드명 */
    private String detailName;

    /* 설명 */
    private String description;

    /* 사용여부 */
    private Boolean isUse;

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getUse() {
        return isUse;
    }

    public void setUse(Boolean use) {
        isUse = use;
    }
}
