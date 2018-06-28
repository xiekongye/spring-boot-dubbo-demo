package com.arthas.study.springbootdubbodemo.api.serviceprocessor.review;

import com.arthas.springbootdubbodemo.contract.review.AddMainReviewRequest;
import com.arthas.springbootdubbodemo.contract.review.AddMainReviewResponse;
import com.arthas.study.springbootdubbodemo.api.serviceprocessor.BaseServiceProcessor;
import org.springframework.stereotype.Component;

/**
 * @author : lieying
 * date : 2018/6/27 16:54
 */
@Component
public class PlatformReviewUpdateProcessor extends BaseServiceProcessor<AddMainReviewRequest,AddMainReviewResponse> {

	@Override
	protected boolean validateRequest(AddMainReviewRequest request) throws IllegalArgumentException {
		return true;
	}

	@Override
	protected AddMainReviewResponse process(AddMainReviewRequest request) {
		return null;
	}
}
