package com.wms.demo.infrastructure.filter;

import com.wms.demo.infrastructure.utils.ThreadLocalUtil;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Activate(group = CommonConstants.CONSUMER)
public class SetTraceIntoRpcFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = ThreadLocalUtil.getTraceId();
        RpcContext.getContext().setAttachment("traceId", traceId);
        return invoker.invoke(invocation);
    }
}
