package com.arthas.springbootdubbodemo.contract.commodity.facade;

import com.arthas.springbootdubbodemo.contract.base.ServiceMeta;
import com.arthas.springbootdubbodemo.contract.commodity.QueryCommodityDetailRequest;
import com.arthas.springbootdubbodemo.contract.commodity.QueryCommodityDetailResponse;

/**
 * @author : lieying
 * date : 2018/6/27 18:00
 */
@ServiceMeta(serviceName = "queryCommodityDetailService",serviceNamespace = "",version = "1.0.0")
public interface IQueryCommodityDetailService {

	QueryCommodityDetailResponse queryCommodityDetail(QueryCommodityDetailRequest request);

}
