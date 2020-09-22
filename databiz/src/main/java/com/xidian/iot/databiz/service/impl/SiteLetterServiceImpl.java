package com.xidian.iot.databiz.service.impl;

import com.github.pagehelper.PageHelper;
import com.xidian.iot.database.entity.SiteLetter;
import com.xidian.iot.database.entity.SiteLetterExample;
import com.xidian.iot.database.mapper.SiteLetterMapper;
import com.xidian.iot.databiz.service.SiteLetterService;
import com.xidian.iot.databiz.service.UidGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wmr
 * @Title: SiteLetterService
 * @Package com.xidian.iot.databiz.service
 * @Description: siteletter service
 * @date 2020/9/21 3:01 下午
 */
@Service
@Slf4j
public class SiteLetterServiceImpl implements SiteLetterService {
    @Autowired
    private SiteLetterMapper siteLetterMapper;
    @Autowired
    private UidGenerator uidGenerator;

    @Override
    public List<SiteLetter> getSiteLetters(int page,int limit){
        if (page >= 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        return siteLetterMapper.selectByExample(new SiteLetterExample());
    }
    @Override
    public void addSiteLetter(SiteLetter siteLetter){
        siteLetter.setSlId(uidGenerator.getUID());
        log.info(siteLetter.getSlId().toString());
        siteLetterMapper.insertSelective(siteLetter);
    }
}
