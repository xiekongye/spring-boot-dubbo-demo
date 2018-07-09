package com.arthas.study.springbootdubbodemo.api.serviceprocessor.review;

import com.arthas.springbootdubbodemo.contract.review.AddMainReviewRequest;
import com.arthas.springbootdubbodemo.contract.review.AddMainReviewResponse;
import com.arthas.study.springbootdubbodemo.api.serviceprocessor.BaseServiceProcessor;
import com.arthas.study.springbootdubbodemo.model.annotations.RPCServiceProcessor;
import com.arthas.study.springbootdubbodemo.service.review.facade.ITestReview;

import javax.inject.Inject;

/**
 * 评论更新处理类
 * @author : lieying
 * date : 2018/6/27 16:54
 */
@RPCServiceProcessor(value ="platformReviewUpdate", name = "评论更新处理器")
public class PlatformReviewUpdateProcessor extends BaseServiceProcessor<AddMainReviewRequest,AddMainReviewResponse> {

	private ITestReview testReview;

	@Inject
	public PlatformReviewUpdateProcessor(ITestReview testReview){
		this.testReview = testReview;
	}

	@Override
	protected boolean validateRequest(AddMainReviewRequest request) throws IllegalArgumentException {
		return true;
	}

	@Override
	protected AddMainReviewResponse process(AddMainReviewRequest request) {

		AddMainReviewResponse response = new AddMainReviewResponse();

		String value = testReview.test(request.getUid());

		response.setReviewId(value);

		return response;
	}
}
