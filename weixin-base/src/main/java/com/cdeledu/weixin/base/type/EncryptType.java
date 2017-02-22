package com.cdeledu.weixin.base.type;

/**
 * @ClassName: EncryptType
 * @Description: <ul>
 *               <li>信息加密类型（暂时只有raw和aes两种值)</li>
 *               <li>1.encrypt_type的值为raw时表示为不加密</li>
 *               <li>2.encrypt_type的值为aes时，表示aes加密</li>
 *               <li>3.无encrypt_type参数同样表示不加密</li>
 *               </ul>
 * @author: 独泪了无痕
 * @date: 2015年10月17日 下午2:15:01
 * @version: V1.0
 * @history:
 */
public enum EncryptType {
	/**
	 * 明文模式
	 */
	RAW,
	/**
	 * 密文模式
	 */
	AES;
}
