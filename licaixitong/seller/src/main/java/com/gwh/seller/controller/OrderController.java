package com.gwh.seller.controller;

import com.gwh.entity.Order;
import com.gwh.seller.params.OrderParam;
import com.gwh.seller.service.OrderService;
//import com.imooc.seller.params.OrderParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单相关
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    static Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;


//    @RequestMapping(value = "/apply", method = RequestMethod.POST)
//    public Order apply(@RequestBody Order order) {
//        order = orderService.apply(order);
//        return order;
//    }
    /**
     * 下单
     * 强制要求在请求头中要设置authId和sign属性
     *从请求头中获取 authId，sign
     * @param param
     * @return
     */
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public Order apply(@RequestHeader String authId, @RequestHeader String sign, @RequestBody OrderParam param) {
        LOG.info("申购请求:{}", param);
        //创建一个订单对象
        Order order = new Order();
        //将参数param值赋值给订单对象order
        BeanUtils.copyProperties(param,order);
        order = orderService.apply(order);
        LOG.info("申购结果:{}", order);
        return order;
    }
}
