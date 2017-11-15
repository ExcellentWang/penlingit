package com.ontheroad.mysql.Mapper.AppMapper;

import com.ontheroad.pojo.user.User;

import java.util.List;
import java.util.Map;

public interface AppUserMapper {

	List<User> findUserByPhone(Map<Object, Object> map);

	void insertAppUser(User user);

	User findUserByPhonePassword(Map<Object, Object> map);

	void updatePassword(Map<Object,Object> map);

	List<User> findUserByIdPassword(Map<Object,Object> map);

	void updatePhone(Map<Object, Object> fieldMap);

	void updateData(User user);

	void updateLogin(User user);

	User getUserDetail(int user_id);

}
