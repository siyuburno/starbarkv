package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

@RequestMapping
@Controller
public class KeyPairController {
    @Autowired
    private KeyPair keyPair;

    @GetMapping("/rsa/publicKey")
    @ResponseBody
    public RSAPublicKey getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        return publicKey;
    }

    @GetMapping("/user/test")
    @ResponseBody
    public String test() {
        return "hello";
    }

//    作者：MacroZheng
//    链接：https://juejin.cn/post/6850037263707930631
//    来源：稀土掘金
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

}
