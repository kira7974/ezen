package kr.spring.member.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

public interface MemberMapper {
	@Select("SELECT member_seq.nextval FROM dual")
	public int selectMem_num();
	
	@Insert("INSERT INTO member (mem_num,id) VALUES (#{mem_num},#{id})")
	public void insertMember(MemberVO member);
	
	@Insert("INSERT INTO member_detail (mem_num,name,passwd,phone,email,zipcode,address) VALUES (#{mem_num},#{name},#{passwd},#{phone},#{email},#{zipcode},#{address})")
	public void insertMember_detail(MemberVO member);
	
	@Select("SELECT m.mem_num,m.id,m.auth,d.passwd FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.id=#{id}")
	public MemberVO selectCheckMember(String id);
	
	@Select("SELECT * FROM member m JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_num=#{mem_num}")
	public MemberVO selectMember(Integer mem_num);
	
	@Update("UPDATE member_detail SET name=#{name},phone=#{phone},email=#{email},zipcode=#{zipcode},address=#{address} WHERE mem_num=#{mem_num}")
	public void updateMember(MemberVO member);
	
	@Update("UPDATE member_detail SET passwd=#{passwd} WHERE mem_num=#{mem_num}")
	public void updatePassword(MemberVO member);
	
	@Update("UPDATE member SET auth=0 WHERE mem_num=#{mem_num}")
	public void deleteMember(Integer mem_num);
	
	@Delete("DELETE FROM member_detail WHERE mem_num=#{mem_num}")
	public void deleteMember_detail(Integer mem_num);
}