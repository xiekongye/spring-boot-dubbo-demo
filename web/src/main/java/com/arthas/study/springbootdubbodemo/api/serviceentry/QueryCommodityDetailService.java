package com.arthas.study.springbootdubbodemo.api.serviceentry;

import com.arthas.springbootdubbodemo.common.components.ExportService;
import com.arthas.springbootdubbodemo.contract.commodity.QueryCommodityDetailRequest;
import com.arthas.springbootdubbodemo.contract.commodity.QueryCommodityDetailResponse;
import com.arthas.springbootdubbodemo.contract.commodity.facade.IQueryCommodityDetailService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : lieying
 * date : 2018/6/27 18:02
 */
@ExportService(httpPrefix = {"/queryCommodityDetail"})
@RestController
public class QueryCommodityDetailService implements IQueryCommodityDetailService {

	@Override
	public QueryCommodityDetailResponse queryCommodityDetail(QueryCommodityDetailRequest request) {
		return new QueryCommodityDetailResponse();
	}
}
