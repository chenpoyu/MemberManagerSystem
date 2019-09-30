package com.poyu.mms.entity.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.poyu.mms.entity.vo.UserVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class TbUser {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(length = 36)
	private String id;

	@NonNull
	@Column(name = "account", length = 40, unique = true, nullable = false)
	private String account;

	@NonNull
	@Column(name = "passwd", length = 40, nullable = false)
	private String passwd;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "create_time", nullable = false, insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@Column(name = "alter_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date alterTime;

	public static TbUser transToPo(UserVo userVo) {
		return new TbUser(userVo.getId(), userVo.getAccount(), userVo.getPwd(), userVo.getName(),
				userVo.getCreateTime(), userVo.getAlterTime());
	}
}