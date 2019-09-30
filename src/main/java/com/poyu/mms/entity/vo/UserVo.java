package com.poyu.mms.entity.vo;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.poyu.mms.entity.po.TbUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

	@NonNull
	private String id;

	@NonNull
	@NotBlank(message = "帳號不可為空")
	private String account;

	@NonNull
	@NotBlank(message = "密碼不可為空")
	private String pwd;

	private String name;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date alterTime;

	public static UserVo transToVo(TbUser user) {
		return new UserVo(user.getId(), user.getAccount(), user.getPasswd(), user.getName(), user.getCreateTime(),
				user.getAlterTime());
	}
}