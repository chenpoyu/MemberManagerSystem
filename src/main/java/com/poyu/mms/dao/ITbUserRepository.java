package com.poyu.mms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poyu.mms.entity.po.TbUser;

public interface ITbUserRepository extends JpaRepository<TbUser, String> {

	Optional<TbUser> findByAccount(String account);

}