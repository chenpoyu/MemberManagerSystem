package com.poyu.mms.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poyu.mms.entity.po.TbUser;
import com.poyu.mms.entity.vo.UserVo;
import com.poyu.mms.service.ILoginService;

@Controller
public class LoginController {

	@Autowired
	private ILoginService loginService;

	@RequestMapping("/")
	public String login() {
		return "login";
	}

	@PostMapping("/api/login")
	public ResponseEntity<?> login(@Valid @RequestBody UserVo userVo) {
		Optional<TbUser> user = loginService.login(userVo);
		String displayName = "";
		if (user.isPresent()) {
			if ("".equals(user.get().getName())) {
				displayName = user.get().getAccount();
			} else {
				displayName = user.get().getName();
			}
		}
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("token", "testToken");
		rtnMap.put("name", displayName);
		return user.map(mapper -> ResponseEntity.ok(rtnMap)).orElse(ResponseEntity.badRequest().build());
	}

	@PostMapping("/api/register")
	public ResponseEntity<?> register(@Valid @RequestBody UserVo userVo) {
		loginService.register(userVo);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/api/forgot/{account}")
	public ResponseEntity<?> forgot(@PathVariable String account) throws Exception {
		loginService.forgot(account);
		return ResponseEntity.ok().build();
	}

	@RequestMapping("/api/logout/{id}")
	public String logout(@PathVariable String id) {
		return "login";
	}
}
