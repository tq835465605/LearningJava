package com.foxhis.spring.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.foxhis.spring.di")
public class JavaConfig {
	
	@Bean(name = "album")
	public Album getAlbumClass()
	{
	     return new Album("Jay Zhou", "夜曲");
	}
	
	@Bean(name = "compatdisc")
	public CompatDisc getCompatDiscClass() {
		return new CompatDisc();
	}

}
