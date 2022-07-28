package com.wms.demo.provider;

import com.wms.demo.infrastructure.utils.ThreadLocalUtil;
import org.apache.dubbo.config.annotation.DubboService;
import com.wms.demo.DemoService;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DubboService(version = "1.0.0")
public class DemoServiceImpl implements DemoService {
    private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public String sayHello(String name) {
        logger.info("=========================== traceId={}, name={}", ThreadLocalUtil.getTraceId(), name);
        logger.info("=========================== Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "Hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();
    }

}
