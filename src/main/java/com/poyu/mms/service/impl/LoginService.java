package com.poyu.mms.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
	public void register(UserVo userVo) {
		if (StringUtils.isEmpty(userVo.getEmail())) {
			throw new IllegalArgumentException("Email不可為空");
		} else if (userRepository.findByEmail(userVo.getEmail()).isPresent()) {
			throw new IllegalArgumentException("Email不可使用");
		}
		TbUser user = TbUser.transToPo(userVo);
		userRepository.save(user);
	}

	@Override
	public void forgot(String account) throws Exception {
		Optional<TbUser> user = userRepository.findByAccount(account);
		if (user.isPresent()) {

		}
	}

}
