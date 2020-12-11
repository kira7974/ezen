package kr.spring.orders.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.boardMarket.vo.BoardMarketVO;
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

	@Override
	public List<OrdersVO> selectOrdersListMember(Map<String, Object> map) {
		return ordersMapper.selectOrdersListMember(map);
	}

	@Override
	public int selectRowCountMember(Map<String, Object> map) {
		return ordersMapper.selectRowCountMember(map);
	}

	@Override
	public List<OrdersVO> selectOrdersListMemberBuy(Map<String, Object> map) {
		return ordersMapper.selectOrdersListMemberBuy(map);
	}

	@Override
	public int selectRowCountMemberBuy(Map<String, Object> map) {
		return ordersMapper.selectRowCountMemberBuy(map);
	}

	@Override
	public void insertOrders(OrdersVO orders) {
		ordersMapper.insertOrders(orders);
	}

	@Override
	public void updateOrdersValidOne(OrdersVO orders) {
		ordersMapper.updateOrdersValidOne(orders);
	}

	@Override
	public void updateOrdersValidTwo(OrdersVO orders) {
		ordersMapper.updateOrdersValidTwo(orders);
	}

}