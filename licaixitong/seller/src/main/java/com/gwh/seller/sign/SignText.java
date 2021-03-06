package com.gwh.seller.sign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gwh.util.JsonUtil;

/**
 * 签名明文接口
 * 为SignAop提供
 * 会使继承该接口的实现类也拥有下面的注解
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //非空
@JsonPropertyOrder(alphabetic = true) //按字典排序
public interface SignText {
    default String toText(){
        //默认实现
        return JsonUtil.toJson(this);
        //返回处理好的明文串
    }
}
