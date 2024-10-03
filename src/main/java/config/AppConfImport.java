package config;

import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import spring.Client;
import spring.Client2;

@Configuration
@EnableAspectJAutoProxy
@Import({AppConf1.class, AppConf2.class})
public class AppConfImport {
	@Bean
	public ExeTimeAspect exeTimeAspect(){
		return new ExeTimeAspect();
	}

	@Bean
	public Calculator calculator(){
		return new RecCalculator();
	}

	/*@Bean
	public Client client() {
		Client client = new Client();
		client.setHost("host");
		return client;
	}
	
	@Bean(initMethod="connect", destroyMethod="close")
	public Client2 client2() {
		Client2 client = new Client2();
		client.setHost("host");
		return client;
	}*/
}
