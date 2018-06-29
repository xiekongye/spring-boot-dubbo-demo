package com.arthas.study.springbootdubbodemo.api.httpexport;

import lombok.Data;

/**
 * @author : lieying
 * date : 2018/6/29 19:48
 */
@Data
public final class HttpResponse {

	private boolean success;//成功标志

	private String code;//信息码

	private String description;//描述

}
