package com.cdeledu.monitor.bean;

import java.io.Serializable;

/**
 * @类描述: 获取文件系统信息
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月31日 下午4:12:48
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class FileSystem implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 盘符名称 */
	private String devName;
	/** 盘符路径 */
	private String dirName;
	/** 盘符标志 */
	private long flags;
	private String options;
	/** 盘符类型 */
	private String sysTypeName;
	/**
	 * 盘符文件系统类型 <br>
	 * 0: TYPE_UNKNOWN ：未知;<br>
	 * 1: TYPE_NONE;<br>
	 * 2: TYPE_LOCAL_DISK : 本地硬盘;<br>
	 * 3: TYPE_NETWORK ：网络;<br>
	 * 4: TYPE_RAM_DISK ：闪存;<br>
	 * 5: TYPE_CDROM ：光驱;<br>
	 * 6: TYPE_SWAP ：页面交换;
	 */
	private int type;
	/** 盘符类型名 */
	private String typeName;
	private long avail;
	private double diskQueue;
	/** 读出字节 */
	private long diskReadBytes;
	/** 读出 */
	private long diskReads;
	private double diskServiceTime;
	/** 写入字节 */
	private long diskWriteBytes;
	/** 写入 */
	private long diskWrites;
	private long files;
	/** 剩余大小 */
	private long free;
	private long freeFiles;
	/** 总大小 */
	private long total;
	/** 已经使用量 */
	private long used;
	/** 资源的利用率 */
	private double usePercent;

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public long getFlags() {
		return flags;
	}

	public void setFlags(long flags) {
		this.flags = flags;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getSysTypeName() {
		return sysTypeName;
	}

	public void setSysTypeName(String sysTypeName) {
		this.sysTypeName = sysTypeName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public long getAvail() {
		return avail;
	}

	public void setAvail(long avail) {
		this.avail = avail;
	}

	public double getDiskQueue() {
		return diskQueue;
	}

	public void setDiskQueue(double diskQueue) {
		this.diskQueue = diskQueue;
	}

	public long getDiskReadBytes() {
		return diskReadBytes;
	}

	public void setDiskReadBytes(long diskReadBytes) {
		this.diskReadBytes = diskReadBytes;
	}

	public long getDiskReads() {
		return diskReads;
	}

	public void setDiskReads(long diskReads) {
		this.diskReads = diskReads;
	}

	public double getDiskServiceTime() {
		return diskServiceTime;
	}

	public void setDiskServiceTime(double diskServiceTime) {
		this.diskServiceTime = diskServiceTime;
	}

	public long getDiskWriteBytes() {
		return diskWriteBytes;
	}

	public void setDiskWriteBytes(long diskWriteBytes) {
		this.diskWriteBytes = diskWriteBytes;
	}

	public long getDiskWrites() {
		return diskWrites;
	}

	public void setDiskWrites(long diskWrites) {
		this.diskWrites = diskWrites;
	}

	public long getFiles() {
		return files;
	}

	public void setFiles(long files) {
		this.files = files;
	}

	public long getFree() {
		return free;
	}

	public void setFree(long free) {
		this.free = free;
	}

	public long getFreeFiles() {
		return freeFiles;
	}

	public void setFreeFiles(long freeFiles) {
		this.freeFiles = freeFiles;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getUsed() {
		return used;
	}

	public void setUsed(long used) {
		this.used = used;
	}

	public double getUsePercent() {
		return usePercent;
	}

	public void setUsePercent(double usePercent) {
		this.usePercent = usePercent;
	}

	@Override
	public String toString() {
		return "FileSystem [devName=" + devName + ", dirName=" + dirName + ", flags=" + flags
				+ ", options=" + options + ", sysTypeName=" + sysTypeName + ", type=" + type
				+ ", typeName=" + typeName + ", avail=" + avail + ", diskQueue=" + diskQueue
				+ ", diskReadBytes=" + diskReadBytes + ", diskReads=" + diskReads
				+ ", diskServiceTime=" + diskServiceTime + ", diskWriteBytes=" + diskWriteBytes
				+ ", diskWrites=" + diskWrites + ", files=" + files + ", free=" + free
				+ ", freeFiles=" + freeFiles + ", total=" + total + ", used=" + used
				+ ", usePercent=" + usePercent + "]";
	}
}
