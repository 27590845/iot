package com.xidian.iot.datacenter.service.chain.json;

import com.xidian.iot.common.util.JsonUtil;
import com.xidian.iot.common.util.StringUtil;
import com.xidian.iot.datacenter.constant.ErrorCode;
import com.xidian.iot.datacenter.exception.AbortChainException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author mrl
 * @Title: CheckJsonDataCommand
 * @Package
 * @Description: 负责检查json数据是否合法，并将合法的List<Map>设置到上下文    copy from langyan
 * @date 2020/9/10 2:32 下午
 */
@Slf4j
public class CheckJsonDataCommand implements Command {

    /**
     * =====入口======
     * @param context
     * @return
     * @throws Exception
     */
    @Override
    public boolean execute(Context context) throws Exception {
        log.info("================================Start check JSON data.================================");
        JsonDataContext upContext = (JsonDataContext) context;
        // 获取datastreams字段
        getAndSetDatastreams(upContext);
        // 获取并设置datastreamsList
        getAndSetDatastreamsList(upContext);
        // 检查datastreamsList
        checkJsonDatestreamsList(upContext);
        log.info("================================Complete check JSON data.================================");
        return false;
    }

    /**
     * 获得节点数据字段datastreams
     * @param context 上下文
     * @throws Exception
     */
    private void getAndSetDatastreams(JsonDataContext context) throws Exception {
        // 获得节点数据json
        String datastreams = JsonUtil.getFieldString(context.getJsonData(), JsonDataContext.MAIN_FILED);
        // 当子json数据中找不到datastreams字段时
        if (StringUtil.isEmpty(datastreams)) {
            throw new AbortChainException(ErrorCode.ERR100032);
        }
        log.info("Check the 'datastreams' , it is not empty , [{}]", datastreams);
        // 设置到上下文中
        context.setDatastreams(datastreams);
    }

    /**
     * 获得并设置datastreamsList
     * @param context 上下文
     * @throws Exception
     * @throws AbortChainException
     */
    private void getAndSetDatastreamsList(JsonDataContext context) throws Exception, AbortChainException {
        String datastreams = context.getDatastreams();
        // 转换为map类型的list便于检测字段
        List<Map> datastreamsList = JsonUtil.toList(datastreams, Map.class);
        if (CollectionUtils.isEmpty(datastreamsList)) {
            throw new AbortChainException(ErrorCode.ERR100033);
        }
        // 设置到上下文
        context.setDatastreamsList(datastreamsList);
    }

    /**
     * 检查datastreamsList是否合法
     * @param context 上下文
     * @throws Exception
     * @throws AbortChainException
     */
    private void checkJsonDatestreamsList(JsonDataContext context) throws Exception, AbortChainException {
        List<Map> jsonDatastreams = context.getDatastreamsList();
        // 违法的datastreams数据
        List<Map> illegalDatastreamsList = new ArrayList<Map>();
        // 合法的datastreams数据
        List<Map> legalDatastreamsList = new ArrayList<Map>();
        // 开始逐个检查
        for (Map<String, Object> data : jsonDatastreams) {
            // 不包含NODE_SN_KEY的的数据，丢弃
            String nodeSn = (String) data.get(JsonDataContext.NODE_SN_KEY);
            if (StringUtils.isEmpty(nodeSn)) {
                illegalDatastreamsList.add(data);
                continue;
            }
            // 若出去NODE_SN_KEY和NODE_AT_KEY，一个属性都没有，则属于违法数据
            if (noAttribute(data)) {
                illegalDatastreamsList.add(data);
                continue;
            }
            // 存储到合法数据中
            legalDatastreamsList.add(data);
        }
        // 当上数的datastreams中没有合法的数据时
        if (legalDatastreamsList.isEmpty()) {
            throw new AbortChainException(ErrorCode.ERR100034, JsonDataContext.NODE_SN_KEY,
                    JsonDataContext.NODE_SN_KEY, JsonDataContext.NODE_AT_KEY, JsonUtil.toJson(illegalDatastreamsList));
        }
        log.debug("Check the 'datastreams' map list");
        log.info("\t====>>legal map list {} ", legalDatastreamsList);
        log.info("\t====>> illegal map list {}", illegalDatastreamsList);
        // 设置到上下文中
        context.setIllegalDatastreamsList(illegalDatastreamsList);
        context.setLegalDatastreamsList(legalDatastreamsList);
    }

    /**
     * 检查上数数据，若出去NODE_SN_KEY和NODE_AT_KEY,其他一个属性都没有则，代表其违法。
     * @param data 上数数据
     * @return true 违法数据，false 合法数据
     */
    private boolean noAttribute(Map<String, Object> data) {
        int count = data.size();
        if (data.containsKey(JsonDataContext.NODE_SN_KEY)) {
            count--;
        }
        if (data.containsKey(JsonDataContext.NODE_AT_KEY)) {
            count--;
        }
        return count == 0;
    }
}