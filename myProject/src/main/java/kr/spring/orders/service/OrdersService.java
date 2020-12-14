package kr.spring.orders.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import kr.spring.boardMarket.vo.BoardMarketVO;
import kr.spring.orders.vo.OrdersVO;

public interface OrdersService {
	public List<OrdersVO> selectOrdersList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	public List<OrdersVO> selectOrdersListMember(Map<String, Object> map);
	public int selectRowCountMember(Map<String, Object> map);
	public List<OrdersVO> selectOrdersListMemberBuy(Map<String,Object> map);
	public int selectRowCountMemberBuy(Map<String,Object> map);
	
	//valid 갱신함수
	public void insertOrders(OrdersVO orders);
	public void updateOrdersValidOne(OrdersVO orders);
	public void updateOrdersValidTwo(OrdersVO orders);
	public void updateOrdersValidThree(OrdersVO orders);
}