package com.xidian.iot.database.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author: Hansey
 * @date: 2020-10-19 16:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class PayloadDto {
    @ApiModelProperty("主题")
    private String sub;
    @ApiModelProperty("签发时间")
    private Long iat;
    @ApiModelProperty("过期时间")
    private Long exp;
//    @ApiModelProperty("JWT的ID")
//    private String jti;
//    @ApiModelProperty("用户名称")
//    private String username;

}
