package com.arthas.study.springbootdubbodemo.api.loadbalance;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;

import java.util.List;

/**
 * @author : lieying
 * date : 2018/7/5 21:37
 */
public class DemoLoadBalance extends AbstractLoadBalance {


	@Override
	protected <T> Invoker<T> doSelect(List<Invoker<T>> list, URL url, Invocation invocation) {
		return null;
	}
}
