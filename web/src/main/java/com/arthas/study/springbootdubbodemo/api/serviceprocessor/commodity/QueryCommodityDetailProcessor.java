package com.arthas.study.springbootdubbodemo.api.serviceprocessor.commodity;

import com.arthas.springbootdubbodemo.contract.commodity.QueryCommodityDetailRequest;
import com.arthas.springbootdubbodemo.contract.commodity.QueryCommodityDetailResponse;
import com.arthas.study.springbootdubbodemo.api.serviceprocessor.BaseServiceProcessor;
import org.springframework.stereotype.Component;

/**
 * @author : lieying
 * date : 2018/6/28 20:37
 */
@Component
public class QueryCommodityDetailProcessor extends BaseServiceProcessor<QueryCommodityDetailRequest,QueryCommodityDetailResponse> {

	@Override
	protected boolean validateRequest(QueryCommodityDetailRequest request) throws IllegalArgumentException {
		return true;
	}

	@Override
	protected QueryCommodityDetailResponse process(QueryCommodityDetailRequest request) {
		return null;
	}
}
