package org.conference.system.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/***
 * @Api (tags = " 账户相关模块 ")
 * @ApiModel (" 修改密码所需参数封装类 ")
 * @ApiModelProperty (" 账户Id ")
 * @ApiOperation (value = " 修改密码 ", notes = " 方法的备注说明 ， 如果有可以写在这里 ")
 * @ApiImplicitParams： 用在请求的方法上，包含一组参数说明
 *     @ApiImplicitParam： 对单个参数的说明
 *         name：参数名
 *         value：参数的汉字说明、解释
 *         required：参数是否必须传
 *         paramType：参数放在哪个地方
 *             · header --> 请求参数的获取：@RequestHeader
 *             · query --> 请求参数的获取：@RequestParam
 *             · path（用于restful接口）--> 请求参数的获取：@PathVariable
 *             · body（请求体）-->  @RequestBody User user
 *             · form（普通表单提交）
 *         dataType：参数类型，默认String，其它值dataType="int"
 *         defaultValue：参数的默认值
例如：
 @ApiResponses({
 @ApiResponse(code = 400, message = "请求参数没填好"),
 @ApiResponse(code = 404, message = "请求路径找不到")
 })
 *
 */

@Slf4j
@Configuration
// 开启Swagger : 注：3.0之后 @EnableOpenApi
@EnableSwagger2
@EnableSwaggerBootstrapUI
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class Swagger2Config implements WebMvcConfigurer {
//    public final static String X_ACCESS_TOKEN = "X-Access-Token";

    /**
     * swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     *
     * @return Docket
     */

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 为当前包下controller生成API文档
//                .apis(RequestHandlerSelectors.basePackage("com.conference"))
                // 为有@Api注解的Controller生成API文档
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                // 为有@ApiOperation注解的方法生成API文档
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 为任何接口生成API文档
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
        //添加登录认证
                /*.securitySchemes(securitySchemes())
                .securityContexts(securityContexts());*/
    }

    /**
     * api文档的详细信息函数,注意这里的注解引用的是哪个
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //大标题
                .title("Jeecg-Boot 后台服务API接口文档")
                // 版本号
                .version("1.0")
                .termsOfServiceUrl("NO terms of service")
                // 描述
                .description("后台API接口")
                // 作者
                .contact(new Contact("yanfw", "yanq.top", "840947210@qq.com"))
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }


    /**
     * 全局跨域处理
     */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
                //跨域允许时间
                .maxAge(3600);
    }

    /**
     * 解决swagger被拦截的问题
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        // 解决swagger-bootstrap-ui访问不了
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
 }

    /**
     * 此处是为了方便浏览器只用敲缀,方便访问
     * 本准备使用forward模式，发现forward过去swagger的静态文件访问不了
     * swagger静态文件是按照请求路径加载的，这个后续可以想办法在优化
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger")
                .setViewName("redirect:/swagger-ui/index.html");
    }

    /**
     * 给API文档接口添加安全认证
     */
    /*private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeys;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$")).build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }*/

    /***
     * 3、在shiro配置类中放行swagger2相关资源
     * //swagger2免拦截
     * filterChainDefinitionMap.put("/swagger-ui.html**", "anon");
     * filterChainDefinitionMap.put("/v2/api-docs", "anon");
     * filterChainDefinitionMap.put("/swagger-resources/**", "anon");
     * filterChainDefinitionMap.put("/webjars/**", "anon");
     */

}
