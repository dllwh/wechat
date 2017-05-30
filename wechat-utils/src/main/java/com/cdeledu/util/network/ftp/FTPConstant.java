package com.cdeledu.util.network.ftp;

import java.util.HashMap;
import java.util.Map;

/**
 * @类描述: FTP相关的一些配置
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年5月30日 下午9:16:01
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class FTPConstant {
	/** ----------------------------------------------------- Fields start */
	/** FTP状态码及其描述 */
	public static Map<Integer, String> replycode = new HashMap<Integer, String>();

	/** ----------------------------------------------------- Fields end */

	static {
		replycode.put(120, "服务已就绪，在 nnn 分钟后开始。");
		replycode.put(125, "数据连接已打开，正在开始传输。");
		replycode.put(150, "文件状态正常，准备打开数据连接。");
		replycode.put(202, "未执行命令，站点上的命令过多。");
		replycode.put(211, "系统状态，或系统帮助答复。");
		replycode.put(212, "目录状态。");
		replycode.put(213, "文件状态。");
		replycode.put(214, "帮助消息。");
		replycode.put(215, "NAME 系统类型，其中，NAME 是 Assigned Numbers 文档中所列的正式系统名称。");
		replycode.put(220, "服务就绪，可以执行新用户的请求。");
		replycode.put(221, "服务关闭控制连接。如果适当，请注销。");
		replycode.put(225, "数据连接打开，没有进行中的传输。");
		replycode.put(226, "关闭数据连接。请求的文件操作已成功（例如，传输文件或放弃文件）。");
		replycode.put(227, "进入被动模式 (h1,h2,h3,h4,p1,p2)。");
		replycode.put(230, "用户已登录，继续进行。");
		replycode.put(250, "请求的文件操作正确，已完成。");
		replycode.put(257, "已创建“PATHNAME”。");
		replycode.put(332, "需要登录帐户。");
		replycode.put(350, "请求的文件操作正在等待进一步的信息。");
		replycode.put(425, "无法打开数据连接。");
		replycode.put(426, "Connection closed; transfer aborted.");
		replycode.put(450, "未执行请求的文件操作。文件不可用（例如，文件繁忙）。");
		replycode.put(451, "请求的操作异常终止：正在处理本地错误。");
		replycode.put(452, "未执行请求的操作。系统存储空间不够。");
		replycode.put(501, "在参数中有语法错误。");
		replycode.put(502, "未执行命令。");
		replycode.put(503, "错误的命令序列。");
		replycode.put(504, "未执行该参数的命令。");
		replycode.put(530, "未登录。");
		replycode.put(532, "存储文件需要帐户。");
		replycode.put(550, "未执行请求的操作。文件不可用（例如，未找到文件，没有访问权限）。");
		replycode.put(551, "请求的操作异常终止：未知的页面类型。");
		replycode.put(552, "请求的文件操作异常终止：超出存储分配（对于当前目录或数据集）。");
		replycode.put(553, "未执行请求的操作。不允许的文件名。");
	}
}
