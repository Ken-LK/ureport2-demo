package com.bestway.ureport;

import com.bstek.ureport.console.UReportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:context.xml")
public class Ureport2DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ureport2DemoApplication.class, args);
    }


    @Bean
    public ServletRegistrationBean ureport2Servlet() {
        return new ServletRegistrationBean(new UReportServlet(), "/ureport/*");
    }


}
