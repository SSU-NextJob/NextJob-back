package com.nextjob.back.code.domain;

import com.nextjob.base.domain.Domain;

public class GroupCodes extends Domain {

    /* 그룹코드 */
    private String groupCode;

    /* 그룹명 */
    private String groupName;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
