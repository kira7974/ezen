package kr.spring.orders.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.boardMarket.vo.BoardMarketVO;
import kr.spring.orders.vo.OrdersVO;

public interface OrdersMapper {
	public List<OrdersVO> selectOrdersList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	//판매글
	public List<OrdersVO> selectOrdersListMember(Map<String, Object> map);
	public int selectRowCountMember(Map<String, Object> map);
	//구매요청글
	public List<OrdersVO> selectOrdersListMemberBuy(Map<String,Object> map);
	public int selectRowCountMemberBuy(Map<String,Object> map);
	
	@Insert("INSERT INTO orders (orders_id, mem_num, valid, market_num) VALUES (order_seq.nextval, #{mem_num}, 1, #{market_num})")
	public void insertOrders(OrdersVO orders);
	@Update("UPDATE orders SET valid=2 WHERE orders_id=#{orders_id}")
	public void updateOrdersValidOne(OrdersVO orders);
	@Update("UPDATE orders SET valid=3 WHERE orders_id=#{orders_id}")
	public void updateOrdersValidTwo(OrdersVO orders);
	@Update("UPDATE orders SET valid=4 WHERE market_num=#{market_num}")
	public void updateOrdersValidThree(OrdersVO orders);
}