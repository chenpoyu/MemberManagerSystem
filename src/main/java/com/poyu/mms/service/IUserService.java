package com.poyu.mms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort.Direction;

import com.poyu.mms.entity.po.TbUser;
import com.poyu.mms.entity.vo.UserVo;

public interface IUserService {
	public void insert(UserVo user);

	public void update(UserVo user);

	public void deleteByPk(String pk);

	public List<TbUser> findAll(int page, int size, String sort, Direction direction);

	public Optional<TbUser> findById(String id);

	public Optional<TbUser> findByAccount(String account);

}