package com.cps;
import com.eventbus.SpringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-27 09:45
 */
@SpringBootApplication
@ComponentScan(basePackages = "com")
public class CPSMain {

    public void init() {
        Environment env = SpringUtils.getBean(Environment.class);
        TcpConfig tcpConfig = new TcpConfig();
        tcpConfig.setHost(env.getProperty("localhost"));
        tcpConfig.setPort(Integer.valueOf(env.getProperty("port")));
        tcpConfig.setBossGroup(Integer.valueOf(env.getProperty("bossGroup")));
        tcpConfig.setWorkGroup(Integer.valueOf(env.getProperty("workGroup")));
        tcpConfig.setHandler(new CpsInDoHandler());
        tcpConfig.setAsync(false);
        tcpConfig.setShortLink(false);
        tcpConfig.setCommName("CPS");

        CpsAcceptor cpsAcceptor = new CpsAcceptor(tcpConfig);
        cpsAcceptor.init();
        cpsAcceptor.start();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CPSMain.class, args);
        SpringUtils.setApplicationContext(context);
        SpringUtils.getBean(CPSMain.class).init();
    }
}
