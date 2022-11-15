package com.lening;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;

/**
 * @BelongsProject: mybatisplus_02_template
 * @BelongsPackage: com.lening
 * @Author: 马晓锋
 * @CreateTime: 2022-09-14  20:00
 * @Description: 代码生成器的运行类
 * @Version: 1.0
 */
public class Generator {
    public static void main(String[] args) {
        //1获取代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        //设置数据库相关配置
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///heima?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("159357");
        autoGenerator.setDataSource(dataSource);


        //设置全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("C:\\Users\\ALIENWARE\\Desktop\\mybatisPlus");//设置代码生成位置
        globalConfig.setOpen(true);//设置代码生成后是否自动打开生成位置
        globalConfig.setAuthor("马晓峰（乐柠教育）");//设置作者名称
        globalConfig.setFileOverride(true);//设置是否覆盖原始生成文件
        globalConfig.setMapperName("%sMapper");//设置数据层接口名，%s为占位符，指代模块名称
        globalConfig.setIdType(IdType.ASSIGN_ID);//设置id生成策略 （主键返回）
        autoGenerator.setGlobalConfig(globalConfig);

        //设置包名相关的配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.lening");//设置父包名
        packageConfig.setEntity("entity");//设置实体类包名
        packageConfig.setMapper("mapper");//设置数据层接口包名
        autoGenerator.setPackageInfo(packageConfig);

        //策略设置
        StrategyConfig strategyConfig = new StrategyConfig();
        //  strategyConfig.setInclude("t_permethod");//设置参与生成的表名
        strategyConfig.setTablePrefix("t_");//设置数据库表的前缀名称 模块名 = 表名-前缀名称
        strategyConfig.setRestControllerStyle(true);//设置是否为rest风格
        //  strategyConfig.setVersionFieldName("version");//设置乐观锁字段名
        //  strategyConfig.setLogicDeleteFieldName("deleted");//设置逻辑删除字段名
        strategyConfig.setEntityLombokModel(true);//设置是否启用lombok
        autoGenerator.setStrategy(strategyConfig);
        // 2执行生成操作
        autoGenerator.execute();
        System.out.println(System.getProperty("file.encoding"));
    }
}
