package com.xidian.iot.common.monitor.parser;

import com.xidian.iot.common.monitor.Frame;
import com.xidian.iot.common.monitor.Parser;
import com.xidian.iot.common.util.TimeUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: 解析logback的日志输出，需要给出logback的pattern
 * @author: mrl
 * @date: 2020/12/14 下午9:28
 */
public class LogbackParser implements Parser {

    public static final String[] levelTags = {"p", "le", "level"};//用level作为日志类型(frame.type)
    public static final String[] dateTags = {"d", "data"};//用date当作日志时间戳(frame.timestamp)
    public static final String[] loggerTags = {"c", "lo", "logger"};//用logger当作日志签名(frame.signature)
    public static final String[] callerTags = {"caller"};//其实也可以用caller当作日志签名(frame.signature)

    /**
     * 使用LogbackParser时对pattern有以下限制：
     * 1.需要设置一个frameHeaderTag，用于标识一次日志输出的开始
     * 2.对level、date、logger字段，固定其长度，且放在前三个字段
     */
    private String pattern;

    //日志类别(比如info，warning，error)

    private int levelStart;
    private int levelEnd;

    private int timeStampStart;
    private int timeStampEnd;

    private int signatureStart;
    private int signatureEnd;

    private int contentStart;

    public LogbackParser(String pattern){
        this.pattern = pattern;
        init();
    }

    @Override
    public String getFrameHeaderTag() {
        //帧头为：从开头到第一个%前的字符串
        return pattern.substring(0, pattern.indexOf('%'));
    }

    @Override
    public Frame parseFrame(String frameStr) {
        Frame frame = new Frame();
        String typeStr = frameStr.substring(levelStart, levelEnd+1);
        frame.setType(getType(typeStr));
        String dateStr = frameStr.substring(timeStampStart, timeStampEnd+1);
        frame.setTimeStamp(TimeUtil.getTimeStamp(dateStr, null));
        String signatureStr = frameStr.substring(signatureStart, signatureEnd+1);
        frame.setSignature(StringUtils.isNoneBlank(signatureStr)?signatureStr.trim():null);
        String contentStr = frameStr.substring(contentStart);
        frame.setContent(contentStr);
        return frame;
    }

    private void init(){
        int currFrameFiledStart = getFrameHeaderTag().length();
        String[] subPatterns = pattern.substring(currFrameFiledStart+1).split("%");
        for(String subPattern : subPatterns){
            String tmp = subPattern;

            //判定当前filed长度
            int filedLen = 0;
            if(subPattern.startsWith("-")){
                String filedLenStr = tmp.substring(tmp.indexOf("-")+1, tmp.indexOf("."));
                filedLen = Integer.valueOf(filedLenStr);
                tmp = tmp.substring(filedLenStr.length()*2 + "-".length() + ".".length());
            }else {
                //剩下的全当做msg
                contentStart = currFrameFiledStart;
                break;
            }

            //判定当前filed是什么类型，以及设置字段起始位置和结束位置
            String finalTmp = tmp;
            boolean matched = false;

            for(String tag : levelTags){
                if(finalTmp.startsWith(tag)){
                    levelStart = currFrameFiledStart;
                    levelEnd = levelStart + filedLen - 1;
                    currFrameFiledStart = levelStart + filedLen;
                    matched = true;
                    break;
                }
            }
            if (matched) continue;
            for(String tag : dateTags){
                if(finalTmp.startsWith(tag)){
                    timeStampStart = currFrameFiledStart;
                    timeStampEnd = timeStampStart + filedLen - 1;
                    currFrameFiledStart = timeStampStart + filedLen;
                    matched = true;
                    break;
                }
            }
            if (matched) continue;
            for(String tag : loggerTags){
                if(finalTmp.startsWith(tag)){
                    signatureStart = currFrameFiledStart;
                    signatureEnd = signatureStart + filedLen - 1;
                    currFrameFiledStart = signatureStart + filedLen;
                    matched = true;
                    break;
                }
            }

            if(!matched){
                currFrameFiledStart += filedLen;
            }
        }
        return;
    }

    private int getType(String level){
        if(StringUtils.isBlank(level)) return Frame.TYPE_WARN;
        level = level.toLowerCase().trim();
        int type = 0;
        switch (level){
            case "info":
                type = Frame.TYPE_INFO;
                break;
            case "warn":
                type = Frame.TYPE_WARN;
                break;
            case "error":
                type = Frame.TYPE_ERROR;
                break;
        }
        return type;
    }
}
