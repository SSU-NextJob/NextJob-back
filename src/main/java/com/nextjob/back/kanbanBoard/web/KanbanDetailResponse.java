package com.nextjob.back.kanbanBoard.web;

import com.nextjob.base.web.servlet.search.Search;

@SuppressWarnings("serial")
public class KanbanDetailResponse implements Search {
    private int kanbanId;

    public int getKanbanId() {
        return kanbanId;
    }

    public void setKanbanId(int kanbanId) {
        this.kanbanId = kanbanId;
    }
}
