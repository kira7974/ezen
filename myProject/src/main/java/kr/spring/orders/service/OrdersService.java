package kr.spring.orders.service;

import java.util.List;
import java.util.Map;

import kr.spring.orders.vo.OrdersVO;

public interface OrdersService {
	public List<OrdersVO> selectOrdersList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	
}