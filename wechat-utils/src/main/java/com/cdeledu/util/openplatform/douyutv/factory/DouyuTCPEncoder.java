package com.cdeledu.util.openplatform.douyutv.factory;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import com.cdeledu.util.openplatform.douyutv.constants.DouYuConstants;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 斗鱼弹幕协议信息封装类(弹幕协议序列化与反序列化类)
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年8月6日 下午8:41:38
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class DouyuTCPEncoder {
	/** ----------------------------------------------------- Fields start */
	private StringBuffer buf = new StringBuffer();

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法描述 : 根据斗鱼弹幕协议进行相应的编码处理
	 * @param key
	 * @param value
	 */
	public void addItem(String key, Object value) {
		buf.append(key.replaceAll("/", "@S").replaceAll("@", "@A"));
		buf.append("@=");
		if (value instanceof String) {
			buf.append(((String) value).replaceAll("/", "@S").replaceAll("@", "@A"));
		} else if (value instanceof Integer) {
			buf.append(value);
		}
		buf.append("/");
	}

	/**
	 * @方法描述 : 返回弹幕协议序列化后的结果
	 * @return
	 */
	public String getResult() {
		// 数据包末尾必须以'\0'结尾
		buf.append('\0');
		return buf.toString();
	}

	/**
	 * @方法: 以小端模式将int转成byte[]
	 * @创建人:独泪了无痕
	 * @param value
	 * @return
	 */
	private static byte[] intToBytesLittle(int value) {
		byte[] b = new byte[4];
		b[0] = (byte) (value & 0xff);
		b[1] = (byte) (value >> 8 & 0xff);
		b[2] = (byte) (value >> 16 & 0xff);
		b[3] = (byte) (value >> 24 & 0xff);
		return b;
	}

	/**
	 * @方法描述 : 将数据转换为小端整数格式(字节数组)
	 * @param data
	 * @return
	 */
	public byte[] getByte(String data) {
		ByteArrayOutputStream boutput = new ByteArrayOutputStream();
		DataOutputStream doutput = new DataOutputStream(boutput);
		try {
			boutput.reset();
			// 消息长度：4 字节小端整数，表示整条消息（包括自身）长度（字节数）
			doutput.write(intToBytesLittle(data.length() + 8), 0, 4);
			// 消息长度：4 字节小端整数，表示整条消息（包括自身）长度（字节数）
			doutput.write(intToBytesLittle(data.length() + 8), 0, 4);
			// 消息类型：2 字节小端整数
			doutput.write(intToBytesLittle(DouYuConstants.MESSAGE_TYPE_CLIENT), 0, 2);
			// 加密字段：暂时未用，默认为 0
			doutput.writeByte(0);
			// 保留字段：暂时未用，默认为 0
			doutput.writeByte(0);
			// 写入消息内容（所有协议内容均为 UTF-8 编码）
			doutput.writeBytes(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boutput.toByteArray();
	}
}