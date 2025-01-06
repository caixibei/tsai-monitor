#  基于 OShi 的硬件信息监控平台

> 声明：该文档多数摘录于 [OSHI官方](https://github.com/oshi/oshi?tab=readme-ov-file#documentation) 站点。
> 本人仅使用官方开源授权功能，并未对源码进行任何二次改造! 🐶🐶🐶

## 概述

OSHI 是一个免费的基于 JNA 的 （本机） Java 操作系统和硬件信息库。

它不需要安装任何其他本机库，旨在提供跨平台实现来检索系统信息，例如操作系统版本、进程、内存和 CPU 使用率、磁盘和分区、设备、传感器等。

- [支持的平台](https://www.oshi.ooo/#supported-platforms)
- [下载和依赖项管理](https://www.oshi.ooo/#downloads-and-dependency-management)
- [文档](https://www.oshi.ooo/#documentation)
- [用法](https://www.oshi.ooo/#usage)
- [支持的功能](https://www.oshi.ooo/#supported-features)
- [支持](https://www.oshi.ooo/#support)
- [OSHI 企业版](https://www.oshi.ooo/#oshi-for-enterprise)
- [安全联系信息](https://www.oshi.ooo/#security-contact-information)
- [持续集成测试状态](https://www.oshi.ooo/#continuous-integration-test-status)
- [我能帮忙吗？](https://www.oshi.ooo/#how-can-i-help)
- [为 OSHI 做出贡献](https://www.oshi.ooo/#contributing-to-oshi)
- [确认](https://www.oshi.ooo/#acknowledgments)
- [许可证](https://www.oshi.ooo/#license)

## 下载和依赖项管理

Stable Release Version 稳定发布版本：

- JDK8：[oshi-core-6.6.5](https://central.sonatype.com/artifact/com.github.oshi/oshi-core/6.6.5)
- JPMS：[oshi-core-java11-6.6.5](https://central.sonatype.com/artifact/com.github.oshi/oshi-core-java11/6.6.5)
- JDK6：[oshi-core-3.14.0](https://central.sonatype.com/artifact/com.github.oshi/oshi-core/3.14.0)

当前开发 （SNAPSHOT） 下载：

- JDK8: [oshi-core-6.6.6-SNAPSHOT](https://oss.sonatype.org/service/local/artifact/maven/redirect?r=snapshots&g=com.github.oshi&a=oshi-core&v=6.6.6-SNAPSHOT&e=jar)
- JPMS：[oshi-core-java11-6.6.6-SNAPSHOT](https://oss.sonatype.org/service/local/artifact/maven/redirect?r=snapshots&g=com.github.oshi&a=oshi-core-java11&v=6.6.6-SNAPSHOT&e=jar)

## 用法

1. 在 Classpath 中包含 OSHI 及其依赖项：

- 我们强烈建议您将 Vault 或 Gradle 等依赖项作为依赖项添加到您的项目依赖项管理器中。
- 对于 Android，您需要为 JNA 添加 AAR 工件，并排除 OSHI 的传递 （JAR） 依赖项。
- 如果您遇到或问题，请参阅 [FAQ](https://github.com/oshi/oshi/blob/master/src/site/markdown/FAQ.md#how-do-i-resolve-jna-noclassdeffounderror-or-nosuchmethoderror-issues)。

2. 创建 SystemInfo 的新实例



3. 使用 getter from 访问硬件或操作系统组件，例如：SystemInfo

```java
SystemInfo si = new SystemInfo();
HardwareAbstractionLayer hal = si.getHardware();
CentralProcessor cpu = hal.getProcessor();
```

有关示例，请参阅 [SystemInfoTest.java](https://github.com/oshi/oshi/blob/master/oshi-core/src/test/java/oshi/SystemInfoTest.java)。要查看计算机的示例输出，请执行以下操作：

```shell
git clone https://github.com/oshi/oshi.git && cd oshi

./mvnw test-compile -pl oshi-core exec:java \
  -Dexec.mainClass="oshi.SystemInfoTest" \
  -Dexec.classpathScope="test"
```

某些设置可以在 oshi.properties 文件中进行配置，也可以使用 GlobalConfig 类进行操作。这应该在启动时完成，因为配置不是线程安全的，并且 OSHI 不保证在操作期间重新读取配置。

该工件包括几个使用 OSHI 获取信息的[概念验证示例](https://github.com/oshi/oshi/blob/master/oshi-demo/src/main/java/oshi/demo/)，包括基本的 Swing GUI。

## 支持的功能

- 计算机系统和固件、底板
- 操作系统和版本/内部版本
- 物理（核心）和逻辑（超线程）CPU、处理器组、NUMA 节点
- 系统和每个处理器的负载、使用情况滴答计数器、中断、正常运行时间
- 进程正常运行时间、CPU、内存使用情况、用户/组、命令行参数、线程详细信息
- 已使用/可用的物理内存和虚拟内存
- 挂载的文件系统（类型、可用和总空间、选项、读取和写入）
- 磁盘驱动器（型号、序列号、大小、读取和写入）和分区
- 网络接口（IP、带宽输入/输出）、网络参数、TCP/UDP 统计信息
- 电池状态（% 容量、剩余时间、电源使用统计数据）
- 连接的显示器（带有 EDID 信息）、图形卡和音频卡
- USB 设备
- 某些硬件上的传感器（温度、风扇速度、电压）
- 连接的显示器（带有 EDID 信息）、图形卡和音频卡

## 支持

- 有关错误报告、功能请求或有关 OSHI 长期计划的一般问题，请[创建一个问题](https://github.com/oshi/oshi/issues)。
- 如需帮助将 OSHI 集成到您自己的项目中，或查看维护者对您的 PR 进行代码审查，请在您的项目站点上标记问题或拉取请求。
- 有关使用 API 的 “操作方法” 问题，请查阅项目中的示例，创建问题，或使用标签在[Stack Overflow 上搜索](https://stackoverflow.com/search?q=%5Boshi%5D+is%3Aquestion)，如果之前没有回答过，请提出新问题。
- 要感谢 OSHI 的主要维护者，您可以[赞助他](https://github.com/sponsors/dbwiddis)或[请他喝杯咖啡](https://www.buymeacoffee.com/dbwiddis)。

## 许可证

本项目根据 MIT 许可证获得许可。

## 库内封装的可查看的硬件信息

- 操作系统
- 处理器
- 内存
- 进程
- 系统服务
- 传感器
- 电池信息（注意是电池，不是电源）
- 磁盘存储
- 逻辑卷组（Windows平台读取不到信息）
- 文件系统
- 网络接口列表
- 网络参数
- IP统计信息
- 显示器列表
- USB设备列表（树）
- 声卡列表
- 显卡列表

## 样式演示

- 手机端

- 中小屏幕（平板）

