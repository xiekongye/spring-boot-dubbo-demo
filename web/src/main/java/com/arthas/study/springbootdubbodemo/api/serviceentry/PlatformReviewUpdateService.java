package com.arthas.study.springbootdubbodemo.api.serviceentry;

import com.alibaba.dubbo.config.annotation.Service;
import com.arthas.springbootdubbodemo.contract.review.AddMainReviewRequest;
import com.arthas.springbootdubbodemo.contract.review.AddMainReviewResponse;
import com.arthas.springbootdubbodemo.contract.review.facade.IPlatformReviewUpdateService;
import com.arthas.study.springbootdubbodemo.api.serviceprocessor.BaseServiceProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : lieying
 * date : 2018/6/27 16:33
 */
@Service(interfaceClass = IPlatformReviewUpdateService.class)
@Component
public class PlatformReviewUpdateService implements IPlatformReviewUpdateService {

	private BaseServiceProcessor<AddMainReviewRequest,AddMainReviewResponse> platformReviewUpdateProcessor;

	public PlatformReviewUpdateService(BaseServiceProcessor<AddMainReviewRequest,AddMainReviewResponse> platformReviewUpdateProcessor){
		this.platformReviewUpdateProcessor = platformReviewUpdateProcessor;
	}


	/**
	 * 新增评论
	 *
	 * @param addMainReviewRequest
	 */
	@Override
	@RequestMapping(value = {"/addMainReview"})
	public AddMainReviewResponse addMainReview(AddMainReviewRequest addMainReviewRequest) {
		return this.platformReviewUpdateProcessor.processSoa(addMainReviewRequest);
	}
}
