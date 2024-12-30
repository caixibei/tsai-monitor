package tsai.spring.boot.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tsai.spring.boot.util.OShiUtil;

import java.util.Map;

/**
 * OShi 硬件信息获取接口
 *
 * @author caixibei
 */
@Slf4j
@RestController
@RequestMapping(value = "/oshi")
public class OShiController {

    /**
     * 获取 JVM 信息
     * @return {@link Map}
     */
    @GetMapping(value = "/getJVMInfo")
    public Map<String, Object> getJVMInfo() {
        Map<String, Object> jvmInfo = OShiUtil.getJvmInfo();
        log.info("===========> jvm information::{}", jvmInfo);
        return jvmInfo;
    }

    /**
     * 获取系统进程信息
     * @return {@link Map}
     */
    @GetMapping(value = "/getProcessesInfo")
    public Map<String, Object> getProcessesInfo() {
        Map<String, Object> processInfo = OShiUtil.getProcessesInfo();
        log.info("===========> process information::{}", processInfo);
        return processInfo;
    }

    @GetMapping(value = "/getSystemInfo")
    public Map<String,Object> getSystemInfo(){
        Map<String, Object> systemInfo = OShiUtil.getSystemInfo();
        log.info("===========> process information::{}", systemInfo);
        return systemInfo;
    }

}
