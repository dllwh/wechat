package com.cdeledu.shell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cdeledu.shell.build.LinuxShellBuilder;
import com.cdeledu.shell.build.WindowsShellBuilder;

/**
 * @类描述:
 * 
 *       <pre>
 *       java通过ProcessBuilder执行本地shell命令<br>
 *       此类用于创建操作系统进程.
 *       </pre>
 *       <p>
 *       每个进程生成器管理这些进程属性： <br>
 *       (1)命令 是一个字符串列表，它表示要调用的外部程序文件及其参数（如果有） <br>
 *       (2)环境 是从变量 到值的依赖于系统的映射。初始值是当前进程环境的一个副本.<br>
 *       (3)工作目录。默认值是当前进程的当前工作目录，通常根据系统属性 user.dir 来命名.<br>
 *       (4)redirectErrorStream 属性。<br>
 *       若是此属性为 false，<br>
 *       子进程的标准输出和错误输出被发送给两个独立的流， <br>
 *       这些流可以通过Process.getInputStream() 和 Process.getErrorStream() 方法来访问。 <br>
 *       若是此值设置为 true，标准错误将与标准输出合并。<br>
 *       在此情况下，合并的数据可从 Process.getInputStream() 返回的流读取
 *       </pre>
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年12月20日 上午8:12:37
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class Shell {
	/** ----------------------------------------------------- Fields start */
	/** liunx OR windows系统的默认编码 */
	private static final String DEFAULT_ENCODE;
	/** 进程生成器(即ProcessBuilder对象) */
	private static ShellBuilder shellBuilder;
	/** 一条指定的系统命令 ,是一个字符串列表 */
	private List<String> commands;
	/** 环境变量字符串数组，其中每个环境变量的设置格式为name=value； */
	// private List<String> envp;
	/** 当前进程的当前工作目录，通常根据系统属性 user.dir 来命名;如果子进程应该继承当前进程的工作目录,则该参数为null */
	// private File dir;
	/** 进程的标准输出流 */
	// private List<Callback> processCallback = new LinkedList<>();
	/** 进程的错误输出流 */
	// private List<Callback> errorCallback = new LinkedList<>();
	/** ----------------------------------------------------- Fields end */

	static {
		String os = System.getProperty("os.name");
		if ("windows".equals(os.toLowerCase())) {
			DEFAULT_ENCODE = "GBK";
			shellBuilder = new WindowsShellBuilder();
		} else {
			DEFAULT_ENCODE = "UTF-8";
			shellBuilder = new LinuxShellBuilder();
		}

	}

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	public Shell(String command, String... more) {
		commands = new ArrayList<>(Arrays.asList(command));
		commands.addAll(Arrays.asList(more));
	}

	public static Shell build(String command, String... more) {
		return new Shell(command, more);
	}

	public Shell env() {
		return this;
	}

	public Shell onProcess() {
		return this;
	}

	public Shell onError() {
		return this;
	}

	public int exec() throws IOException {
		return 0;
	}
}
