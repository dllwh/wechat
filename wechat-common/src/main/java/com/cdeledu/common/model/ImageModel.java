package com.cdeledu.common.model;

import java.io.Serializable;

public class ImageModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name; // 名称
	private String ext; // 扩展名
	private int width; // 宽度
	private int height; // 高度
	private double size; // 大小KB

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ImageModel [图片的名称=" + name + ", 图片的后缀=" + ext + ", 图片的宽度=" + width + ", 图片的高度="
				+ height + ", 图片的大小=" + size + "]";
	}

}
