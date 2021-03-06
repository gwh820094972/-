package com.gwh.seller.sign;

import com.gwh.seller.service.SignService;
import com.gwh.util.RSAUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 验签aop
 */
@Component
@Aspect//切面
public class SignAop {

    @Autowired
    private SignService signService;

    /*
      公钥验签
       authId 授权编号  用于获取对应的公钥
       sign 签名结果
       SignText text  返回的处理好的明文串
       "execution(* com.gwh.seller.controller.*.*(..)) && args(authId,sign,text,..)"
       会拦截到com.gwh.seller.controller下的所有类的所有参数以authId,sign,text,..开头的方法。
       并让这些方法做验签操作，只有明文与加密后的sign符合才能验签成功，操作成功
     */
    @Before(value = "execution(* com.gwh.seller.controller.*.*(..)) && args(authId,sign,text,..)")
    public void verify(String authId, String sign, SignText text){
        //根据authID获取公钥
        String publicKey = signService.getPublicKey(authId);
        //验证签名
        Assert.isTrue(RSAUtil.verify(text.toText(),sign,publicKey),"验签失败");
    }
}
