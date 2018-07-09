package com.arthas.study.springbootdubbodemo.service.review;

import com.arthas.springbootdubbodemo.common.utils.SerializeUtils;
import com.arthas.study.springbootdubbodemo.model.po.UserPO;
import com.arthas.study.springbootdubbodemo.repository.dao.facade.IUserMapper;
import com.arthas.study.springbootdubbodemo.service.review.facade.ITestReview;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author : lieying
 * date : 2018/7/9 19:53
 */
@Service
public class TestReview implements ITestReview {

	private IUserMapper userMapper;

	@Inject
	public TestReview(IUserMapper userMapper){
		this.userMapper = userMapper;
	}

	@Override
	public String test(String key) {

		UserPO user = userMapper.getUserById(1);

		return SerializeUtils.toJson(user);
	}
}
