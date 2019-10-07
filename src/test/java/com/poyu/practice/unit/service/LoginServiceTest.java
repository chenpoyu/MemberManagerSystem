package com.poyu.practice.unit.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.poyu.mms.MemberManagerSystemApplication;
import com.poyu.mms.dao.ITbUserRepository;
import com.poyu.mms.entity.po.TbUser;
import com.poyu.mms.entity.vo.UserVo;
import com.poyu.mms.service.ILoginService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MemberManagerSystemApplication.class)
public class LoginServiceTest {

	@Autowired
	private ILoginService loginService;

	@MockBean
	private ITbUserRepository userRepository;

	@Before
	public void setup() {
	}

	@Test
	public void login() throws Exception {
		TbUser user = new TbUser("tester", "1234");
		when(userRepository.findByAccount("tester")).thenReturn(Optional.of(user));

		UserVo userVo = new UserVo();
		userVo.setAccount("tester");
		userVo.setPwd("1234");
		// 正確帳號密碼
		assertEquals(user, loginService.login(userVo).get());

		// 錯誤帳號密碼
		userVo.setPwd("4321");
		assertEquals(null, loginService.login(userVo));
	}
}
