package com.wms.demo.infrastructure.filter;

import com.wms.demo.infrastructure.utils.ThreadLocalUtil;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Activate(group = CommonConstants.PROVIDER)
public class GetTraceFromRpcFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = RpcContext.getContext().getAttachment("traceId");
        ThreadLocalUtil.setTraceId(traceId);
        return invoker.invoke(invocation);
    }
}
