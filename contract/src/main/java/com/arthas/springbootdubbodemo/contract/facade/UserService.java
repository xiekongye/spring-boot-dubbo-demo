package com.arthas.springbootdubbodemo.contract.facade;

import com.arthas.springbootdubbodemo.contract.entity.User;

/**
 * @author : lieying
 * date : 2018/6/26 22:02
 */
public interface UserService {

	User getUserInfoById(String userId);

}
