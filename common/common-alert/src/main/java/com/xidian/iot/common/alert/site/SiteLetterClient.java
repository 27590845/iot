package com.xidian.iot.common.alert.site;

import com.xidian.iot.common.alert.AlertClient;
import com.xidian.iot.common.alert.AlertVo;
import lombok.Setter;

import java.util.List;


public class SiteLetterClient implements AlertClient {

    /**
     * 站内信数据访问接口
     */
    @Setter
    private SiteLetterDao siteLetterDao;

    public boolean send(AlertVo vo) {
        SiteLetterVo siteLetterVo = (SiteLetterVo) vo;
        // 添加站内信
        siteLetterDao.addSiteLetter((SiteLetter) siteLetterVo);
        return true;
    }

    public void send(List<AlertVo> list) {
        if (null != list && list.size() > 0) {
            for (AlertVo alertVo : list) {
                send(alertVo);
            }
        }
    }

}
