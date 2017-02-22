## shell执行模块

> `Java语言以其跨平台性和简易性而著称,有些时候为了更加方便的获取系统信息,我们就需要在用shell指令来获取信息,在java程序中如何调用linux的命令？如何调用shell脚本呢？JDK自带的两种方式有通过Runtime.getRuntime().exec()和ProcessBuilder类来做,后者是JDK1.5以后引入的,官方也建议放弃使用Runtime的方式来做.今天在实现的时候就是采用ProcessBuilder,apache commons类库也提供了一个exec包专门做这类功能,在编写过程中，遇到几个比较坑的地方:`

> + `1、构建ProcessBuilder采用的参数：`

> > `建议采用“/bin/bash”. "-c",  "your shell"组装一个List， 其实你一次如果执行多个命令，都可以统一放到那个“your shell”字符串中。`

> + `2、执行过程中输出流控制：`

> + `3、有些命令是需要环境变量的支持， 这时需要执行evivonment()拷贝系统相关env变量到当前进程上下文中，供命令使用。`

> + `4、如果执行过程卡死，需要知道可以kill哪个进程， 所以输出内容中将当前进程ID打印出，便于手动处理`

> `暂无`