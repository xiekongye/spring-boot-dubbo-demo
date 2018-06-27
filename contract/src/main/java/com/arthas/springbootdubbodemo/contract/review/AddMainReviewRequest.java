package com.arthas.springbootdubbodemo.contract.review;

import com.arthas.springbootdubbodemo.contract.base.ServiceBaseRequest;
import lombok.Data;

/**
 * 增加评论请求
 * @author : lieying
 * date : 2018/6/27 16:12
 */
@Data
public class AddMainReviewRequest extends ServiceBaseRequest {

	private String uid;

	private String orderId;

	private String goodsId;

	private String comment;

}
