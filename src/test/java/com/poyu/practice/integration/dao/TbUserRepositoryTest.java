package com.poyu.practice.integration.dao;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.poyu.mms.MemberManagerSystemApplication;
import com.poyu.mms.dao.ITbUserRepository;
import com.poyu.mms.entity.po.TbUser;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MemberManagerSystemApplication.class)
@Transactional
public class TbUserRepositoryTest {

	@Autowired
	private ITbUserRepository tbUserRepository;

	@Test
	public void findByAccount() {
		// given
		TbUser tbUser = new TbUser("tester", "testpwd");
		tbUser.setEmail("test@test.com");
		tbUserRepository.save(tbUser);

		Optional<TbUser> rtnUser = tbUserRepository.findByAccount("tester");
		assertEquals(tbUser, rtnUser.isPresent() ? rtnUser.get() : null);

		Optional<TbUser> rtnUser2 = tbUserRepository.findByAccount("null");
		assertEquals(null, rtnUser2.isPresent() ? rtnUser2.get() : null);
	}
}