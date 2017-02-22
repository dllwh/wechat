package com.cdeledu.weixin.base.exception;

/**
 * @ClassName: AesException
 * @Description: 【消息加解密-接入指引】函数错误返回码
 * @author: 独泪了无痕
 * @date: 2015年10月25日 下午4:55:33
 * @version: V1.0
 * @see <a
 *      href="http://mp.weixin.qq.com/wiki/0/61c3a8b9d50ac74f18bdf2e54ddfc4e0.html#.E5.87.BD.E6.95.B0.E9.94.99.E8.AF.AF.E8.BF.94.E5.9B.9E.E7.A0.81">函数错误返回码</a>
 */
public class AesException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * 函数返回码
	 */
	public final static int OK = 0;
	public final static int ValidateSignatureError = -40001;
	public final static int ParseXmlError = -40002;
	public final static int ComputeSignatureError = -40003;
	public final static int IllegalAesKey = -40004;
	public final static int ValidateAppidError = -40005;
	public final static int EncryptAESError = -40006;
	public final static int DecryptAESError = -40007;
	public final static int IllegalBuffer = -40008;
	public final static int EncodeBase64Error = -40009;
	public final static int DecodeBase64Error = -40010;
	public final static int GenReturnXmlError = -40011;

	private int code;

	public int getCode() {
		return code;
	}

	public AesException(int code) {
		super(getMessage(code));
		this.code = code;
	}

	private static String getMessage(int code) {
		switch (code) {
		case OK:
			return "处理成功";
		case ValidateSignatureError:
			return "校验签名失败";
		case ParseXmlError:
			return "解析xml失败";
		case ComputeSignatureError:
			return "计算签名失败";
		case IllegalAesKey:
			return "不合法的AESKey";
		case ValidateAppidError:
			return "校验AppID失败";
		case EncryptAESError:
			return "AES加密失败";
		case DecryptAESError:
			return "AES解密失败";
		case IllegalBuffer:
			return "公众平台发送的xml不合法";
		case EncodeBase64Error:
			return "Base64编码失败";
		case DecodeBase64Error:
			return "Base64解码失败";
		case GenReturnXmlError:
			return "公众帐号生成回包xml失败";
		default:
			return null;
		}
	}
}
