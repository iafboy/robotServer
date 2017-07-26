package io.pivotal.microservices;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import io.pivotal.microservices.common.CommonParams;
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableZuulProxy
@EnableHystrix
@ServletComponentScan( basePackages =  CommonParams.BASEPATH)
@ComponentScan(basePackages = CommonParams.BASEPATH)
public class RobotsrvcServer {
	protected static Logger logger = Logger.getLogger(RobotsrvcServer.class.getName());

	public static void main(String[] args) {
		System.setProperty("spring.mvc.dispatch-options-request","true");
		System.setProperty("javax.net.ssl.trustStore", CommonParams.InternalCertPath);
		SpringApplication.run(RobotsrvcServer.class, args);
	
	}
}
