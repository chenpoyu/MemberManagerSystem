package com.poyu.mms.service;

import java.util.Optional;

import com.poyu.mms.entity.po.TbUser;
import com.poyu.mms.entity.vo.UserVo;

public interface ILoginService {
	public Optional<TbUser> login(UserVo user);

	public void register(UserVo user);

	public void forgot(String account) throws Exception;
}