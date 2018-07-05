package com.arthas.study.springbootdubbodemo.api.serviceentry;

import com.alibaba.dubbo.config.annotation.Service;
import com.arthas.springbootdubbodemo.contract.commodity.QueryCommodityDetailRequest;
import com.arthas.springbootdubbodemo.contract.commodity.QueryCommodityDetailResponse;
import com.arthas.springbootdubbodemo.contract.commodity.facade.IQueryCommodityDetailService;
import com.arthas.study.springbootdubbodemo.api.serviceprocessor.BaseServiceProcessor;
import com.arthas.study.springbootdubbodemo.model.annotations.DubboService;
import com.arthas.study.springbootdubbodemo.model.annotations.DubboServiceMethod;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * @author : lieying
 * date : 2018/6/27 18:02
 */
@DubboService
@Component
@Service
public class QueryCommodityDetailService implements IQueryCommodityDetailService {

	private BaseServiceProcessor<QueryCommodityDetailRequest,QueryCommodityDetailResponse> queryCommodityDetailProcessor;

	@Inject
	public QueryCommodityDetailService(BaseServiceProcessor<QueryCommodityDetailRequest,QueryCommodityDetailResponse> queryCommodityDetailProcessor){
		this.queryCommodityDetailProcessor = queryCommodityDetailProcessor;
	}

	@Override
	@DubboServiceMethod
	public QueryCommodityDetailResponse queryCommodityDetail(QueryCommodityDetailRequest request) {
		return new QueryCommodityDetailResponse();
	}
}
