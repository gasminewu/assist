package me.wll.common.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @desc Swagger配置类，该类里面的应该是固定的，主要用来设置文档的主题信息，比如文档的大标题，副标题，公司名 等
 */
@Configuration // 托管spring
@EnableSwagger2 // 开启swagger功能
public class SwaggerConfig {

	/** 是否开启swagger */
	@Value("${swagger.enabled}")
	private boolean enabled;

	/** 设置请求的统一前缀 */
	@Value("${swagger.pathMapping}")
	private String pathMapping;

	/**
	 * 创建API
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				// 是否启用Swagger
				.enable(enabled)
				// 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
				.apiInfo(apiInfo())
				// 设置哪些接口暴露给Swagger展示
				.select()
				// 扫描所有有注解的api，用这种方式更灵活
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				// 扫描指定包中的swagger注解
				// .apis(RequestHandlerSelectors.basePackage("com.xxx.project.tool.swagger"))
				// 扫描所有 .apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build()
				/* 设置安全模式，swagger可以设置访问token */
				.securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .pathMapping(pathMapping).ignoredParameterTypes(HttpSession.class);
	}

	/**
	 * 安全模式，这里指定token通过Authorization头请求头传递
	 */
	private List<SecurityScheme> securitySchemes() {
		List<SecurityScheme> apiKeyList = new ArrayList<SecurityScheme>();
		apiKeyList.add(new ApiKey("Authorization", "Authorization", In.HEADER.toValue()));
		return apiKeyList;
	}
	 /**
     * 	安全上下文
     */
    private List<SecurityContext> securityContexts()
    {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
//                        .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                        .build());
        return securityContexts;
    }

	/**
	 * 默认的安全上引用
	 */
	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		List<SecurityReference> securityReferences = new ArrayList<>();
		securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
		return securityReferences;
	}


//	@Value("${token.name}")
//	private String tokenName;
//	@Value("${token.description}")
//	private String tokenDescription;

	/**
	 * 创建API应用 apiInfo() 增加API相关信息
	 * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
	 * 本例采用指定扫描的包路径来定义指定要建立API的目录。
	 *
	 * @return
	 */
//	@Bean
//	public Docket createRestApi() {
//		Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
//				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any())
//				.build();
//		if (StringUtils.isNotBlank(tokenName)) {
//			tokenDescription = StringUtils.isBlank(tokenDescription) ? "user ticket" : tokenDescription;
//			ParameterBuilder ticketPar = new ParameterBuilder();
//			List<Parameter> pars = new ArrayList<Parameter>();
//			ticketPar.name(tokenName).description(tokenDescription)// Token 以及Authorization
//																	// 为自定义的参数，session保存的名字是哪个就可以写成那个
//					.modelRef(new ModelRef("string")).parameterType("header").required(false).build(); // header中的ticket参数非必填，传空也可以
//			pars.add(ticketPar.build()); // 根据每个方法名也知道当前方法在设置什么参数
//
//			// 版本类型是swagger2
//			docket = docket.globalOperationParameters(pars);
//		}
//		return docket;
//	}

	/**
	 * 创建该API的基本信息（这些基本信息会展现在文档页面中） 访问地址：http://项目实际地址/swagger-ui.html
	 * 
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("使用Swagger2 构建RESTful APIS -cost") // 接口管理文档首页显示
				.description("cost - Swagger使用演示")// API的描述
				.termsOfServiceUrl("www.wisoft.com.cn")// 网站url等
				.version("0.0.1").build();
	}
}
