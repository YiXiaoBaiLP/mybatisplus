### MyBatis-Plus 的练习仓库

> 本仓库为MyBatis Plus 的练习仓库  
> 数据库初始化脚本地址：`src/main/resources/sql/untitled.sql`
#### MyBatis Plus 相关配置
``` yaml
mybatis-plus:
  configuration:
    # 控制台打印mybatis-plus生成的SQL语句
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      # 配置实体类所对应的表的统一前缀
      table-prefix: t_
      # 设置统一的主键生成策略
      id-type: auto
  # 配置类型别名
  type-aliases-package: buzz.yixiaobai.pojo
```