package kr.spring.orders.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.orders.dao.OrdersMapper;
import kr.spring.orders.vo.OrdersVO;

@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
	
	@Resource
	OrdersMapper ordersMapper;

	@Override
	public List<OrdersVO> selectOrdersList(Map<String, Object> map) {
		return ordersMapper.selectOrdersList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return ordersMapper.selectRowCount(map);
	}

}