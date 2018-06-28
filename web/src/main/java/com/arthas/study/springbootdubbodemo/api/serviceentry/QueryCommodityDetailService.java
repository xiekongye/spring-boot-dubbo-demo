package com.arthas.study.springbootdubbodemo.api.serviceentry;

import com.alibaba.dubbo.config.annotation.Service;
import com.arthas.springbootdubbodemo.contract.commodity.QueryCommodityDetailRequest;
import com.arthas.springbootdubbodemo.contract.commodity.QueryCommodityDetailResponse;
import com.arthas.springbootdubbodemo.contract.commodity.facade.IQueryCommodityDetailService;
import com.arthas.study.springbootdubbodemo.api.serviceprocessor.BaseServiceProcessor;
import org.springframework.stereotype.Component;

/**
 * @author : lieying
 * date : 2018/6/27 18:02
 */
@Service(interfaceClass = IQueryCommodityDetailService.class)
@Component
public class QueryCommodityDetailService implements IQueryCommodityDetailService {

	private BaseServiceProcessor<QueryCommodityDetailRequest,QueryCommodityDetailResponse> queryCommodityDetailProcessor;

	public QueryCommodityDetailService(BaseServiceProcessor<QueryCommodityDetailRequest,QueryCommodityDetailResponse> queryCommodityDetailProcessor){
		this.queryCommodityDetailProcessor = queryCommodityDetailProcessor;
	}

	@Override
	public QueryCommodityDetailResponse queryCommodityDetail(QueryCommodityDetailRequest request) {
		return new QueryCommodityDetailResponse();
	}
}
