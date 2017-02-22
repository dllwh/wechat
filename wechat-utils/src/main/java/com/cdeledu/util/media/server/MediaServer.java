package com.cdeledu.util.media.server;

/**
 * @类描述: 多媒体播放操作辅助类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月11日 下午12:23:18
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface MediaServer {
	/**
	 * @方法描述: 播放
	 * @param location
	 *            声音文件路径
	 */
	public void playClick(String location);

	/**
	 * @方法描述: 播放声音文件
	 * @param soundFileName
	 *            声音文件路径（可以是MP3、WAV、Midi等声音文件）
	 * @param Repeat
	 *            是否重复播放
	 */
	public void playClick(String soundFileName, boolean Repeat);

	/**
	 * @方法描述: 得到当前播放位置
	 * @return
	 */
	public float getCurrentPosition();

	/**
	 * @方法描述: 得到文件的时间
	 * @return
	 */
	public float getDuration();

	/**
	 * @方法描述: 后退播放
	 */
	public void prevClick();

	/**
	 * @方法描述: 前进播放
	 */
	public void nextClick();

	/**
	 * @方法描述: 暂停播放
	 */
	public void pauseClick();

	/**
	 * @方法描述: 停止播放
	 */
	public void stopClick();

	/**
	 * @方法描述: 清除操作
	 */
	public void dispose();

	/**
	 * @方法描述: 获取音量
	 * @return
	 */
	public float getVolume();

	/**
	 * @方法描述: 设置音量的百分比
	 * @return
	 */
	public float setVolume();

	/**
	 * @方法描述: 返回当前状态播放：播放，暂停，停止等
	 * @return
	 */
	public String getSoundPlayState();
}
