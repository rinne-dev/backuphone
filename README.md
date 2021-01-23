# 备用机助手

## 介绍

这是一个能让你闲置的 Android 手机发挥余热的小工具。

## 需求

* Android 6.0+

## 使用

### 1. 发送接口

#### 1.1 Server 酱

打开 [sc.ftqq.com](https://sc.ftqq.com)，登录后即可在 [发送消息](https://sc.ftqq.com/?c=code) 页面得到 `SCKEY`。

#### 1.2 企业微信

1. 注册企业微信

打开 [注册企业微信](https://work.weixin.qq.com/wework_admin/register_wx?from=loginpage)，注册一个企业微信账号。

2. 获取 `Corp ID`

打开 [企业信息](https://work.weixin.qq.com/wework_admin/frame#profile)，下面的企业 ID 即 `Corp ID`。

3. 配置微信插件（可选）

打开 [微信插件](https://work.weixin.qq.com/wework_admin/frame#profile/wxPlugin)，使用个人微信扫码关注。

4. 创建应用

打开 [创建应用](https://work.weixin.qq.com/wework_admin/frame#apps/createApiApp)，上传 Logo 并填写应用名称，可见范围选择自己即可。

5. 获取 `Agent ID` 和 `Secret`

打开 [应用管理](https://work.weixin.qq.com/wework_admin/frame#apps)，在下面的「自建」中找到刚刚创建的应用，点击进入详情，即可得到 `Agent ID` 和 `Secret`。

### 2. 安装配置

安装本工具，进入应用设置，解除后台限制，打开短信权限。

### 3. 使用

打开桌面上的「备用机助手」，设置发送通道，填入对应发送通道的配置参数（第一步得到的），打开全局开关即可。

## 兼容性

以下为经过测试可用的机型及系统版本：

* Google Pixel 2 (Stock Android 11)
* ...

> 理论上没有被厂商魔改过的 Android 关闭后台限制后均可正常使用

## 计划

### 功能

* 短信转发：完成
* 未接来电提醒：计划

### 发送接口

* Server 酱：完成
* 企业微信：完成
* 钉钉：计划
* 飞书：计划

## 参考

* [balasasidhar/android-sms-listener](https://github.com/balasasidhar/android-sms-listener)

## 协议

[MIT License](https://github.com/rinne-dev/backuphone/blob/main/LICENSE)
