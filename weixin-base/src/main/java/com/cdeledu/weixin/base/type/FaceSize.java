package com.cdeledu.weixin.base.type;

/**
 * @ClassName: FaceSize
 * @Description: 头像大小
 * @author: 独泪了无痕
 * @date: 2015-10-13 下午10:57:19
 * @version: V1.0
 */
public enum FaceSize {
	/**
	 * 46x46
	 */
	small(46),
	/**
	 * 64x64
	 */
	middle1(64),
	/**
	 * 96x96
	 */
	middle2(96),
	/**
	 * 132x132
	 */
	big(132);
	private int size;

	FaceSize(int size) {
		this.size = size;
	}

	public int getInt() {
		return size;
	}
}
