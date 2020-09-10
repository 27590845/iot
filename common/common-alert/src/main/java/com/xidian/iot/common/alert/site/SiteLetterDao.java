package com.xidian.iot.common.alert.site;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * 站内信数据层访问逻辑。
 * 
 * @author zhengrj
 */
public class SiteLetterDao extends SqlSessionDaoSupport {

    /**
     * 添加一条站内信
     */
    public SiteLetter addSiteLetter(SiteLetter siteLetter) {
        return (SiteLetter) getSqlSession().selectOne("SiteLetter.addSiteLetter", siteLetter);
    }
}
