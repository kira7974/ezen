package kr.spring.itemPhone.service;

import java.util.List;
import java.util.Map;

import kr.spring.itemPhone.vo.ItemPhoneVO;

public interface ItemPhoneService {
	public List<ItemPhoneVO> selectList(Map<String, Object>map);
	public int selectRowCount(Map<String,Object> map);
	public void insertPhone(ItemPhoneVO phone);
	public ItemPhoneVO selectPhone(Integer phone_num);
	public void updatePhone(ItemPhoneVO phone);
	public void deletePhone(Integer phone_num);
}
