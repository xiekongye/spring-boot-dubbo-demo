package com.arthas.springbootdubbodemo.contract.review.facade;

import com.arthas.springbootdubbodemo.contract.base.ServiceMeta;
import com.arthas.springbootdubbodemo.contract.review.AddMainReviewRequest;
import com.arthas.springbootdubbodemo.contract.review.AddMainReviewResponse;

/**
 * 更新评论服务
 * @author : lieying
 * date : 2018/6/27 16:27
 */
@ServiceMeta(serviceName = "platformReviewQueryService",serviceNamespace = "testnamespace",version = "1.0.0")
public interface IPlatformReviewUpdateService {

	/**
	 * 新增评论
	 * */
	AddMainReviewResponse addMainReview(AddMainReviewRequest addMainReviewRequest);

}
