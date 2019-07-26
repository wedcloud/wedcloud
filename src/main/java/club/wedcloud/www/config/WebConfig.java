package club.wedcloud.www.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins("http://www.wedcloud.club:39999", "*")
        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE").maxAge(3600)
        .allowCredentials(true);
  }

  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    // TODO Auto-generated method stub

  }

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    // TODO Auto-generated method stub

  }

  @Override
  public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    // TODO Auto-generated method stub

  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    // TODO Auto-generated method stub

  }

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
    // TODO Auto-generated method stub
  }

  /**
   * 消息转换器
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    // TODO Auto-generated method stub
    FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
    FastJsonConfig config = new FastJsonConfig();
    config.setDateFormat("yyyy-MM-dd");
    config.setCharset(Charset.forName("UTF-8"));
    // 修改配置 将返回内容过滤
    config.setSerializerFeatures(SerializerFeature.WriteMapNullValue,
        SerializerFeature.PrettyFormat, SerializerFeature.WriteNullListAsEmpty,
        SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero);
    converter.setFastJsonConfig(config);
    List<MediaType> supportedMediaTypes = new ArrayList<>();
    supportedMediaTypes.add(MediaType.APPLICATION_JSON);
    supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
    supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
    supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
    supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
    supportedMediaTypes.add(MediaType.APPLICATION_PDF);
    supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
    supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
    supportedMediaTypes.add(MediaType.APPLICATION_XML);
    supportedMediaTypes.add(MediaType.IMAGE_GIF);
    supportedMediaTypes.add(MediaType.IMAGE_JPEG);
    supportedMediaTypes.add(MediaType.IMAGE_PNG);
    supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
    supportedMediaTypes.add(MediaType.TEXT_HTML);
    supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
    supportedMediaTypes.add(MediaType.TEXT_PLAIN);
    supportedMediaTypes.add(MediaType.TEXT_XML);
    converter.setSupportedMediaTypes(supportedMediaTypes);
    converters.add(converter);
  }

  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    // TODO Auto-generated method stub

  }

  @Override
  public void configureHandlerExceptionResolvers(
      List<HandlerExceptionResolver> exceptionResolvers) {
    // TODO Auto-generated method stub

  }

  @Override
  public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
    // TODO Auto-generated method stub

  }

  @Override
  public Validator getValidator() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MessageCodesResolver getMessageCodesResolver() {
    // TODO Auto-generated method stub
    return null;
  }

}
