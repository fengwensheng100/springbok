<p align="center">
 <img src="https://img.shields.io/badge/Spring%20Boot-2.7-blue.svg" alt="Downloads">
 <img src="https://img.shields.io/badge/Vue-3.4-blue.svg" alt="Downloads">
</p>

## 项目介绍

基于Springboot2.7、Mybatis-Plus、Sa-Token，前端基于Vue3、Element-Plus、Vite的快速开发平台，简洁、灵活、结构清晰，遵循Apache2.0协议，完全开源，可免费用于个人或商业项目，点击上方Star，关注更新

## 分支介绍

| 分支   | 说明     |
| ------ | -------- |
| master | 主分支   |
| dev    | 开发分支 |

## 文档

[《springbok开发文档》](https://www.yuque.com/fengwensheng-ot86q/rm6qf3?#)

## 技术栈

### 后端

| 框架         | 版本    | 描述               |
| ------------ | ------- | ------------------ |
| JDK          | 1.8     | java软件开发工具包 |
| Spring Boot  | 2.7.3   | 应用快速开发框架   |
| Spring MVC   | 5.3.22  | MVC框架            |
| Sa-Token     | 1.37.0  | 轻量级权限认证框架 |
| MySQL        | >=5.7   | 数据库             |
| Mybatis-Plus | 3.4.2   | Mybatis增强工具    |
| Redis        | >=5.0   | Key-Value数据库    |
| Lombok       | 1.18.24 | Java类库           |
| Hutool       | 5.8.3   | Java工具包         |
| Knife4j      | 2.0.8   | Swagger增强        |
| Jackson      | 2.13.3  | JSON工具库         |
| Quartz       | 2.7.3   | 定时任务调度框架   |

### 前端

| 框架                 | 版本    | 描述                    |
| -------------------- | ------- | ----------------------- |
| Vue                  | 3.4.15  | 渐进式JavaScript框架    |
| Vite                 | 5.0.11  | 开发与构建工具          |
| TypeScript           | 5.3.0   | JavaScript类型的超集    |
| Element-Plus         | 2.5.5   | UI组件库                |
| Pinia                | 2.1.7   | Vue状态管理库           |
| Pinia-Plugin-Persist | 1.0.0   | Pinia持久化存储插件     |
| Vue Router           | 4.2.5   | Vue路由                 |
| Axios                | 1.6.7   | 基于Promise的网络请求库 |
| Echarts              | 5.4.3   | 可视化图表库            |
| Dayjs                | 1.11.10 | 日期操作工具库          |
| Wangeditor           | 5.1.23  | 富文本编辑器            |

## 项目结构

```lua
springbok
├── springbok-common -- 通用模块（必须）
├── springbok-core -- 核心模块（必须）
├── springbok-system -- 系统模块（必须）
├── springbok-system-api -- 系统模块api（必须）
├── springbok-quartz -- 定时任务模块（必须）
├── springbok-monitor -- 应用监控服务（可选）
├── springbok-mq -- 消息模块（可选）
├── springbok-erp -- ERP模块（可选）
├── springbok-erp-api -- ERP模块api（可选）
├── springbok-mall -- 商城模块（可选）
├── springbok-mall-api -- 商城模块api（可选）
├── springbok-test -- 示例，仅做示例用（可选）
└── springbok-test-api -- 示例api，仅做示例用（可选）
```

## 项目截图

### 系统管理

#### 基础资料

![菜单管理](https://gitee.com/fengws/springbok/raw/master/image/菜单管理.png)

![角色管理](https://gitee.com/fengws/springbok/raw/master/image/角色管理.png)

![用户管理](https://gitee.com/fengws/springbok/raw/master/image/用户管理.png)

![应用管理](https://gitee.com/fengws/springbok/raw/master/image/应用管理.png)

#### 开发配置

![定时任务](https://gitee.com/fengws/springbok/raw/master/image/定时任务.png)

![系统配置](https://gitee.com/fengws/springbok/raw/master/image/系统配置.png)

![数据库监控](https://gitee.com/fengws/springbok/raw/master/image/数据库监控.png)

![接口文档](https://gitee.com/fengws/springbok/raw/master/image/接口文档.png)

![应用监控](https://gitee.com/fengws/springbok/raw/master/image/应用监控.png)

![字典管理](https://gitee.com/fengws/springbok/raw/master/image/字典管理.png)

![操作日志](https://gitee.com/fengws/springbok/raw/master/image/操作日志.png)

### 商城系统

#### 商品中心

![营销分类](https://gitee.com/fengws/springbok/raw/master/image/营销分类.png)

![规格管理](https://gitee.com/fengws/springbok/raw/master/image/规格管理.png)

![销售商品](https://gitee.com/fengws/springbok/raw/master/image/销售商品.png)

#### 订单中心

![销售订单](https://gitee.com/fengws/springbok/raw/master/image/销售订单.png)

#### 会员中心

![会员管理](https://gitee.com/fengws/springbok/raw/master/image/会员管理.png)

#### 营销中心

![优惠券管理](https://gitee.com/fengws/springbok/raw/master/image/优惠券管理.png)

![专题管理](https://gitee.com/fengws/springbok/raw/master/image/专题管理.png)

![广告管理](https://gitee.com/fengws/springbok/raw/master/image/广告管理.png)

![标签管理](https://gitee.com/fengws/springbok/raw/master/image/标签管理.png)

### ERP

#### 库存中心

![库存查询](https://gitee.com/fengws/springbok/raw/master/image/库存查询.png)

![库存记录](https://gitee.com/fengws/springbok/raw/master/image/库存记录.png)

![盘点订单](https://gitee.com/fengws/springbok/raw/master/image/盘点订单.png)

#### 采购中心

![采购出库单](https://gitee.com/fengws/springbok/raw/master/image/采购出库单.png)

![采购入库单](https://gitee.com/fengws/springbok/raw/master/image/采购入库单.png)

### 其他

#### 暗夜模式

#### 其他主题

#### 开启水印

## 开源协议

**本开源软件遵循 [Apache 2.0 协议](https://www.apache.org/licenses/LICENSE-2.0.html) ，100%开源，可免费作为个人或商业使用**
