# 甜蜜日记｜甜品电商后端
> 甜蜜日记——基于SpringBoot的甜品店自提预约系统

## 📌项目介绍
甜品外卖平台后端接口服务，提供员工登录、甜品分类管理、订单业务、购物车、预约自提/配送、WebSocket实时消息、业务数据报表等全套接口，供管理后台与微信小程序调用。

## 🛠技术栈
- 核心框架：SpringBoot、SpringMVC
- ORM：MyBatis
- 数据库：MySQL 8.0
- 中间件：Redis、WebSocket
- 工具：Maven、Lombok、Knife4j接口文档
- 安全：JWT令牌登录鉴权，拦截器校验请求

## 📦Maven模块划分
- **sweet‑common**：公共模块
  统一返回封装Result、自定义业务异常、工具类(JWT、Redis、WebSocket)、常量、拦截器、AOP
- **sweet‑pojo**：数据模型模块
  entity数据库实体、DTO接收前端参数、VO返回视图对象
- **sweet‑server**：主启动模块
  Controller控制器、Service业务层、Mapper、定时任务、配置类，项目启动入口

## ✨核心接口业务
1. 员工登录、JWT鉴权拦截
2. 菜品、分类管理，甜品规格维护、上下架
3. 购物车增删改查
4. 订单：下单、预约自提/预约配送、订单状态流转、订单取消
5. WebSocket：来单提醒、客户催单实时推送
6. 定时任务：自动处理超时未支付订单
7. 业务统计：营业额报表、用户统计、订单统计接口
8. 百度地图API，校验配送地址范围

## 📝接口文档
项目启动后访问 Knife4j文档地址
`http://localhost:8080/doc.html`

## 🚀部署启动步骤
1. 创建MySQL数据库，执行项目提供的sql脚本导入表结构与基础数据
2. 修改 `sweet‑server/src/main/resources/application.yml`
   - 修改MySQL数据库连接账号密码
   - 修改Redis连接配置
   - 配置百度地图AK密钥
3. Maven刷新依赖，等待依赖下载完成
4. 运行启动类 `SkyApplication`
5. 默认端口：`8080`
