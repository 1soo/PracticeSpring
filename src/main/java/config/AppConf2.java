package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import spring.*;

@Configuration
@ComponentScan(basePackages= {"spring"}, excludeFilters=@Filter(type=FilterType.ASPECTJ, pattern="spring.*Dao"))
public class AppConf2 {
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter summaryPrinter() {
		return new MemberSummaryPrinter();
	}
	
}
