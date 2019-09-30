package com.poyu.mms.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poyu.mms.dao.ITbUserRepository;
import com.poyu.mms.entity.po.TbUser;
import com.poyu.mms.entity.vo.UserVo;
import com.poyu.mms.service.ILoginService;

@Service
public class LoginService implements ILoginService {

	@Autowired
	private ITbUserRepository userRepository;

	@Override
	public Optional<TbUser> login(UserVo user) {
		// TODO
		// 1.write in login table
		// 2.pass md5
		Optional<TbUser> u = userRepository.findByAccount(user.getAccount());
		return u.map(mapper -> user.getPwd().equals(mapper.getPasswd()) ? u : null).orElse(null);
	}

	@Override
	public void register(UserVo user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void forgot(String account) throws Exception {
//		if (StringUtils.isEmpty(account)) {
//			throw new IllegalArgumentException("帳號不可為空");
//		}
	}

}
