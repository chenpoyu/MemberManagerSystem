package com.poyu.mms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.poyu.mms.dao.ITbUserRepository;
import com.poyu.mms.entity.po.TbUser;
import com.poyu.mms.entity.vo.UserVo;
import com.poyu.mms.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private ITbUserRepository userRepository;

	@Override
	public void insert(UserVo userVo) {
		TbUser user = TbUser.transToPo(userVo);
		userRepository.save(user);
	}

	@Override
	public void update(UserVo userVo) {
		TbUser user = TbUser.transToPo(userVo);
		userRepository.save(user);
	}

	@Override
	public void deleteByPk(String pk) {
		userRepository.deleteById(pk);
	}

	@Override
	public Optional<TbUser> findById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<TbUser> findByAccount(String account) {
		return userRepository.findByAccount(account);
	}

	@Override
	public List<TbUser> findAll(int page, int size, String sort, Direction direction) {
		Pageable paging = PageRequest.of(page, size, direction, sort);
		Slice<TbUser> slicedResult = userRepository.findAll(paging);
		return slicedResult.getContent();
	}
}
