package com.agm.issuemanagement;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IssueManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueManagementApplication.class, args);
	}

	@Bean //her yerde erişilip kullanılabilsin, aynı instance üzerinden kullanılsın
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
