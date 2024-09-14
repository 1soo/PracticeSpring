package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.*;

@Configuration
@ComponentScan(basePackages= {"spring"})
public class AppConf2 {
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter summaryPrinter() {
		return new MemberSummaryPrinter();
	}
}
