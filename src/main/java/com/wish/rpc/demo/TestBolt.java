package com.wish.rpc.demo;

import com.alibaba.fastjson.JSONObject;
import com.alipay.sofa.rpc.api.GenericService;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.wish.plat.common.PlatRequestBody;
import com.wish.plat.common.PlatResponseBody;
import com.wish.plat.flow.engine.api.FlowOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.Map;

/**
 * @ClassName TestBolt
 * @Description: TODO
 * @Author liguo_rong
 * @Date 2020/5/13
 **/
@RestController
@Consumes("application/json;charset=UTF-8")
@Produces("application/json;charset=UTF-8")
@Slf4j
public class TestBolt {

    @PostMapping("/testBolt/{taskId}")
    public PlatResponseBody testBolt(@PathVariable("taskId") String taskId, @RequestBody PlatRequestBody flowRequestBody) {
        log.info("Bolt协议泛化调用开始。。。。");
        String interfaceClass = "com.wish.plat.flow.engine.api.FlowOperationService";
        String bolt = "bolt";
//        String directUrl = "172.29.25.110:12177";
        String directUrl = "172.27.255.21";
        String methodName = "saveTask";
        String[] prmname = new String[]{String.class.getName(), PlatRequestBody.class.getName()};
        Object[] vlue = new Object[2];
        vlue[0] = taskId;
        vlue[1] = flowRequestBody;
        ConsumerConfig consumerConfig = ((ConsumerConfig) (new ConsumerConfig()).setInterfaceId(interfaceClass)).setProtocol(bolt).setDirectUrl(directUrl).setGeneric(true).setTimeout(3000).setConnectTimeout(10000);
        GenericService genericService = (GenericService) consumerConfig.refer();

        Object genericObjectInvoke = genericService.$genericInvoke(methodName, prmname, vlue, Map.class);
        PlatResponseBody platResponseBody = (PlatResponseBody) genericObjectInvoke;
        log.info("genericObjectInvoke:{}", JSONObject.toJSONString(platResponseBody));
        log.info("Bolt协议泛化调用结束");
        return platResponseBody;
    }
}