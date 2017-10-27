package com.juqitech.service.interfaces;

import com.juqitech.service.utils.net.CommonResult;
import com.juqitech.service.utils.net.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
@Api(value = "测试服务", description = "测试服务", tags = {"test service"})
public class TestServiceController {



    @RequestMapping(value = "/test/service", method = RequestMethod.GET)
    @ApiOperation(value = "测试接口", notes = "测试接口备注")
    public CommonResult testService(
            @ApiParam(value = "测试参数",allowableValues = "0,1,2,3,4,5,6,7,8,9") @RequestParam(required = false,defaultValue = "-1") String showStatus
            ) {
        CommonResult commonResult = new CommonResult();
        commonResult.setStatusCode(StatusCode.success);
        return commonResult;
    }

}
