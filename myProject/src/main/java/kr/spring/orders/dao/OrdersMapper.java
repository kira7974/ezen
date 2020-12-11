package kr.spring.orders.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.boardMarket.vo.BoardMarketVO;
import kr.spring.orders.vo.OrdersVO;

public interface OrdersMapper {
	public List<OrdersVO> selectOrdersList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);

}