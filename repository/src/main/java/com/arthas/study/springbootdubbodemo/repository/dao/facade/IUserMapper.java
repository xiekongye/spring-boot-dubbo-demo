package com.arthas.study.springbootdubbodemo.repository.dao.facade;

import com.arthas.study.springbootdubbodemo.model.po.UserPO;
import org.apache.ibatis.annotations.Param;

/**
 * @author : lieying
 * date : 2018/7/9 19:48
 */
public interface IUserMapper {

	UserPO getUserById(@Param("id") Integer id);

}
