package com.arthas.springbootdubbodemo.contract.review;

import com.arthas.springbootdubbodemo.contract.base.ServiceBaseResponse;
import lombok.Data;

/**
 * @author : lieying
 * date : 2018/6/27 16:15
 */
@Data
public class AddMainReviewResponse extends ServiceBaseResponse {

	private boolean success;

	private String reviewId;

}
