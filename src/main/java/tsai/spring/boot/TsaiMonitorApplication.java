package tsai.spring.boot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class TsaiMonitorApplication {

    private static final Logger logger = LoggerFactory.getLogger(TsaiMonitorApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TsaiMonitorApplication.class, args);
        Environment environment = context.getEnvironment();
        String port = environment.getProperty("server.port");
        logger.info("====================服务运行环境信息=====================");
        logger.info("服务端口：{}",port);
        logger.info("=======================================================");
    }
}
