package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.SiteLetter;

import java.util.List;

public interface SiteLetterService {
    /**
     * 分页获取当前所有的站内信
     * @param page
     * @param limit
     * @return java.util.List<com.xidian.iot.database.entity.SiteLetter>
     * */
    List<SiteLetter> getSiteLetters(int page, int limit);
    /**
     * 添加站内信
     * */
    void addSiteLetter(SiteLetter siteLetter);
}
