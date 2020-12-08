package kr.spring.itemPhone.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.itemPhone.dao.ItemPhoneMapper;
import kr.spring.itemPhone.vo.ItemPhoneVO;

@Service("phoneService")
public class ItemPhoneServiceImpl implements ItemPhoneService {
	
	@Resource
	ItemPhoneMapper phoneMapper;

	@Override
	public List<ItemPhoneVO> selectList(Map<String, Object> map) {
		return phoneMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return phoneMapper.selectRowCount(map);
	}

	@Override
	public void insertPhone(ItemPhoneVO phone) {
		phoneMapper.insertPhone(phone);		
	}

	@Override
	public ItemPhoneVO selectPhone(Integer phone_num) {
		return phoneMapper.selectPhone(phone_num);
	}

	@Override
	public void updatePhone(ItemPhoneVO phone) {
		phoneMapper.updatePhone(phone);		
	}

	@Override
	public void deletePhone(Integer phone_num) {
		phoneMapper.deletePhone(phone_num);		
	}

}
