# Jerrymouse
一个web容器服务.类似于Tomcat 9，
预计实现
* Servlet 4.0
* ~~EL 3.0~~
* ~~WebSocket 1.1~~
* ~~JASPIC 1.1~~

并提供了作为 Web 服务器的一些特有功能，
如管理和控制平台、安全域管理和附加组件等。

同时杂糅了一些Spring框架中的依赖注入，面向切面编程等技术
> 注：尽量遵守标准，同时在标准上扩展了些东西，不保证在某些细节上的一致性

# Usage

可参考示例项目（todo）

**预计** 流程如下：
* 启动Jerrymouse容器服务
* 编写代码并打包
* 上传给容器
* 代码执行

# 文档&参考资料

* 在线版的文档大体上在[这里](https://www.yuque.com/czfshine/jerrymouse)
* 同时在doc目录下有文档的镜像备份和原始文件

相关术语基本上和tomcat的意义一样，可参考[tomcat的文档](https://tomcat.apache.org/tomcat-9.0-doc/introduction.html)
规范地址：
* [java servlet 4.0](https://www.jcp.org/en/jsr/detail?id=369)


# 关于JSP
这个项目不想也不会实现JSP，基于以下几点原因：
* 个人认为，将java代码和前端显示的html等融合在一个文件内，一开始就有不好的味道了
* 现在的模板引擎已基本够使用，逻辑复杂的直接使用javascript加载数据显示即可
* ~~编写的工作量大，解析JSP的解析器可能会很大~~

当然，有时间可能会写：）不然编译原理就白学了

# 贡献
* 代码规范基本参照阿里那个java代码规范
* 可从TODO或者issue里找待完成的事项
