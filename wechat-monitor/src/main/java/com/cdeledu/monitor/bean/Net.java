package com.cdeledu.monitor.bean;

import java.io.Serializable;

/**
 * @类描述: 获取网络信息
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月31日 下午4:14:19
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class Net implements Serializable {

	private static final long serialVersionUID = -2359077483566491608L;
	/** 网络设备名称 */
	private String name = null;
	/** 网卡的物理地址 */
	private String hwaddr = null;
	/** 网卡类型 */
	private String type = null;
	/** 网卡描述信息 */
	private String description = null;
	/** IP地址 */
	private String address = null;
	private String destination = null;
	/** 网关广播地址 */
	private String broadcast = null;
	/** 子网掩码 */
	private String netmask = null;
	private long flags = 0L;
	/** 设置网卡的最大传输单元 */
	private long mtu = 0L;
	private long metric = 0L;
	/** 接收到的总字节数 */
	private long rxBytes = 0L;
	/** 接收的总包裹数 */
	private long rxPackets = 0L;
	/** 接收到的错误包数 */
	private long rxErrors = 0L;
	// 表示这个数据包已经进入到网卡的接收缓存fifo队列，并且开始被系统中断处理准备进行数据包拷贝（从网卡缓存fifo队列拷贝到系统内存），但由于此时的系统原因（比如内存不够等）导致这个数据包被丢掉，即这个数据包被Linux系统丢掉。
	private long rxDropped = 0L; // 接收时丢弃的包数
	// 表示这个数据包还没有被进入到网卡的接收缓存fifo队列就被丢掉，因此此时网卡的fifo是满的。为什么fifo会是满的？因为系统繁忙，来不及响应网卡中断，导致网卡里的数据包没有及时的拷贝到系统内存，fifo是满的就导致后面的数据包进不来，即这个数据包被网卡硬件丢掉。
	private long rxOverruns = 0L;
	private long rxFrame = 0L;
	/** 发送的总字节数 */
	private long txBytes = 0L;
	/** 发送的总包裹数 */
	private long txPackets = 0L;
	/** 发送数据包时的错误数 */
	private long txErrors = 0L;
	/** 发送时丢弃的包数 */
	private long txDropped = 0L;
	private long txOverruns = 0L;
	private long txCollisions = 0L;
	private long txCarrier = 0L;
	private long speed = 0L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHwaddr() {
		return hwaddr;
	}

	public void setHwaddr(String hwaddr) {
		this.hwaddr = hwaddr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public long getFlags() {
		return flags;
	}

	public void setFlags(long flags) {
		this.flags = flags;
	}

	public long getMtu() {
		return mtu;
	}

	public void setMtu(long mtu) {
		this.mtu = mtu;
	}

	public long getMetric() {
		return metric;
	}

	public void setMetric(long metric) {
		this.metric = metric;
	}

	public long getRxBytes() {
		return rxBytes;
	}

	public void setRxBytes(long rxBytes) {
		this.rxBytes = rxBytes;
	}

	public long getRxPackets() {
		return rxPackets;
	}

	public void setRxPackets(long rxPackets) {
		this.rxPackets = rxPackets;
	}

	public long getRxErrors() {
		return rxErrors;
	}

	public void setRxErrors(long rxErrors) {
		this.rxErrors = rxErrors;
	}

	public long getRxDropped() {
		return rxDropped;
	}

	public void setRxDropped(long rxDropped) {
		this.rxDropped = rxDropped;
	}

	public long getRxOverruns() {
		return rxOverruns;
	}

	public void setRxOverruns(long rxOverruns) {
		this.rxOverruns = rxOverruns;
	}

	public long getRxFrame() {
		return rxFrame;
	}

	public void setRxFrame(long rxFrame) {
		this.rxFrame = rxFrame;
	}

	public long getTxBytes() {
		return txBytes;
	}

	public void setTxBytes(long txBytes) {
		this.txBytes = txBytes;
	}

	public long getTxPackets() {
		return txPackets;
	}

	public void setTxPackets(long txPackets) {
		this.txPackets = txPackets;
	}

	public long getTxErrors() {
		return txErrors;
	}

	public void setTxErrors(long txErrors) {
		this.txErrors = txErrors;
	}

	public long getTxDropped() {
		return txDropped;
	}

	public void setTxDropped(long txDropped) {
		this.txDropped = txDropped;
	}

	public long getTxOverruns() {
		return txOverruns;
	}

	public void setTxOverruns(long txOverruns) {
		this.txOverruns = txOverruns;
	}

	public long getTxCollisions() {
		return txCollisions;
	}

	public void setTxCollisions(long txCollisions) {
		this.txCollisions = txCollisions;
	}

	public long getTxCarrier() {
		return txCarrier;
	}

	public void setTxCarrier(long txCarrier) {
		this.txCarrier = txCarrier;
	}

	public long getSpeed() {
		return speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}
}
