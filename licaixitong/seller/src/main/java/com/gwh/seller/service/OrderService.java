package com.gwh.seller.service;

import com.gwh.entity.Order;
import com.gwh.entity.enums.OrderStatus;
import com.gwh.entity.enums.OrderType;
import com.gwh.seller.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.UUID;

/**
 * 订单服务
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    /**下单
     * 申购订单
     * @param order
     * @return
     */
    public Order apply(Order order){
        //数据校验
        checkOrder(order);
        //完善订单数据
        completeOrder(order);
        order = orderRepository.saveAndFlush(order);
        return order;
    }

    private void checkOrder(Order order) {
        //必填字段
        Assert.notNull(order.getOuterOrderId(),"需要外部订单编号");
        Assert.notNull(order.getChanId(),"需要渠道编号");
        Assert.notNull(order.getChanUserId(),"需要用户编号");
        Assert.notNull(order.getProductId(),"需要产品编号");
        Assert.notNull(order.getAmount(),"需要购买金额");
        Assert.notNull(order.getCreateAt(),"需要订单时间");

        //产品是否存在及金额是否符合要求
//        Product product = productService.findOne(order.getProductId());
//        Assert.notNull(product,"产品不存在");
        //金额要满足如果有起投金额时，要大于等于起投金额，如果有投资步长时，超过起投金额的部分要是投资步长的整数倍

    }

    /**
     * 完善订单数据
     * @param order
     */
    private void completeOrder(Order order) {
        //随机生成订单id
        order.setOrderId(UUID.randomUUID().toString().replaceAll("-",""));
        order.setOrderType(OrderType.APPLY.name());
        order.setOrderStatus(OrderStatus.SUCCESS.name());
        order.setUpdateAt(new Date());
    }

}
