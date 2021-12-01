# SpringScheduleAdmin(简称ssa)

[![star](https://gitee.com/plumeorg/spring-schedule-admin/badge/star.svg?theme=dark)](https://gitee.com/frankchenlong/plumelog/stargazers)
[![fork](https://gitee.com/plumeorg/spring-schedule-admin/badge/fork.svg?theme=dark)](https://gitee.com/frankchenlong/plumelog/members)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
![java version](https://img.shields.io/badge/JAVA-1.8+-green.svg)
[![Maven Status](https://maven-badges.herokuapp.com/maven-central/com.plumelog/spring-schedule-admin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.plumelog/spring-schedule-admin)

#### 介绍

SpringScheduleAdmin 是一个用于管理spring自带的Scheduled注解，定时任务的管理插件，能够方便的在项目中管理你的SpringSchedule的定时任务

#### 安装教程

* 第一步：引入依赖到项目中

```xml

<dependency>
    <groupId>com.plumelog</groupId>
    <artifactId>spring-boot-schedule-admin-starter</artifactId>
    <version>1.1</version>
</dependency>
```
* 第二步:启动你的项目

访问你的项目路径后面加上  `ssa/#/`

例如：`http://localhost:8080/ssa/#/` <- 这里的`/#/`不可省略

![avatar](/pic/img.png)
![avatar](/pic/img_1.png)

* 体验地址
  
  http://ssa.plumelog.com/ssa/#/

  注意：com.plumelog.lite.client.AutoDeleteLogs.deleteLogs 这个任务不要修改

  这个体验地址还整合了plumelog-lite,可以用plumelog-lite查看执行情况

  打开 http://ssa.plumelog.com/plumelog/#/console  
  
  应用名称里选择ssa,点击连接按钮查看控制台日志
