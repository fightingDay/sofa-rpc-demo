package com.wish.rpc.demo;

import com.alibaba.fastjson.JSONObject;
import com.alipay.sofa.rpc.api.GenericService;
import com.alipay.sofa.rpc.boot.container.RegistryConfigContainer;
import com.alipay.sofa.rpc.boot.runtime.binding.RpcBindingType;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.wish.plat.common.PlatRequestBody;
import com.wish.plat.common.PlatResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.Map;

/**
 * @ClassName TestBoltRegistry
 * @Description: TODO
 * @Author liguo_rong
 * @Date 2020/5/14
 **/
@RestController
@Consumes("application/json;charset=UTF-8")
@Produces("application/json;charset=UTF-8")
@Slf4j
public class TestBoltRegistry {

    @Autowired
    private RegistryConfigContainer registryConfigContainer;

    @PostMapping("/testBolt/{taskId}")
    public PlatResponseBody testBoltRegistry(@PathVariable("taskId") String taskId, @RequestBody PlatRequestBody flowRequestBody) {
        log.info("Bolt协议泛化调用注册中心开始。。。。");
        String interfaceClass = "com.wish.plat.flow.engine.api.FlowOperationService";
        String bolt = "bolt";
        String methodName = "saveTask";
        String[] prmname = new String[]{String.class.getName(), PlatRequestBody.class.getName()};
        Object[] vlue = new Object[2];
        vlue[0] = taskId;
        vlue[1] = flowRequestBody;
        ConsumerConfig<GenericService> consumerConfig = (ConsumerConfig) ((ConsumerConfig) (new ConsumerConfig()).setConnectTimeout(10000).setDisconnectTimeout(3000).setInterfaceId(interfaceClass)).setProtocol(RpcBindingType.BOLT_BINDING_TYPE.getType()).setGeneric(true).setRegistry(this.registryConfigContainer.getRegistryConfig());
        GenericService genericService = (GenericService) consumerConfig.refer();

        Object genericObjectInvoke = genericService.$genericInvoke(methodName, prmname, vlue, Map.class);
        PlatResponseBody platResponseBody = (PlatResponseBody) genericObjectInvoke;
        log.info("genericObjectInvoke:{}", JSONObject.toJSONString(platResponseBody));
        log.info("Bolt协议泛化调用注册中心结束");
        return platResponseBody;
    }
}
