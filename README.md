# SupplyManager

供销管理系统

springboot 2.1.3 搭建，数据库MySQL, ORM hibernate, Thymeleaf + bootstrap 构建前端页面。

---

## 构建
clone 到本地，导入IDEA。本项目的物理表由hibernate自动生成，只需配置好数据库。

项目包结构：
- config&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一些springboot 或 spring security的配置类
- controller&nbsp;&nbsp;&nbsp;MVC C层
- dao&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;spring data jpa接口
- domain&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数据库实体类
- implement&nbsp;&nbsp;服务接口的实现类
- service&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对DAO层包装的一层服务接口
- Utils&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工具类

---

### 配置数据库 
 ```sql
mysql> create database db_example; -- db_example可为任意名字
mysql> create user 'root'@'%' identified by '12345'; -- 创建 root 用户 ,密码: 12345
mysql> grant all on db_example.* to 'root'@'%'; -- 赋予所有权限
 ```
mysql建好数据库后，确保打开mysql服务

---

### 配置工程

前往路径： src/main/resources/application.properties
修改application.properties文件配置工程：
```java
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/db_example?useUnicode=true&characterEncoding=utf-8
spring.datasource.username=root
spring.datasource.password=12345
```
`?useUnicode=true&characterEncoding=utf-8`解决中文查询问题。
`spring.jpa.hibernate.ddl-auto=update` 

ddl-auto 可为  `none`即不操作数据库, `create`每次编译都会删除所有表，重新建表, `update`每次编译不会重新建表，会根据定义的表操作数据库。

第一次运行前更改ddl-auto为create，以便hibernate建表，之后更改为update。每次修改实体都需要这样做一次。

如有问题可以参考此文： https://spring.io/guides/gs/accessing-data-mysql/

---

### 初始化数据库
第一次编译运行后，打开mysql终端，执行sql脚本，
在本项目的路径：`/src/main/resources/sp.sql`

脚本中有权限角色，和管理员密码。
根目录有实体关系表： `relation.xlsx`

商品对应的图片在`/src/main/resources/static/images/goods`下，必须为`商品编号.jpg`格式。

---

## 运行

springboot生成的jar集成了Tomcat服务，所以部署项目时只要配置好数据库，直接运行jar包即可。
