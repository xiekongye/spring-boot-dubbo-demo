package com.arthas.study.springbootdubbodemo.api.serviceprocessor.commodity;

import com.arthas.springbootdubbodemo.contract.commodity.QueryCommodityDetailRequest;
import com.arthas.springbootdubbodemo.contract.commodity.QueryCommodityDetailResponse;
import com.arthas.study.springbootdubbodemo.api.serviceprocessor.BaseServiceProcessor;
import com.arthas.study.springbootdubbodemo.model.annotations.RPCServiceProcessor;

/**
 * @author : lieying
 * date : 2018/6/28 20:37
 */
@RPCServiceProcessor(name = "商品详情处理器")
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
