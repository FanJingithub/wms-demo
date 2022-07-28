package com.wms.demo.consumer;

import com.aliyun.mse20190531.models.GetNacosConfigRequest;
import com.aliyun.mse20190531.models.GetNacosConfigResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;

public class NacosConfigTest {
    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.mse20190531.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "mse.cn-hangzhou.aliyuncs.com";
        return new com.aliyun.mse20190531.Client(config);
    }

//    public static void main(String[] args_) throws Exception {
//        java.util.List<String> args = java.util.Arrays.asList(args_);
//        com.aliyun.mse20190531.Client client = NacosConfigTest.createClient("", "");
//        GetNacosConfigRequest getNacosConfigRequest = new GetNacosConfigRequest()
//                .setInstanceId("mse-cn-2r42svi8a12")
//                .setDataId("wms.config.demo")
//                .setGroup("wms");
//        RuntimeOptions runtime = new RuntimeOptions();
//        try {
//            // 复制代码运行请自行打印 API 的返回值
//            GetNacosConfigResponse nacosConfigWithOptions = client.getNacosConfigWithOptions(getNacosConfigRequest, runtime);
//            System.out.println(nacosConfigWithOptions.getBody().getConfiguration().getContent());
//        } catch (TeaException error) {
//            // 如有需要，请打印 error
//            com.aliyun.teautil.Common.assertAsString(error.message);
//            error.printStackTrace();
//        } catch (Exception _error) {
//            TeaException error = new TeaException(_error.getMessage(), _error);
//            // 如有需要，请打印 error
//            com.aliyun.teautil.Common.assertAsString(error.message);
//            _error.printStackTrace();
//        }
//    }
}
