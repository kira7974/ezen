package kr.spring.itemPhone.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.itemPhone.vo.ItemPhoneVO;

public interface ItemPhoneMapper {
	public List<ItemPhoneVO> selectList(Map<String, Object>map);
	@Select("SELECT COUNT(*) FROM item_phone p JOIN spmember m ON p.mem_num = m.mem_num")
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO item_phone (phone_num, phone_name, phone_date, phone_apu, phone_ram, phone_memory, phone_display, phone_company, phone_os, phone_type, phone_titleimg, phone_titleimgname, phone_content, phone_contentimg, phone_contentimgname, mem_num) VALUES (phone_seq.nextval, #{phone_name}, #{phone_date}, #{phone_apu}, #{phone_ram}, #{phone_memory}, #{phone_display}, #{phone_company}, #{phone_os}, #{phone_type}, #{phone_titleimg}, #{phone_titleimgname}, #{phone_content}, #{phone_contentimg}, #{phone_contentimgname}, #{mem_num})")
	public void insertPhone(ItemPhoneVO phone);
	@Select("SELECT * FROM item_phone p JOIN spmember m ON p.mem_num=m.mem_num WHERE p.phone_num=#{phone_num}")
	public ItemPhoneVO selectPhone(Integer phone_num);
	public void updatePhone(ItemPhoneVO phone);
	@Delete("DELETE FROM item_phone WHERE phone_num=#{phone_num}")
	public void deletePhone(Integer phone_num);
}
