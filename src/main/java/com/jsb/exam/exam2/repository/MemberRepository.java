package com.jsb.exam.exam2.repository;

import com.jsb.exam.exam2.container.ContainerComponent;
import com.jsb.exam.exam2.dto.Member;
import com.jsb.mysqliutil.MysqlUtil;
import com.jsb.mysqliutil.SecSql;

public class MemberRepository implements ContainerComponent {
	public void init() {

	}

	// 재구현 완료 21-08-15
	public Member getMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM member AS M");
		sql.append("WHERE M.loginId = ?", loginId);
		
		return MysqlUtil.selectRow(sql, Member.class);
	}

	// 재구현 완료 21-08-16
	public int join(String loginId, String loginPw, String loginPwConfirm, String name, String nickname, String email, String cellphoneNo) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO `member`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", loginPwConfirm = ?", loginPwConfirm);
		sql.append(", `name` = ?", name);
		sql.append(", nickname = ?", nickname);
		sql.append(", cellphoneNo = ?", cellphoneNo);
		sql.append(", email = ?", email);
		
		int id = MysqlUtil.insert(sql);
		
		return id;
	}

	// 재구현 완료 21-08-16
	public Member getMemberByNameAndEmail(String name, String email) {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM member AS M");
		sql.append("WHERE M.name = ?", name);
		sql.append("AND M.email = ?", email);
		sql.append("LIMIT 1");
		
		return MysqlUtil.selectRow(sql, Member.class);
	}

	// 재구현 완료 21-08-17
	public void modifyPassword(int id, String loginPw) {
		SecSql sql = new SecSql();
		sql.append("UPDATE `member`");
		sql.append("SET updateDate = NOW()");
		sql.append(", loginPw = ?", loginPw);
		sql.append("WHERE id = ?", id);

		MysqlUtil.update(sql);
	}

	// 재구현 완료 21-08-17
	public Member getMemberByNickname(String nickname) {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM member AS M");
		sql.append("WHERE M.nickname = ?", nickname);
		
		return MysqlUtil.selectRow(sql, Member.class);
	}

}
