package com.xidian.iot.dataapi.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
            "          \"measurement\": \"#{measurement}\",\n" +
            "          \"orderByTime\": \"ASC\",\n" +
            "          \"policy\": \"default\",\n" +
            "          \"refId\": \"A\",\n" +
            "          \"resultFormat\": \"time_series\",\n" +
            "          \"select\": [\n" +
            "            [\n" +
            "              {\n" +
            "                \"params\": [\n" +
            "                  \"#{panel_column}\"\n" +
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
            "              \"value\": \"#{panel_tag}\"\n" +
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
            "          \"measurement\": \"#{measurement}\",\n" +
            "          \"orderByTime\": \"ASC\",\n" +
            "          \"policy\": \"default\",\n" +
            "          \"refId\": \"A\",\n" +
            "          \"resultFormat\": \"time_series\",\n" +
            "          \"select\": [\n" +
            "            [\n" +
            "              {\n" +
            "                \"params\": [\n" +
            "                  \"#{panel_column}\"\n" +
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
            "              \"value\": \"#{panel_tag}\"\n" +
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
            "          \"measurement\": \"#{measurement}\",\n" +
            "          \"orderByTime\": \"ASC\",\n" +
            "          \"policy\": \"default\",\n" +
            "          \"refId\": \"A\",\n" +
            "          \"resultFormat\": \"time_series\",\n" +
            "          \"select\": [\n" +
            "            [\n" +
            "              {\n" +
            "                \"params\": [\n" +
            "                  \"#{panel_column}\"\n" +
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
            "              \"value\": \"#{panel_tag}\"\n" +
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
            "          \"measurement\": \"#{measurement}\",\n" +
            "          \"orderByTime\": \"ASC\",\n" +
            "          \"policy\": \"default\",\n" +
            "          \"refId\": \"A\",\n" +
            "          \"resultFormat\": \"time_series\",\n" +
            "          \"select\": [\n" +
            "            [\n" +
            "              {\n" +
            "                \"params\": [\n" +
            "                  \"#{panel_column}\"\n" +
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
            "              \"value\": \"#{panel_tag}\"\n" +
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
            "          \"measurement\": \"#{measurement}\",\n" +
            "          \"orderByTime\": \"ASC\",\n" +
            "          \"policy\": \"default\",\n" +
            "          \"refId\": \"A\",\n" +
            "          \"resultFormat\": \"time_series\",\n" +
            "          \"select\": [\n" +
            "            [\n" +
            "              {\n" +
            "                \"params\": [\n" +
            "                  \"#{panel_column}\"\n" +
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
            "              \"value\": \"#{panel_tag}\"\n" +
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
            "          \"measurement\": \"#{measurement}\",\n" +
            "          \"orderByTime\": \"ASC\",\n" +
            "          \"policy\": \"default\",\n" +
            "          \"refId\": \"A\",\n" +
            "          \"resultFormat\": \"time_series\",\n" +
            "          \"select\": [\n" +
            "            [\n" +
            "              {\n" +
            "                \"params\": [\n" +
            "                  \"#{panel_column}\"\n" +
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
            "              \"value\": \"#{panel_tag}\"\n" +
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
    public static final String placeholder_tag = "#{panel_tag}";
    public static final String placeholder_column = "#{panel_column}";
    public static final String placeholder_measurement = "#{measurement}";
    public static final String placeholder_height = "#{panel_height}";
    public static final String placeholder_width = "#{panel_width}";
    public static final String placeholder_xAxis = "#{panel_xAxis}";
    public static final String placeholder_yAxis = "#{panel_yAxis}";
    public static final String placeholder_dash_title = "#{dashboard_title}";

    public enum PanelTemp{
        MIN, MAX, STAT, HEATMAP, DISTRIBUTE, LINE
    }

    // influx中，每个scene使用一个表，nodeSn作为表中的索引列(influx中tag的概念)
    @Getter
    @Setter
    public static class Panel{
        private PanelTemp type;
        private int id;
        private String title;
        private String alias;
        private String description;
        private String measurement;  // 查询表，对应sceneSn
        private String tag;  // 查询条件，对应nodeSn
        private String column;  // 查询字段，对应attrKey
        private int height, width, xAxis, yAxis;
        public Panel(){}
        public Panel(PanelTemp type, int id, String title, String alias, String description, String measurement, String tag, String column, int height, int width, int xAxis, int yAxis) {
            this.type = type;
            this.id = id; //必须初始化id，不然前端抛异常
            this.title = title;
            this.alias = alias;
            this.description = description;
            this.measurement = measurement;
            this.tag = tag;
            this.column = column;
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
                        .replace(placeholder_measurement, panel.getMeasurement())
                        .replace(placeholder_tag, panel.getTag())
                        .replace(placeholder_column, panel.getColumn())
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
            }
        }
        return dashboard;
    }

    /**
     * 根据panel最小集合，生成dashboard的panel冗余集合，dashboard效果见 /static/dashboard_pretty.json
     * panelsMin中panel的measurement、tag、column字段不能为空
     * @param panelsMin
     * @return
     */
    public static List<Panel> prettyDashboardPanels(List<Panel> panelsMin){
        if(CollectionUtils.isEmpty(panelsMin)) return null;
        List<Panel> panelsPretty = new ArrayList<>();
        Panel currPanel = null;
        int panelIdx = 0;

        currPanel = panelsMin.get(panelIdx);
        Panel maxPanel = new GrafanaFormatUtil.Panel(PanelTemp.MAX, 1
                , currPanel.getTag()+"."+currPanel.getColumn()+"最大值", currPanel.getTag()+"."+currPanel.getColumn(), ""
                , currPanel.getMeasurement(), currPanel.getTag(), currPanel.getColumn()
                ,8,4,0,0);
        Panel minPanel = new GrafanaFormatUtil.Panel(PanelTemp.MIN, 2
                , currPanel.getTag()+"."+currPanel.getColumn()+"最小值", currPanel.getTag()+"."+currPanel.getColumn(), ""
                , currPanel.getMeasurement(), currPanel.getTag(), currPanel.getColumn()
                ,8,4,4,0);
        Panel statPanel = new GrafanaFormatUtil.Panel(PanelTemp.STAT, 3
                , currPanel.getTag()+"."+currPanel.getColumn()+"状态图", currPanel.getTag()+"."+currPanel.getColumn(), ""
                , currPanel.getMeasurement(), currPanel.getTag(), currPanel.getColumn()
                ,8,16,8,0);

        if(panelsMin.size()>1) ++ panelIdx;
        currPanel = panelsMin.get(panelIdx);
        Panel heatmapPanel = new GrafanaFormatUtil.Panel(PanelTemp.HEATMAP, 4
                , currPanel.getTag()+"."+currPanel.getColumn()+"热力图", currPanel.getTag()+"."+currPanel.getColumn(), ""
                , currPanel.getMeasurement(), currPanel.getTag(), currPanel.getColumn()
                ,8,12,0,8);

        if(panelsMin.size()>2) ++ panelIdx;
        currPanel = panelsMin.get(panelIdx);
        Panel distributePanel = new GrafanaFormatUtil.Panel(PanelTemp.DISTRIBUTE, 5
                , currPanel.getTag()+"."+currPanel.getColumn()+"分布图", currPanel.getTag()+"."+currPanel.getColumn(), ""
                , currPanel.getMeasurement(), currPanel.getTag(), currPanel.getColumn()
                ,8,12,12,8);

        panelsPretty.add(maxPanel);
        panelsPretty.add(minPanel);
        panelsPretty.add(statPanel);
        panelsPretty.add(heatmapPanel);
        panelsPretty.add(distributePanel);
        if(panelsMin.size()>3){
            panelsMin.remove(0);
            panelsMin.remove(0);
            panelsMin.remove(0);
        }
        panelsPretty.addAll(defaultDashboardPanels(panelsMin, 16, 6));

        return panelsPretty;
    }

    /**
     * 根据panel最小集合，生成dashboard的panel冗余集合，dashboard效果见 /static/dashboard_default.json
     * @param panelsMin
     * @param startY 起始y坐标
     * @param startId 起始Id
     * @return
     */
    public static List<Panel> defaultDashboardPanels(List<Panel> panelsMin, int startY, int startId){
        if(CollectionUtils.isEmpty(panelsMin)) return null;
        List<Panel> panelsDefault = new ArrayList<>();
        for (int i=0; i< panelsMin.size(); i++){
            Panel currPanel = panelsMin.get(i);
            Panel defaultPanel = new GrafanaFormatUtil.Panel(PanelTemp.LINE, ++ startId
                    , currPanel.getTag()+"."+currPanel.getColumn()+"折线图", currPanel.getTag()+"."+currPanel.getColumn(), ""
                    , currPanel.getMeasurement(), currPanel.getTag(), currPanel.getColumn()
                    ,8,12,12*(i%2),startY+8*(i/2));
            panelsDefault.add(defaultPanel);
        }
        return panelsDefault;
    }

}
