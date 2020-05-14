package com.wish.rpc.demo.invoke;

import com.alibaba.fastjson.JSON;
import com.wish.plat.common.PlatRequestBody;
import com.wish.plat.common.PlatResponseBody;
import com.wish.plat.flow.engine.api.FlowOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

/**
 * @ClassName TestBolt2
 * @Description: TODO
 * @Author liguo_rong
 * @Date 2020/5/14
 **/
@RestController
@Consumes("application/json;charset=UTF-8")
@Produces("application/json;charset=UTF-8")
@Slf4j
public class TestBolt2 {

    @Autowired
    private FlowOperationService flowOperationService;

    @PostMapping("/saveTask/{taskId}")
    public PlatResponseBody testBoltSaveTask(@PathVariable("taskId") String taskId, @RequestBody PlatRequestBody flowRequestBody) {
        log.info("Bolt协议调用");
        PlatResponseBody platResponseBody = flowOperationService.saveTask(taskId, flowRequestBody);
        log.info("platResponseBody:{}", JSON.toJSONString(platResponseBody));
        return platResponseBody;
    }
}
