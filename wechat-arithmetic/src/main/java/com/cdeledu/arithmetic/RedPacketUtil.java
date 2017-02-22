package com.cdeledu.arithmetic;

/**
 * @类描述: 实现微信红包分配算法
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年12月11日 下午12:53:27
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class RedPacketUtil {
	/** ----------------------------------------------------- Fields start */
	/** 设置金额的上下限:随机的最小红包是1分，最大金额是200元 */
	private static final float MINMONEY = 0.01f;
	private static final float MAXMONEY = 200f;

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 分配红包之前要判断金额是否合法
	 * @param money
	 *            红包总金额
	 * @param count
	 *            总人数
	 * @return
	 */
	public static boolean isRight(double money, int count) {
		double avg = money / count;// 平均值
		if (avg < MINMONEY) {// 小于最小金额
			return false;
		} else if (avg > MAXMONEY) {// 大于最大金额
			return false;
		}
		return true;
	}

	/**
	 * @方法描述: 随机产生一个红包
	 * @param sumMoney
	 * @param minMoney
	 *            最小金额
	 * @param maxMoney
	 *            最大金额
	 * @param count
	 */
	public static double randomRedPacket(double sumMoney, double minMoney, double maxMoney,
			int count) {
		// 若是只有一个,直接返回红包金额
		if (1 == count) {
			return sumMoney;
		}

		// 若是最小金额红包 == 最大金额红包， 直接返回最小金额红包
		if (Double.compare(minMoney, maxMoney) == 0) {
			return minMoney;
		}
		// 校验 最大值 maxMoney 要是比sumMoney 金额高的话？ 去 sumMoney 金额
		double totalMoney = Double.compare(maxMoney, sumMoney) < 0 ? maxMoney : sumMoney;

		// 随机产生一个红包 = 随机一个数* (金额-最小)+最小
		double randomMoney = Math.rint(Math.random() * (totalMoney - minMoney) + minMoney);
		// 剩下的金额
		double moneyOther = totalMoney - randomMoney;
		// 判断红包金额是否合理,如果不合理则重新产生分配方案
		double moneyReal = 0f;
		if (isRight(moneyOther, count - 1)) {
			return randomMoney;
		} else {
			// 本次红包过大，导致下次的红包过小；如果红包过大，下次就随机一个小值到本次红包金额的一个红包
			double avg = moneyOther / (count - 1);
			if (avg < MINMONEY) {
				// 递归调用，修改红包最大金额
			} else if (avg > MAXMONEY) {
				// 递归调用，修改红包最小金额
			}
		}
		return moneyReal;
	}
	/** ----------------------------------------------- [私有方法] */
}