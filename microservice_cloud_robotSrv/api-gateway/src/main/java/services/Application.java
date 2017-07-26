package services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@SpringBootApplication
@EnableSidecar
public class Application {
	private static final String InternalCertPath="/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/security/jssecacerts";
	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.trustStore", InternalCertPath);
		SpringApplication.run(Application.class, args);
	}
}
