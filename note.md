#windows端口被占用
#查询端口
netstat -ano
#查询指定端口
notStat -ano |findstr "端口号"
#根据进程PID查询进程名称
tasklist |findstr "进程PID号"
#根据PID杀死任务
taskkill /F /PID "进程PID号"
#根据进程名称杀死任务
taskkill -f -t -im "进程名称 "



#临时属性设置
带属性启动SpringBoot
java -jar (首字母加tab键 会自动输入进去 ) springboot.jar --server.port=80
携带多个属性启动springBoot属性间使用空格分隔
临时属性必须是当前boot支持的属性否则无效
#属性加载优先顺序
官网由低到高
#临时属性设置（开发环境）
带属性启动springBoot程序，为程序添加运行属性

1. vm 下面的Configuration下Program arguments:--server.port=80 --logging.level.root=warn
   1. 通过编程的形式带参数启动springboot程序 为程序添加运行参数
      public static void main(String[] args) {
               String[] arg = new String[1];
               arg[0] ="--server.port=8080";
      SpringApplication.run(SSMPApplication.class, arg);
   }
        
   2. 不带参数启动springBoot程序 （安全性）
      public static void main(String[] args) {

           //可以在启动springboot程序时断开读取外部临时配置的对应入口，也就是去掉读取外部参数的形参 args
           SpringApplication.run(SSMPApplication.class);
      }
#配置文件分类
   1级：file : config/application.yml 最高  工程路径config目录中配置文件：服务于运维经理整体调控
   2级：file : application.yml 工程路径配置文件：服务于运维人员配置涉密线上环境
   3级：classpath: config/application.yml 项目类config目录中配置文件：服务于项目经理整体调控
   4级：classpath: application.yml 最低 项目类路径配置文件：服务于开发人员本机开发与测试
   作用：1级与2级留作系统打包后设置通用属性 1级常用于运维经理进行线上整体项目部署方案调控
   3级与4级用于系统开发阶段设置通用属性，3.级常用于项目经理进行整体项目属性调控
   
多层级配置文件的属性采用叠加并覆盖的形式用于程序
#自定义配置文件 config
通过启动参数加载配置文件（无需书写配置文件的扩展名）
--spring.config.name=ebank
通过启动参数加载指定文件路下的配置文件
--spring.config.location=classpath:/ebank.properties
注意 properties与yml文件均支持
通过启动参数加载指定文件路下的配置文件时可以加载多个配置.0，
--spring.config.location=classpath:/ebank.properties，classpath:/ebank-server.properties
注意:多配置文件常用于将配置进行分类，进行独立管理，或者可选配置单独制作便于上线更新维护
#自定义配置文件--重要说明
单服务器项目：使用自定义配置文件需求较低
多服务器项目：使用自定义配置文件需求较高，将所有配置放在一个目录中，统一管理
基于springCloud技术，所有的服务器将不在设置配置文件，而是通过配置中心进行设定，动态加载配置信息
#多环境开发 profiles 
yaml版本
yml中书写顺序 
1.应用环境 2.设置环境 
3.1.生产环境
--- 区分环境
2.开发环境
--- 区分环境设置边界
3.测试环境
#多环境开发多文件版 ymlprofiles 
yaml版
1主启动配置文件application.yml
spring:
profiles:
active: pro
2.环境分类配置文件application-pro.yml 生产环境
server:
port: 81
3.环境分类配置文件application-dev.yml 开发环境
server:
port: 82
4.环境分类配置文件application-test.yml 测试环境
server:
port: 80
注意
主配置文件中设置公共设置（全局）
环境分类配置文件中常用于设置冲突属性（局部）
#多环境开发多配置文件格式  ymlprofiles
properties版
1主启动配置文件application.properties
spring.profiles.active=pro
2.环境分类配置文件application-pro.properties 生产环境
server.port=81
3.环境分类配置文件application-dev.properties 开发环境
server.port=82
4.环境分类配置文件application-test.properties 测试环境
server.port=80
#多环境开发独立配置文件书写（二）
根据功能对配置文件中的信息进行拆分，并制作独立的配置文件，名字规则如下
application-devDB.yml
application-devRedis.yml
application-devMVC.yml
使用include属性在激活指令环境的情况下，同时对多个环境进行加载其生效，多环境间用逗号分割
spring:
 profiles:
   active: dev
   include: devDB,devRedis,devMVC //后配置的覆盖新配置的
注意：当主环境dev与其他环境有相同属性时，主环境属性生效：其他环境中有有相同属性时，最后加载的环境生效
在spring boot2.4版之后开始使用gruop属性替代include属性，降低了配置书写
使用group属性定义多主环境于子环境的包含关系
spring:
    profiles:
    active: dev
    group:
        "dev":devMvc,devDB
        "pro":proMvc,proDB
        "test":testMvc,testDB
#maven与springboot环境兼容
1maven中设置多环境属性
 <!--设置多环境-->
    <profiles>
        <profile>
            <id>env_dev</id>
            <properties>
                <!--具体属性见名知意-->
                <profile.active>dev</profile.active>
            </properties>
        </profile>
        <profile>
            <id>env_pro</id>

            <properties>
                <profile.active>pro</profile.active>
            </properties>
            <!--设定环境-->
            <activation>
                <!--是否默认启动-->
                <activeByDefault>true</activeByDefault>
            </activation>
        </profile>
    </profiles>
2.springboot中引用maven属性他会读取你maven中的值
spring:
    profiles:
        active: @profile.active@
3.执行maven打包指令,并在生成的boot打包文件.jar文件查看对应信息
1.maven为主
2.如果读取maven要手动compile方可生效
#日志
#基础日志
日志（log）作用 
1.编程调试代码 
2.运营其记录信息
    记录日常运维的重要信息（峰值流量，平均响应时长....）
    记录应用报错信息（错误堆栈）
    记录运维过程数据（扩容,宕机，报警...）
3.代码中如何使用日志工具记录日志
//1.创建记录日志的对象
private static final Logger log = LoggerFactory.getLogger(LogController.class);

@GetMapping
    public String getAll(){
        日志级别
        log.trace("运行堆栈trace...级别过低，使用率也低");
        log.debug("给程序员调试代码debug..");
        log.info("记录运维过程数据信息info...");
        log.warn("warn记录运维过程报警数据...");
        log.error("error记录错误堆栈信息...");
log.fatal()//灾难信息合并计入error 没有提供相应的api为什么  error和fatal合并了都叫error  在日志系统中定义为灾难性后果，一般用不到
return "springBoot";
}
    2.记录日志输出级别
    开启debug模式，输出调式信息，常用于检测系统运行状况
    debug: ture
    设置日志级别，root表示根节点 即应用日记级别
    logging:
        level:
            root: debug
            #为队包设置日志
            com.lening.controller: debug
3.设置日志组，控制指定包对应的日志输出级别，也可以直接控制指定包对应的日志输出级别
            #设置分组对某个组设置日记级别
             1.设置分组
        #设置某个包的日志级别
        grop:
            #自定义组名，设置当组中所包含的
            enbak:  com.lening.controller, com.lening.service, com.lening.mapper
            service:com.alibaba
            2.设置分组对某个分组设置日志级配
            ebank: warn
4.优化日志对象创建代码
使用lombok提供的注解@Slf4j简化开发 减少对日志对象的声明操作
但凡一个类要写日志 就加上@slf4j
#日志输出格式控制
时间 yyyy-mm-dd HH:mm:ss  精确到毫秒
级别 INFO...
PID 进程ID，用于表明当前操作所处理的进程，当多服务记录日志时，该值可用于协助程序员调试程序
main 所属的线程
蓝色字体 所属类/接口名 就是位置，当前显示信息为SpringBoot重写后的信息，名称过长时，简化包名书写为首字母，甚至删除
最后的黑色字体 日志信息
一。设置日志输出格式
logging:
    #设置日志的模板格式
    pattern:
        #console: "%d - %m%n"
        #console: "%d %5p - %m%n"
        #console: "%d %clr(%5p) - %m%n"
        #console: "%d %clr(%5p) -----[%16t] %m%n"
        #console: "%d %clr(%5p) -----[%16t] %clr(%-40c.40c){syan} :  %m %n"

%d: 日期
%p: 级别 %5p 代码总宽度为5  
%m: 消息
%n: 换行
%clr: 彩色
%t: 线程名
%c: 简称类名
默认对齐: 右对齐
-40 代表： 左对齐
.40 :内容的截取
{cyan}:设置颜色 青色
#日志文件
logging:
    file:
        name: server.log 
滚动的日志文件
logging:
logback:
rollingpolicy:
  #文件大小 超过他就新建一个文件
max-file-size: 4KB
   #设置文件名
   #server.2020-01-01.0.log
file-name-pattern: service.%d{yyyy-MM-dd}.%i.log
#热部署 
手动启动热部署
启动开发工具
导入 spring-boot-devtools
激活热部署 ctrl +F9
关于热部署
重启（Restart）:自定义开发代码,包括类页面配置文件等，加载位置restart类加载器
重载(Reload): jar,加载位置base类加载器

自动启动热部署
激活方式 idea失去焦点5秒启动
热部署默认不启动范围
/META-INF/maven
/META-INF/resource
/resource
/static
/public
/templates


自定义不参与重启排除项
spring:
devtools:
restart:
 //设置不参与热部署的文件或文件夹
exclude: staric/**,public/**,config/application.yml

设置高优先级属性禁用热部署
public static void main(String[] args) {
System.setProperty("spring.devtools.restart.enabled", "false");
 SpringApplication.run(SSMPApplication.class, args);
}