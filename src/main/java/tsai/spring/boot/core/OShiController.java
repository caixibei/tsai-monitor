package tsai.spring.boot.core;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tsai.spring.boot.util.OShiUtil;
import java.util.Map;
/**
 * OShi 硬件信息获取接口
 * @author caixibei
 */
@Slf4j
@RestController
@RequestMapping(value = "/oshi")
public class OShiController {

    /**
     * 获取 JVM 信息
     */
    @GetMapping(value = "/getJVMInfo")
    public Map<String, Object> getJVMInfo(){
        Map<String, Object> jvmInfo = OShiUtil.getJvmInfo();
        log.info("=========== JVM::{}",jvmInfo);
        return jvmInfo;
    }

}
