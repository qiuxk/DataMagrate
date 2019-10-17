#1 SpringBoot读取配置文件
   配置文件映射到yml文件中属性名，@Configuration @Component（组件）
   @ConfigurationProperties(prefix = "system") prefix配置yml文件到前缀名 或者system.xxx.xxx类似的
   在需要使用配置文件时，需要添加@EnableConfigurationProperties（类名），直接引用使用即可。
   


