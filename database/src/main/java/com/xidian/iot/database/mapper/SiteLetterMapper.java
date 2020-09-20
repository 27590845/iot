package com.xidian.iot.database.mapper;

import com.xidian.iot.database.entity.SiteLetter;
import com.xidian.iot.database.entity.SiteLetterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SiteLetterMapper {
    long countByExample(SiteLetterExample example);

    int deleteByExample(SiteLetterExample example);

    int deleteByPrimaryKey(Long slId);

    int insert(SiteLetter record);

    int insertSelective(SiteLetter record);

    List<SiteLetter> selectByExample(SiteLetterExample example);

    SiteLetter selectByPrimaryKey(Long slId);

    int updateByExampleSelective(@Param("record") SiteLetter record, @Param("example") SiteLetterExample example);

    int updateByExample(@Param("record") SiteLetter record, @Param("example") SiteLetterExample example);

    int updateByPrimaryKeySelective(SiteLetter record);

    int updateByPrimaryKey(SiteLetter record);
}