package com.poyu.mms.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poyu.mms.entity.po.TbUser;
import com.poyu.mms.entity.vo.UserVo;
import com.poyu.mms.service.IUserService;

@RestController
@RequestMapping("/api")
public class UserController {

	// TODO
	// 1.Entity -> VO because of the security
	// 2.Validate

	@Autowired
	private IUserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<UserVo>> getUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "createTime") String sort) {
		List<TbUser> userList = userService.findAll(page, size, sort, Direction.ASC);
		List<UserVo> voList = userList.stream().map(mapper -> UserVo.transToVo(mapper)).collect(Collectors.toList());
		if (voList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(voList);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserVo> getUser(@PathVariable String id) {
		Optional<TbUser> user = userService.findById(id);
		return user.map(mapper -> ResponseEntity.ok(UserVo.transToVo(mapper)))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/user")
	public ResponseEntity<?> addUser(@Valid @RequestBody UserVo userVo) {
		userService.insert(userVo);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable String id, @Valid @RequestBody UserVo userVo) {
		userVo.setId(id);
		userService.update(userVo);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable String id) {
		userService.deleteByPk(id);
		return ResponseEntity.ok().build();
	}
}
