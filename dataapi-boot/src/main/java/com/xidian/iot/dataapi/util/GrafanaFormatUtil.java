package com.xidian.iot.dataapi.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xidian.iot.database.vo.SceneVo;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @description:
 * @author: mrl
 * @date: 2021/4/2 下午12:02
 */
public class GrafanaFormatUtil {

    // 查询最小值，仪表盘样式
    public static final String PANEL_MIN = "{\n" +
            "      \"datasource\": \"iotdata\",\n" +
            "      \"description\": \"#{panel_des}\",\n" +
            "      \"fieldConfig\": {\n" +
            "        \"defaults\": {\n" +
            "          \"custom\": {},\n" +
            "          \"mappings\": [],\n" +
            "          \"thresholds\": {\n" +
            "            \"mode\": \"percentage\",\n" +
            "            \"steps\": [\n" +
            "              {\n" +
            "                \"color\": \"orange\",\n" +
            "                \"value\": null\n" +
            "              },\n" +
            "              {\n" +
            "                \"color\": \"red\",\n" +
            "                \"value\": 80\n" +
            "              }\n" +
            "            ]\n" +
            "          }\n" +
            "        },\n" +
            "        \"overrides\": []\n" +
            "      },\n" +
            "      \"gridPos\": {\n" +
            "        \"h\": #{panel_height},\n" +
            "        \"w\": #{panel_width},\n" +
            "        \"x\": #{panel_xAxis},\n" +
            "        \"y\": #{panel_yAxis}\n" +
            "      },\n" +
            "      \"id\": #{panel_id},\n" +
            "      \"options\": {\n" +
            "        \"reduceOptions\": {\n" +
            "          \"calcs\": [\n" +
            "            \"mean\"\n" +
            "          ],\n" +
            "          \"fields\": \"\",\n" +
            "          \"values\": false\n" +
            "        },\n" +
            "        \"showThresholdLabels\": false,\n" +
            "        \"showThresholdMarkers\": true,\n" +
            "        \"text\": {}\n" +
            "      },\n" +
            "      \"pluginVersion\": \"7.4.5\",\n" +
            "      \"targets\": [\n" +
            "        {\n" +
            "          \"groupBy\": [\n" +
            "            {\n" +
            "              \"params\": [\n" +
            "                \"10s\"\n" +
            "              ],\n" +
            "              \"type\": \"time\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"params\": [\n" +
            "                \"null\"\n" +
            "              ],\n" +
            "              \"type\": \"fill\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"measurement\": \"#{sceneSn}\",\n" +
            "          \"orderByTime\": \"ASC\",\n" +
            "          \"policy\": \"default\",\n" +
            "          \"refId\": \"A\",\n" +
            "          \"resultFormat\": \"time_series\",\n" +
            "          \"select\": [\n" +
            "            [\n" +
            "              {\n" +
            "                \"params\": [\n" +
            "                  \"#{panel_attrKey}\"\n" +
            "                ],\n" +
            "                \"type\": \"field\"\n" +
            "              },\n" +
            "              {\n" +
            "                \"params\": [],\n" +
            "                \"type\": \"min\"\n" +
            "              }\n" +
            "            ]\n" +
            "          ],\n" +
            "          \"tags\": [\n" +
            "            {\n" +
            "              \"key\": \"nodeSn\",\n" +
            "              \"operator\": \"=\",\n" +
            "              \"value\": \"#{panel_nodeSn}\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ],\n" +
            "      \"timeFrom\": null,\n" +
            "      \"timeShift\": null,\n" +
            "      \"title\": \"#{panel_title}\",\n" +
            "      \"type\": \"gauge\"\n" +
            "    }";

    // 查询最大值，仪表盘样式
    public static final String PANEL_MAX = "{\n" +
            "      \"datasource\": \"iotdata\",\n" +
            "      \"description\": \"#{panel_des}\",\n" +
            "      \"fieldConfig\": {\n" +
            "        \"defaults\": {\n" +
            "          \"custom\": {},\n" +
            "          \"mappings\": [],\n" +
            "          \"thresholds\": {\n" +
            "            \"mode\": \"percentage\",\n" +
            "            \"steps\": [\n" +
            "              {\n" +
            "                \"color\": \"dark-blue\",\n" +
            "                \"value\": null\n" +
            "              },\n" +
            "              {\n" +
            "                \"color\": \"red\",\n" +
            "                \"value\": 80\n" +
            "              }\n" +
            "            ]\n" +
            "          }\n" +
            "        },\n" +
            "        \"overrides\": []\n" +
            "      },\n" +
            "      \"gridPos\": {\n" +
            "        \"h\": #{panel_height},\n" +
            "        \"w\": #{panel_width},\n" +
            "        \"x\": #{panel_xAxis},\n" +
            "        \"y\": #{panel_yAxis}\n" +
            "      },\n" +
            "      \"id\": #{panel_id},\n" +
            "      \"options\": {\n" +
            "        \"reduceOptions\": {\n" +
            "          \"calcs\": [\n" +
            "            \"mean\"\n" +
            "          ],\n" +
            "          \"fields\": \"\",\n" +
            "          \"values\": false\n" +
            "        },\n" +
            "        \"showThresholdLabels\": false,\n" +
            "        \"showThresholdMarkers\": true,\n" +
            "        \"text\": {}\n" +
            "      },\n" +
            "      \"pluginVersion\": \"7.4.5\",\n" +
            "      \"targets\": [\n" +
            "        {\n" +
            "          \"groupBy\": [\n" +
            "            {\n" +
            "              \"params\": [\n" +
            "                \"10s\"\n" +
            "              ],\n" +
            "              \"type\": \"time\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"params\": [\n" +
            "                \"null\"\n" +
            "              ],\n" +
            "              \"type\": \"fill\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"measurement\": \"#{sceneSn}\",\n" +
            "          \"orderByTime\": \"ASC\",\n" +
            "          \"policy\": \"default\",\n" +
            "          \"refId\": \"A\",\n" +
            "          \"resultFormat\": \"time_series\",\n" +
            "          \"select\": [\n" +
            "            [\n" +
            "              {\n" +
            "                \"params\": [\n" +
            "                  \"#{panel_attrKey}\"\n" +
            "                ],\n" +
            "                \"type\": \"field\"\n" +
            "              },\n" +
            "              {\n" +
            "                \"params\": [],\n" +
            "                \"type\": \"max\"\n" +
            "              }\n" +
            "            ]\n" +
            "          ],\n" +
            "          \"tags\": [\n" +
            "            {\n" +
            "              \"key\": \"nodeSn\",\n" +
            "              \"operator\": \"=\",\n" +
            "              \"value\": \"#{panel_nodeSn}\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ],\n" +
            "      \"timeFrom\": null,\n" +
            "      \"timeShift\": null,\n" +
            "      \"title\": \"#{panel_title}\",\n" +
            "      \"type\": \"gauge\"\n" +
            "    }";

    // 查询平均值，状态图样式
    public static final String PANEL_STAT = "{\n" +
            "      \"datasource\": \"iotdata\",\n" +
            "      \"description\": \"#{panel_des}\",\n" +
            "      \"fieldConfig\": {\n" +
            "        \"defaults\": {\n" +
            "          \"custom\": {},\n" +
            "          \"mappings\": [],\n" +
            "          \"thresholds\": {\n" +
            "            \"mode\": \"absolute\",\n" +
            "            \"steps\": [\n" +
            "              {\n" +
            "                \"color\": \"blue\",\n" +
            "                \"value\": null\n" +
            "              },\n" +
            "              {\n" +
            "                \"color\": \"red\",\n" +
            "                \"value\": 80\n" +
            "              }\n" +
            "            ]\n" +
            "          }\n" +
            "        },\n" +
            "        \"overrides\": []\n" +
            "      },\n" +
            "      \"gridPos\": {\n" +
            "        \"h\": #{panel_height},\n" +
            "        \"w\": #{panel_width},\n" +
            "        \"x\": #{panel_xAxis},\n" +
            "        \"y\": #{panel_yAxis}\n" +
            "      },\n" +
            "      \"id\": #{panel_id},\n" +
            "      \"options\": {\n" +
            "        \"colorMode\": \"value\",\n" +
            "        \"graphMode\": \"area\",\n" +
            "        \"justifyMode\": \"auto\",\n" +
            "        \"orientation\": \"auto\",\n" +
            "        \"reduceOptions\": {\n" +
            "          \"calcs\": [\n" +
            "            \"mean\"\n" +
            "          ],\n" +
            "          \"fields\": \"\",\n" +
            "          \"values\": false\n" +
            "        },\n" +
            "        \"text\": {},\n" +
            "        \"textMode\": \"auto\"\n" +
            "      },\n" +
            "      \"pluginVersion\": \"7.4.5\",\n" +
            "      \"targets\": [\n" +
            "        {\n" +
            "          \"groupBy\": [\n" +
            "            {\n" +
            "              \"params\": [\n" +
            "                \"10s\"\n" +
            "              ],\n" +
            "              \"type\": \"time\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"params\": [\n" +
            "                \"null\"\n" +
            "              ],\n" +
            "              \"type\": \"fill\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"measurement\": \"#{sceneSn}\",\n" +
            "          \"orderByTime\": \"ASC\",\n" +
            "          \"policy\": \"default\",\n" +
            "          \"refId\": \"A\",\n" +
            "          \"resultFormat\": \"time_series\",\n" +
            "          \"select\": [\n" +
            "            [\n" +
            "              {\n" +
            "                \"params\": [\n" +
            "                  \"#{panel_attrKey}\"\n" +
            "                ],\n" +
            "                \"type\": \"field\"\n" +
            "              },\n" +
            "              {\n" +
            "                \"params\": [],\n" +
            "                \"type\": \"mean\"\n" +
            "              }\n" +
            "            ]\n" +
            "          ],\n" +
            "          \"tags\": [\n" +
            "            {\n" +
            "              \"key\": \"nodeSn\",\n" +
            "              \"operator\": \"=\",\n" +
            "              \"value\": \"#{panel_nodeSn}\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ],\n" +
            "      \"timeFrom\": null,\n" +
            "      \"timeShift\": null,\n" +
            "      \"title\": \"#{panel_title}\",\n" +
            "      \"type\": \"stat\"\n" +
            "    }";

    // 查询均值，热力图样式
    public static final String PANEL_HEATMAP = "{\n" +
            "      \"cards\": {\n" +
            "        \"cardPadding\": null,\n" +
            "        \"cardRound\": null\n" +
            "      },\n" +
            "      \"color\": {\n" +
            "        \"cardColor\": \"#b4ff00\",\n" +
            "        \"colorScale\": \"sqrt\",\n" +
            "        \"colorScheme\": \"interpolateOranges\",\n" +
            "        \"exponent\": 0.5,\n" +
            "        \"mode\": \"spectrum\"\n" +
            "      },\n" +
            "      \"dataFormat\": \"timeseries\",\n" +
            "      \"datasource\": \"iotdata\",\n" +
            "      \"description\": \"#{panel_des}\",\n" +
            "      \"fieldConfig\": {\n" +
            "        \"defaults\": {\n" +
            "          \"custom\": {}\n" +
            "        },\n" +
            "        \"overrides\": []\n" +
            "      },\n" +
            "      \"gridPos\": {\n" +
            "        \"h\": #{panel_height},\n" +
            "        \"w\": #{panel_width},\n" +
            "        \"x\": #{panel_xAxis},\n" +
            "        \"y\": #{panel_yAxis}\n" +
            "      },\n" +
            "      \"heatmap\": {},\n" +
            "      \"hideZeroBuckets\": false,\n" +
            "      \"highlightCards\": true,\n" +
            "      \"id\": #{panel_id},\n" +
            "      \"legend\": {\n" +
            "        \"show\": false\n" +
            "      },\n" +
            "      \"pluginVersion\": \"7.1.3\",\n" +
            "      \"reverseYBuckets\": false,\n" +
            "      \"targets\": [\n" +
            "        {\n" +
            "          \"groupBy\": [\n" +
            "            {\n" +
            "              \"params\": [\n" +
            "                \"$__interval\"\n" +
            "              ],\n" +
            "              \"type\": \"time\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"params\": [\n" +
            "                \"null\"\n" +
            "              ],\n" +
            "              \"type\": \"fill\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"measurement\": \"#{sceneSn}\",\n" +
            "          \"orderByTime\": \"ASC\",\n" +
            "          \"policy\": \"default\",\n" +
            "          \"refId\": \"A\",\n" +
            "          \"resultFormat\": \"time_series\",\n" +
            "          \"select\": [\n" +
            "            [\n" +
            "              {\n" +
            "                \"params\": [\n" +
            "                  \"#{panel_attrKey}\"\n" +
            "                ],\n" +
            "                \"type\": \"field\"\n" +
            "              },\n" +
            "              {\n" +
            "                \"params\": [],\n" +
            "                \"type\": \"mean\"\n" +
            "              }\n" +
            "            ]\n" +
            "          ],\n" +
            "          \"tags\": [\n" +
            "            {\n" +
            "              \"key\": \"nodeSn\",\n" +
            "              \"operator\": \"=\",\n" +
            "              \"value\": \"#{panel_nodeSn}\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ],\n" +
            "      \"timeFrom\": null,\n" +
            "      \"timeShift\": null,\n" +
            "      \"title\": \"#{panel_title}\",\n" +
            "      \"tooltip\": {\n" +
            "        \"show\": true,\n" +
            "        \"showHistogram\": false\n" +
            "      },\n" +
            "      \"type\": \"heatmap\",\n" +
            "      \"xAxis\": {\n" +
            "        \"show\": true\n" +
            "      },\n" +
            "      \"xBucketNumber\": null,\n" +
            "      \"xBucketSize\": null,\n" +
            "      \"yAxis\": {\n" +
            "        \"decimals\": null,\n" +
            "        \"format\": \"short\",\n" +
            "        \"logBase\": 1,\n" +
            "        \"max\": null,\n" +
            "        \"min\": null,\n" +
            "        \"show\": true,\n" +
            "        \"splitFactor\": null\n" +
            "      },\n" +
            "      \"yBucketBound\": \"auto\",\n" +
            "      \"yBucketNumber\": null,\n" +
            "      \"yBucketSize\": null\n" +
            "    }";

    // 查询均值，分布图样式
    public static final String PANEL_DISTRIBUTE = "{\n" +
            "      \"aliasColors\": {},\n" +
            "      \"bars\": true,\n" +
            "      \"dashLength\": 10,\n" +
            "      \"dashes\": false,\n" +
            "      \"datasource\": \"iotdata\",\n" +
            "      \"description\": \"#{panel_des}\",\n" +
            "      \"fieldConfig\": {\n" +
            "        \"defaults\": {\n" +
            "          \"custom\": {},\n" +
            "          \"thresholds\": {\n" +
            "            \"mode\": \"absolute\",\n" +
            "            \"steps\": []\n" +
            "          }\n" +
            "        },\n" +
            "        \"overrides\": []\n" +
            "      },\n" +
            "      \"fill\": 1,\n" +
            "      \"fillGradient\": 0,\n" +
            "      \"gridPos\": {\n" +
            "        \"h\": #{panel_height},\n" +
            "        \"w\": #{panel_width},\n" +
            "        \"x\": #{panel_xAxis},\n" +
            "        \"y\": #{panel_yAxis}\n" +
            "      },\n" +
            "      \"hiddenSeries\": false,\n" +
            "      \"id\": #{panel_id},\n" +
            "      \"legend\": {\n" +
            "        \"avg\": false,\n" +
            "        \"current\": false,\n" +
            "        \"hideEmpty\": false,\n" +
            "        \"hideZero\": false,\n" +
            "        \"max\": false,\n" +
            "        \"min\": false,\n" +
            "        \"show\": false,\n" +
            "        \"total\": false,\n" +
            "        \"values\": false\n" +
            "      },\n" +
            "      \"lines\": false,\n" +
            "      \"linewidth\": 1,\n" +
            "      \"nullPointMode\": \"null\",\n" +
            "      \"options\": {\n" +
            "        \"alertThreshold\": true\n" +
            "      },\n" +
            "      \"percentage\": false,\n" +
            "      \"pluginVersion\": \"7.4.5\",\n" +
            "      \"pointradius\": 2,\n" +
            "      \"points\": false,\n" +
            "      \"renderer\": \"flot\",\n" +
            "      \"seriesOverrides\": [],\n" +
            "      \"spaceLength\": 10,\n" +
            "      \"stack\": false,\n" +
            "      \"steppedLine\": false,\n" +
            "      \"targets\": [\n" +
            "        {\n" +
            "          \"groupBy\": [\n" +
            "            {\n" +
            "              \"params\": [\n" +
            "                \"$__interval\"\n" +
            "              ],\n" +
            "              \"type\": \"time\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"params\": [\n" +
            "                \"null\"\n" +
            "              ],\n" +
            "              \"type\": \"fill\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"measurement\": \"#{sceneSn}\",\n" +
            "          \"orderByTime\": \"ASC\",\n" +
            "          \"policy\": \"default\",\n" +
            "          \"refId\": \"A\",\n" +
            "          \"resultFormat\": \"time_series\",\n" +
            "          \"select\": [\n" +
            "            [\n" +
            "              {\n" +
            "                \"params\": [\n" +
            "                  \"#{panel_attrKey}\"\n" +
            "                ],\n" +
            "                \"type\": \"field\"\n" +
            "              },\n" +
            "              {\n" +
            "                \"params\": [],\n" +
            "                \"type\": \"mean\"\n" +
            "              }\n" +
            "            ]\n" +
            "          ],\n" +
            "          \"tags\": [\n" +
            "            {\n" +
            "              \"key\": \"nodeSn\",\n" +
            "              \"operator\": \"=\",\n" +
            "              \"value\": \"#{panel_nodeSn}\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ],\n" +
            "      \"thresholds\": [],\n" +
            "      \"timeFrom\": null,\n" +
            "      \"timeRegions\": [],\n" +
            "      \"timeShift\": null,\n" +
            "      \"title\": \"#{panel_title}\",\n" +
            "      \"tooltip\": {\n" +
            "        \"shared\": false,\n" +
            "        \"sort\": 0,\n" +
            "        \"value_type\": \"individual\"\n" +
            "      },\n" +
            "      \"type\": \"graph\",\n" +
            "      \"xaxis\": {\n" +
            "        \"buckets\": null,\n" +
            "        \"mode\": \"histogram\",\n" +
            "        \"name\": null,\n" +
            "        \"show\": true,\n" +
            "        \"values\": []\n" +
            "      },\n" +
            "      \"yaxes\": [\n" +
            "        {\n" +
            "          \"$$hashKey\": \"object:3028\",\n" +
            "          \"format\": \"short\",\n" +
            "          \"label\": null,\n" +
            "          \"logBase\": 1,\n" +
            "          \"max\": null,\n" +
            "          \"min\": null,\n" +
            "          \"show\": true\n" +
            "        },\n" +
            "        {\n" +
            "          \"$$hashKey\": \"object:3029\",\n" +
            "          \"format\": \"short\",\n" +
            "          \"label\": null,\n" +
            "          \"logBase\": 1,\n" +
            "          \"max\": null,\n" +
            "          \"min\": null,\n" +
            "          \"show\": true\n" +
            "        }\n" +
            "      ],\n" +
            "      \"yaxis\": {\n" +
            "        \"align\": false,\n" +
            "        \"alignLevel\": null\n" +
            "      }\n" +
            "    }";

    // 查询均值，折线图样式
    public static final String PANEL_LINE = "{\n" +
            "      \"aliasColors\": {},\n" +
            "      \"bars\": false,\n" +
            "      \"dashLength\": 10,\n" +
            "      \"dashes\": false,\n" +
            "      \"datasource\": \"iotdata\",\n" +
            "      \"decimals\": null,\n" +
            "      \"description\": \"#{panel_des}\",\n" +
            "      \"fieldConfig\": {\n" +
            "        \"defaults\": {\n" +
            "          \"custom\": {},\n" +
            "          \"thresholds\": {\n" +
            "            \"mode\": \"absolute\",\n" +
            "            \"steps\": []\n" +
            "          }\n" +
            "        },\n" +
            "        \"overrides\": []\n" +
            "      },\n" +
            "      \"fill\": 1,\n" +
            "      \"fillGradient\": 0,\n" +
            "      \"gridPos\": {\n" +
            "        \"h\": #{panel_height},\n" +
            "        \"w\": #{panel_width},\n" +
            "        \"x\": #{panel_xAxis},\n" +
            "        \"y\": #{panel_yAxis}\n" +
            "      },\n" +
            "      \"hiddenSeries\": false,\n" +
            "      \"id\": #{panel_id},\n" +
            "      \"legend\": {\n" +
            "        \"avg\": false,\n" +
            "        \"current\": true,\n" +
            "        \"max\": false,\n" +
            "        \"min\": false,\n" +
            "        \"show\": true,\n" +
            "        \"total\": false,\n" +
            "        \"values\": true\n" +
            "      },\n" +
            "      \"lines\": true,\n" +
            "      \"linewidth\": 1,\n" +
            "      \"nullPointMode\": \"null\",\n" +
            "      \"options\": {\n" +
            "        \"alertThreshold\": true\n" +
            "      },\n" +
            "      \"percentage\": false,\n" +
            "      \"pluginVersion\": \"7.4.5\",\n" +
            "      \"pointradius\": 2,\n" +
            "      \"points\": false,\n" +
            "      \"renderer\": \"flot\",\n" +
            "      \"seriesOverrides\": [],\n" +
            "      \"spaceLength\": 10,\n" +
            "      \"stack\": false,\n" +
            "      \"steppedLine\": false,\n" +
            "      \"targets\": [\n" +
            "        {\n" +
            "          \"alias\": \"#{panel_alias}\",\n" +
            "          \"groupBy\": [\n" +
            "            {\n" +
            "              \"params\": [\n" +
            "                \"10s\"\n" +
            "              ],\n" +
            "              \"type\": \"time\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"params\": [\n" +
            "                \"null\"\n" +
            "              ],\n" +
            "              \"type\": \"fill\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"measurement\": \"#{sceneSn}\",\n" +
            "          \"orderByTime\": \"ASC\",\n" +
            "          \"policy\": \"default\",\n" +
            "          \"refId\": \"A\",\n" +
            "          \"resultFormat\": \"time_series\",\n" +
            "          \"select\": [\n" +
            "            [\n" +
            "              {\n" +
            "                \"params\": [\n" +
            "                  \"#{panel_attrKey}\"\n" +
            "                ],\n" +
            "                \"type\": \"field\"\n" +
            "              },\n" +
            "              {\n" +
            "                \"params\": [],\n" +
            "                \"type\": \"mean\"\n" +
            "              }\n" +
            "            ]\n" +
            "          ],\n" +
            "          \"tags\": [\n" +
            "            {\n" +
            "              \"key\": \"nodeSn\",\n" +
            "              \"operator\": \"=\",\n" +
            "              \"value\": \"#{panel_nodeSn}\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ],\n" +
            "      \"thresholds\": [],\n" +
            "      \"timeFrom\": null,\n" +
            "      \"timeRegions\": [],\n" +
            "      \"timeShift\": null,\n" +
            "      \"title\": \"#{panel_title}\",\n" +
            "      \"tooltip\": {\n" +
            "        \"shared\": true,\n" +
            "        \"sort\": 0,\n" +
            "        \"value_type\": \"individual\"\n" +
            "      },\n" +
            "      \"type\": \"graph\",\n" +
            "      \"xaxis\": {\n" +
            "        \"buckets\": null,\n" +
            "        \"mode\": \"time\",\n" +
            "        \"name\": null,\n" +
            "        \"show\": true,\n" +
            "        \"values\": []\n" +
            "      },\n" +
            "      \"yaxes\": [\n" +
            "        {\n" +
            "          \"format\": \"short\",\n" +
            "          \"label\": null,\n" +
            "          \"logBase\": 1,\n" +
            "          \"max\": null,\n" +
            "          \"min\": null,\n" +
            "          \"show\": true\n" +
            "        },\n" +
            "        {\n" +
            "          \"format\": \"short\",\n" +
            "          \"label\": null,\n" +
            "          \"logBase\": 1,\n" +
            "          \"max\": null,\n" +
            "          \"min\": null,\n" +
            "          \"show\": true\n" +
            "        }\n" +
            "      ],\n" +
            "      \"yaxis\": {\n" +
            "        \"align\": false,\n" +
            "        \"alignLevel\": null\n" +
            "      }\n" +
            "    }";

    public static final String DASHBOARD_INIT = "{\n" +
            "  \"dashboard\": {\n" +
            "    \"annotations\": {\n" +
            "      \"list\": [\n" +
            "        {\n" +
            "          \"builtIn\": 1,\n" +
            "          \"datasource\": \"-- Grafana --\",\n" +
            "          \"enable\": true,\n" +
            "          \"hide\": true,\n" +
            "          \"iconColor\": \"rgba(0, 211, 255, 1)\",\n" +
            "          \"name\": \"Annotations & Alerts\",\n" +
            "          \"type\": \"dashboard\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"editable\": true,\n" +
            "    \"gnetId\": null,\n" +
            "    \"graphTooltip\": 0,\n" +
            "    \"id\": null,\n" +
            "    \"links\": [],\n" +
            "    \"panels\": [],\n" +
            "    \"refresh\": \"5s\",\n" +
            "    \"schemaVersion\": 27,\n" +
            "    \"style\": \"dark\",\n" +
            "    \"tags\": [],\n" +
            "    \"templating\": {\n" +
            "      \"list\": []\n" +
            "    },\n" +
            "    \"time\": {\n" +
            "      \"from\": \"now-15m\",\n" +
            "      \"to\": \"now\"\n" +
            "    },\n" +
            "    \"timepicker\": {\n" +
            "      \"refresh_intervals\": [\n" +
            "        \"5s\",\n" +
            "        \"10s\",\n" +
            "        \"30s\",\n" +
            "        \"1m\",\n" +
            "        \"5m\",\n" +
            "        \"15m\",\n" +
            "        \"30m\",\n" +
            "        \"1h\",\n" +
            "        \"2h\",\n" +
            "        \"1d\"\n" +
            "      ]\n" +
            "    },\n" +
            "    \"timezone\": \"\",\n" +
            "    \"title\": \"#{dashboard_title}\",\n" +
            "    \"uid\": null,\n" +
            "    \"version\": 0\n" +
//            "    \"id\": null,\n" +
//            "    \"uid\": null,\n" +
//            "    \"title\": \"#{dashboard_title}\",\n" +
//            "    \"tags\": [],\n" +
//            "    \"timezone\": \"\",\n" +
//            "    \"time\": {\n" +
//            "      \"from\": \"now-15m\",\n" +
//            "      \"to\": \"now\"\n" +
//            "    },\n" +
//            "    \"schemaVersion\": 27,\n" +
//            "    \"version\": 0,\n" +
//            "    \"refresh\": \"15s\"\n" +
            "  },\n" +
            "  \"folderId\": 0,\n" +
            "  \"overwrite\": false\n" +
            "}";

    public static final String placeholder_id = "#{panel_id}";
    public static final String placeholder_des = "#{panel_des}";
    public static final String placeholder_titlt = "#{panel_title}";
    public static final String placeholder_alias = "#{panel_alias}";
    public static final String placeholder_nodeSn = "#{panel_nodeSn}";
    public static final String placeholder_attrKey = "#{panel_attrKey}";
    public static final String placeholder_sceneSn = "#{sceneSn}";
    public static final String placeholder_height = "#{panel_height}";
    public static final String placeholder_width = "#{panel_width}";
    public static final String placeholder_xAxis = "#{panel_xAxis}";
    public static final String placeholder_yAxis = "#{panel_yAxis}";
    public static final String placeholder_dash_title = "#{dashboard_title}";

//    public static final JSONObject panelMinJson = JSONObject.parseObject(PANEL_MIN);
//    public static final JSONObject panelMaxJson = JSONObject.parseObject(PANEL_MAX);
//    public static final JSONObject panelStatJson = JSONObject.parseObject(PANEL_STAT);
//    public static final JSONObject panelHeatmapJson = JSONObject.parseObject(PANEL_HEATMAP);
//    public static final JSONObject panelDistributeJson = JSONObject.parseObject(PANEL_DISTRIBUTE);
//    public static final JSONObject panelLineJson = JSONObject.parseObject(PANEL_LINE);
//    public static final JSONObject dashboardStander = JSONObject.parseObject(DASHBOARD_STANDER);

    public enum PanelTemp{
        MIN, MAX, STAT, HEATMAP, DISTRIBUTE, LINE
    }

    @Getter
    @Setter
    public static class Panel{
        private PanelTemp type;
        private int id;
        private String title;
        private String alias;
        private String description;
        private String sceneSn;
        private String nodeSn;
        private String attrKey;
        private int height, width, xAxis, yAxis;
        public Panel(){}
        public Panel(PanelTemp type, int id, String title, String alias, String description, String sceneSn, String nodeSn, String attrKey, int height, int width, int xAxis, int yAxis) {
            this.type = type;
            this.id = id; //必须初始化id，不然前端抛异常
            this.title = title;
            this.alias = alias;
            this.description = description;
            this.sceneSn = sceneSn;
            this.nodeSn = nodeSn;
            this.attrKey = attrKey;
            this.height = height;
            this.width = width;
            this.xAxis = xAxis;
            this.yAxis = yAxis;
        }
    }

    public static JSONObject addPanel(JSONObject dashboard, Panel ...panels){
        if(dashboard == null) return null;
        if(panels != null && panels.length>0){
            for (Panel panel : panels){
                String temp = null;
                switch (panel.getType()){
                    case MIN:
                        temp = PANEL_MIN;
                        break;
                    case MAX:
                        temp = PANEL_MAX;
                        break;
                    case STAT:
                        temp = PANEL_STAT;
                        break;
                    case HEATMAP:
                        temp = PANEL_HEATMAP;
                        break;
                    case DISTRIBUTE:
                        temp = PANEL_DISTRIBUTE;
                        break;
                    case LINE:
                        temp = PANEL_LINE;
                        break;
                    default:
                        return dashboard;
                }
                temp = temp.replace(placeholder_des, panel.getDescription())
                        .replace(placeholder_id, String.valueOf(panel.getId()))
                        .replace(placeholder_titlt, panel.getTitle())
                        .replace(placeholder_sceneSn, panel.getSceneSn())
                        .replace(placeholder_nodeSn, panel.getNodeSn())
                        .replace(placeholder_attrKey, panel.getAttrKey())
                        .replace(placeholder_height, String.valueOf(panel.getHeight()))
                        .replace(placeholder_width, String.valueOf(panel.getWidth()))
                        .replace(placeholder_xAxis, String.valueOf(panel.getXAxis()))
                        .replace(placeholder_yAxis, String.valueOf(panel.getYAxis()));
                if(StringUtils.isNotBlank(panel.getAlias())){
                    temp = temp.replace(placeholder_alias, panel.getAlias());
                }
                JSONObject jsonObject = JSONObject.parseObject(temp);
                if(!dashboard.containsKey("panels")){
                    dashboard.put("panels", new JSONArray());
                }
                dashboard.getJSONArray("panels").add(jsonObject);
//                temp.put("description", panel.getDescription());
//                temp.put("title", panel.getTitle());
//                JSONObject gridPos = temp.getJSONObject("gridPos");
//                gridPos.put("h", panel.getHeight());
//                gridPos.put("w", panel.getWidth());
//                gridPos.put("x", panel.getXAxis());
//                gridPos.put("y", panel.getYAxis());
//                JSONObject target = (JSONObject) temp.getJSONArray("targets").get(0);//targets只有一个元素
//                target.put("measurement", panel.getSceneSn());
//                if(StringUtils.isNotBlank(panel.getAlias())){
//                    target.put("alias", panel.getAlias());
//                }
//                JSONArray select = (JSONArray) target.getJSONArray("select").get(0);//select只有一个元素
//                for(Object selectItem: select){
//                    JSONObject sij = (JSONObject) selectItem;
//                    if("field".equals(sij.getString("type"))){
//                        ((JSONArray)sij.get("params")).add(panel.getAttrKey());
//                        break;
//                    }
//                }
//                JSONObject tag = (JSONObject) target.getJSONArray("tags").get(0);
//                tag.put("value", panel.getNodeSn());
//                dashboard.getJSONArray("panels").add(temp);

            }
        }
        return dashboard;
    }
}
