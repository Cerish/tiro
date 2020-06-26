package cn.cerish.config;

import io.swagger.annotations.ApiImplicitParams;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    // 配置了 swagger 的bean实例
    @Bean
    public Docket auth() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("Auth 接口文档"))
                .groupName("auth")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.cerish.controller.auth"))
                .build();
    }
    @Bean
    public Docket collection() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("Collection 接口文档"))
                .groupName("collection")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.cerish.controller.collection"))
                .build()
                .globalOperationParameters(globalOperation());
    }
    private List<Parameter> globalOperation(){
        //添加head参数配置start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        //第一个token为传参的key，第二个token为swagger页面显示的值
        tokenPar.name("Authorzation").description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        return pars;
    }

    // api 信息
    public ApiInfo apiInfo(String title) {
        // Contact contact = new Contact("cerish", "https://www.baidu.com", "731523780@qq.com");
        return new ApiInfo(
                title,
                "",
                "v1.0",
                "",
                new Contact("","",""),
                "",
                "",
                new ArrayList()
        );
    }
}
