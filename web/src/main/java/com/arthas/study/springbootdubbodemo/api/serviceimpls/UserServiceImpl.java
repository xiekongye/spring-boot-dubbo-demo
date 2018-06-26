package com.arthas.study.springbootdubbodemo.api.serviceimpls;

import com.alibaba.dubbo.config.annotation.Service;
import com.arthas.springbootdubbodemo.contract.entity.User;
import com.arthas.springbootdubbodemo.contract.facade.UserService;
import org.springframework.stereotype.Component;

/**
 * @author : lieying
 * date : 2018/6/26 22:03
 */
@Service
@Component
public class UserServiceImpl implements UserService {

	@Override
	public User getUserInfoById(String userId) {
		return null;
	}
}
