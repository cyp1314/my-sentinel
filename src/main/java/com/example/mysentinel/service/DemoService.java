package com.example.mysentinel.service;

import cn.binarywang.tools.generator.ChineseNameGenerator;
import cn.binarywang.tools.generator.EmailAddressGenerator;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.mysentinel.entity.User;
import com.example.mysentinel.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DemoService {

    @Autowired
    UserMapper userMapper;

    @Transactional
    @SentinelResource(value = "DemoService#bonjour", defaultFallback = "bonjourFallback")
    public String bonjour(String name) {
        User user = new User();
        user.setName(ChineseNameGenerator.getInstance().generate());
        user.setAge(18);
        user.setEmail(EmailAddressGenerator.getInstance().generate());
        userMapper.insert(user);
        return UUID.randomUUID().toString() +" ,"+name + user.toString();
    }

    public String bonjourFallback(Throwable t) {
        if (BlockException.isBlockException(t)) {
            return "Blocked by Sentinel: " + t.getClass().getSimpleName();
        }
        return "Oops, failed: " + t.getClass().getCanonicalName();
    }
}