package com.cdeledu.util.apache.lang;

import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.constant.ConstantHelper;

/**
 * 
 * @ClassName: StringUtilHelper
 * @Description: 字符串工具类(继承与Apache common中的StringUtils 类)
 * @author: 独泪了无痕
 * @date: 2015年9月10日 下午3:56:45
 * @version: V1.0
 * @history:
 */
public class StringUtilHelper extends StringUtils {
	/*--------------------------私有属性 start -------------------------------*/
	/** 特殊字符串 */
	public static char[] symbols = { '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=', ',', '.',
			':', ';', '"', '，', '。', '：', '；', ' ', '`', '~', '！', '/', '?', '？', '、', '<', '>', '》', '《', '）', '（',
			'|', 'Θ', '∧', 'Γ', 'α', 'β', 'γ', 'δ', 'ε', 'ζ', 'η', 'ι', 'κ', 'λ', 'μ', 'ν', 'ξ', 'ο', 'π', 'ρ', 'σ',
			'τ', 'υ', 'φ', 'χ', 'ψ', 'ω', 'Г', 'Д', 'Ё', 'Ж', 'З', 'И', 'Й', 'Л', 'Ф', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы',
			'Ь', 'Э', 'Ю', 'Я', 'б', 'г', 'д', 'ё', 'ж', 'з', 'и', 'й', 'к', 'ā', 'á', 'ǎ', 'à', 'ō', 'ó', 'ǒ', 'ò',
			'ê', 'ē', 'é', 'ě', 'è', 'ī', 'í', 'ǐ', 'ì', 'ū', 'ú', 'ǔ', 'ù', 'ǖ', 'ǘ', 'ǚ', 'ǜ', '§', '№', '☆', '★',
			'○', '●', '◎', '◇', '◆', '□', '■', '△', '▲', '※', '→', '←', '↑', '↓', '〓', '＃', '＆', '＠', '⊙', '⓪', '①',
			'②', '③', '④', '⑤', '⑥', '⑦', '⑧', '⑨', '⑩', '⑪', '⑫', '⑬', '⑭', '⑮', '⑯', '⑰', '⑱', '⑲', '⑳', '⊕', '¤',
			'㊣', '▂', '▃', '▄', '▅', '▆', '▇', '█', '▓', '回', '≡', '╝', '╚', '╔', '╗', '╬', '╓', '╩', '┠', '┨', '┯',
			'┷', '┏', '┓', '┗', '┛', '┳', '⊥', '『', '』', '┌', '♀', '◣', '◢', '◥', '▼', '▽', '⊿', '{', '}', '[', ']',
			'∫', '∮', '∝', '∞', '∨', '∑', '∏', '∪', '∩', '∈', '∵', '∴', '∥', '∠', '⌒', '√', '∟', '≌', '∽', '≦', '≧',
			'≈', '≠', '≤', '≥', '≮', '≯', '【', '】', '〖', '〗', 'Ⓐ', 'Ⓑ', 'Ⓒ', 'Ⓓ', 'Ⓔ', 'Ⓕ', 'Ⓖ', 'Ⓗ', 'Ⓘ', 'Ⓙ', 'Ⓚ',
			'Ⓛ', 'Ⓜ', 'Ⓝ', 'Ⓞ', 'Ⓟ', 'Ⓠ', 'Ⓡ', 'Ⓢ', 'Ⓣ', 'Ⓤ', 'Ⓥ', 'Ⓦ', 'Ⓧ', 'Ⓨ', 'Ⓩ', 'ⓐ', 'ⓑ', 'ⓒ', 'ⓓ', 'ⓔ', 'ⓕ',
			'ⓖ', 'ⓗ', 'ⓘ', 'ⓙ', 'ⓚ', 'ⓛ', 'ⓜ', 'ⓝ', 'ⓞ', 'ⓟ', 'ⓠ', 'ⓡ', 'ⓢ', 'ⓣ', 'ⓤ', 'ⓥ', 'ⓦ', 'ⓧ', 'ⓨ', 'ⓩ', 'Ⅰ',
			'Ⅱ', 'Ⅲ', 'Ⅳ', 'Ⅴ', 'Ⅵ', 'Ⅶ', 'Ⅷ', 'Ⅸ', 'Ⅹ', 'Ⅺ', 'Ⅻ', '⒜', '⒝', '⒞', '⒟', '⒠', '⒡', '⒢', '⒣', '⒤', '⒥',
			'⒦', '⒧', '⒨', '⒩', '⒪', '⒫', '⒬', '⒭', '⒮', '⒯', '⒰', '⒱', '⒲', '⒳', '⒴', '⒵', 'ぁ', 'あ', 'ぃ', 'い', 'ぅ',
			'う', 'ぇ', 'え', 'ぉ', 'お', 'か', 'が', 'き', 'ぎ', 'く', 'ぐ', 'け', 'げ', 'こ', 'ご', 'さ', 'ざ', 'し', 'じ', 'す', 'ず',
			'せ', 'ぜ', 'そ', 'ぞ', 'た', 'だ', 'ち', 'ぢ', 'っ', 'つ', 'づ', 'て', 'で', 'と', 'ど', 'な', 'に', 'ぬ', 'ね', 'の', 'は',
			'ば', 'ぱ', 'ひ', 'び', 'ぴ', 'ふ', 'ぶ', 'ぷ', 'へ', 'べ', 'ぺ', 'ほ', 'ぼ', 'ぽ', 'ま', 'み', 'む', 'め', 'も', 'ゃ', 'や',
			'ゅ', 'ゆ', 'ょ', 'よ', 'ら', 'り', 'る', 'れ', 'ろ', 'ゎ', 'わ', 'ゐ', 'ゑ', 'を', 'ん', 'ゔ', 'ゕ', 'ゖ', 'ァ', 'ア', 'ィ',
			'イ', 'ゥ', 'ウ', 'ェ', 'エ', 'ォ', 'オ', 'カ', 'ガ', 'キ', 'ギ', 'ク', 'グ', 'ケ', 'ゲ', 'コ', 'ゴ', 'サ', 'ザ', 'シ', 'ジ',
			'ス', 'ズ', 'セ', 'ゼ', 'ソ', 'ゾ', 'タ', 'ダ', 'チ', 'ヂ', 'ッ', 'ツ', 'ヅ', 'テ', 'デ', 'ト', 'ド', 'ナ', 'ニ', 'ヌ', 'ネ',
			'ノ', 'ハ', 'バ', 'パ', 'ヒ', 'ビ', 'ピ', 'フ', 'ブ', 'プ', 'ヘ', 'ベ', 'ペ', 'ホ', 'ボ', 'ポ', 'マ', 'ミ', 'ム', 'メ', 'モ',
			'ャ', 'ヤ', 'ュ', 'ユ', 'ョ', 'ヨ', 'ラ', 'リ', 'ル', 'レ', 'ロ', 'ヮ', 'ワ', 'ヰ', 'ヱ', 'ヲ', 'ン', 'ヴ', 'ヵ', 'ヶ', 'ヷ',
			'ヸ', 'ヹ', 'ヺ', '・', 'ー', 'ヽ', 'ヾ', 'ヿ', '゠', 'ㇰ', 'ㇱ', 'ㇲ', 'ㇳ', 'ㇴ', 'ㇵ', 'ㇶ', 'ㇷ', 'ㇸ', 'ㇹ', 'ㇺ', 'ㇻ',
			'ㇼ', 'ㇽ', 'ㇾ', 'ㇿ', '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖', '拾', '佰', '仟', '万', '亿', '吉', '太',
			'拍', '艾', '分', '厘', '毫', '微', '卍', '卐', '卄', '巜', '弍', '弎', '弐', '朤', '氺', '曱', '甴', '囍', '兀', '々', '〆',
			'〡', '〢', '〣', '〤', '〥', '〦', '〧', '〨', '〩', '㊎', '㊍', '㊌', '㊋', '㊏', '㊚', '㊛', '㊐', '㊊', '㊤', '㊥', '㊦',
			'㊧', '㊨', '㊒', '㊫', '㊑', '㊓', '㊔', '㊕', '㊖', '㊗', '㊘', '㊜', '㊝', '㊞', '㊟', '㊠', '㊡', '㊢', '㊩', '㊪', '㊬',
			'㊭', '㊮', '㊯', '㊰', '㊀', '㊁', '㊂', '㊃', '㊄', '㊅', '㊆', '㊇', '㊈', '㊉', '♚', '♛', '♝', '♞', '♜', '♟', '♔',
			'♕', '♗', '♘', '♖', 'ㄅ', 'ㄆ', 'ㄇ', 'ㄈ', 'ㄉ', 'ㄊ', 'ㄋ', 'ㄌ', 'ㄍ', 'ㄎ', 'ㄏ', 'ㄐ', 'ㄑ', 'ㄒ', 'ㄓ', 'ㄔ', 'ㄕ',
			'ㄖ', 'ㄗ', 'ㄘ', 'ㄙ', 'ㄚ', 'ㄛ', 'ㄜ', 'ㄝ', 'ㄞ', 'ㄟ', 'ㄠ', 'ㄡ', 'ㄢ', 'ㄣ', 'ㄤ', 'ㄥ', 'ㄦ', 'ㄧ', 'ㄨ', 'ㄩ', '☀',
			'☁', '☂', '☃', '☄', '☇', '☈', '☊', '☋', '☌', '☍', '♫', '♬', '♩', '♭', '♪', '∷', '﹌', '°', '′', '″', '＄',
			'￥', '〒', '￠', '￡', '％', '℃', '℉', '﹩', '﹪', '‰', '﹫', '㎡', '㏕', '㎜', '㎝', '㎞', '㏎', '㎏', '㎎', '㏄', 'º',
			'¹', '²', '³', '☏', '✐', '✎', '✏', '✑', '✒', '✍', '✉', '✁', '✂', '✃', '✄', '✆', '☎', '☑', '✓', '✔', '☐',
			'☒', '✗', '✘', '✕', '✖', '☢', '☠', '☣', '✈', '✡', '㍿', '☯', '☰', '☲', '☱', '☴', '☵', '☶', '☳', '☷', '☜',
			'☞', '☝', '☚', '☛', '☟', '✌', '♤', '♧', '♡', '♢', '♠', '♣', '♥', '♦', '❄', '♨', '웃', '유', '❖', '☽', '☾',
			'☪', '✿', '♂', '✪', '✯', '☭', '➳', '◕', '€', '£', 'Ұ', '₴', '₰', '¢', '₤', '¥', '₳', '₲', '₪', '₵', '元',
			'₣', '₱', '฿', '₡', '₮', '₭', '₩', 'ރ', '円', '₢', '₥', '₫', '₦', 'z', 'ł', '﷼', '₠', '₧', '₯', '₨', 'K',
			'č', 'र', '₹', 'ƒ', '₸', '↖', '↗', '↘', '↙', '↔', '↕', '➻', '➼', '➽', '➸', '➺', '➴', '➵', '➶', '➷', '➹',
			'▶', '►', '▷', '◁', '◀', '◄', '«', '»', '➩', '➪', '➫', '➬', '➭', '➮', '➯', '➱', '⏎', '➲', '➾', '➔', '➘',
			'➙', '➚', '➛', '➜', '➝', '➞', '➟', '➠', '➡', '➢', '➣', '➤', '➥', '➦', '➧', '➨', '↚', '↛', '↜', '↝', '↞',
			'↟', '↠', '↡', '↢', '↣', '↤', '↥', '↦', '↧', '↨', '⇄', '⇅', '⇆', '⇇', '⇈', '⇉', '⇊', '⇋', '⇌', '⇍', '⇎',
			'⇏', '⇐', '⇑', '⇒', '⇓', '⇔', '⇖', '⇗', '⇘', '⇙', '⇜', '↩', '↪', '↫', '↬', '↭', '↮', '↯', '↰', '↱', '↲',
			'↳', '↴', '↵', '↶', '↷', '↸', '↹', '↼', '↽', '↾', '↿', '⇀', '⇁', '⇂', '⇃', '⇞', '⇟', '⇠', '⇡', '⇢', '⇣',
			'⇤', '⇥', '⇦', '⇧', '⇨', '⇩', '⇪', '↺', '↻', '⇚', '⇛', '♐', '╯', '╰', '╮', '╭', '﹄', '﹃', '═', '╳', '╨',
			'╧', '╦', '╥', '╤', '╣', '╢', '╟', '╠', '╡', '╊', '╋', '╪', '╫', '║', '╒', '╕', '╖', '╘', '╙', '╛', '╜',
			'╞', '┺', '┻', '┼', '┽', '┾', '┿', '╀', '╁', '╂', '╃', '╄', '╅', '╆', '╇', '╈', '╉', '┪', '┫', '┬', '┭',
			'┮', '┰', '┱', '┲', '┴', '┵', '┶', '┸', '┹', '└', '┕', '┖', '┘', '┙', '┚', '├', '┝', '┞', '┟', '┡', '┢',
			'┣', '┤', '┥', '┦', '┧', '┩', '─', '━', '│', '┃', '╌', '╍', '╎', '╏', '┄', '┅', '┆', '┇', '┈', '┉', '┊',
			'┋', '┍', '┎', '┐', '┑', '┒', '∰', '∯', '∭', '∬', '⋚', '⋛', '⊱', '∱', '∲', '∳', '‱', '℅', 'ø', 'Ø', '≍',
			'≎', '≏', '≐', '≑', '≒', '≓', '≔', '≕', '≖', '≗', '≘', '≙', '≚', '≛', '≜', '≝', '≞', '≟', '≢', '≣', '≨',
			'≩', '⊰', '⅟', '½', '⅓', '⅕', '⅙', '⅛', '⅔', '⅖', '⅚', '⅜', '¾', '⅗', '⅝', '⅞', '⅘', '≂', '≃', '≄', '≅',
			'≆', '≇', '≉', '≊', '≋', '㏑', '㏒', 'ⅰ', 'ⅱ', 'ⅲ', 'ⅳ', 'ⅴ', 'ⅵ', 'ⅶ', 'ⅷ', 'ⅸ', 'ⅹ', '⒈', '⒉', '⒊', '⒋',
			'⒌', '⒍', '⒎', '⒏', '⒐', '⒑', '❶', '❷', '❸', '❹', '❺', '❻', '❼', '❽', '❾', '❿', '⓫', '⓬', '⓭', '⓮', '⓯',
			'⓰', '⓱', '⓲', '⓳', '⓴', '㈠', '㈢', '㈣', '㈤', '㈥', '㈦', '㈧', '㈨', '㈩', '➊', '➋', '➌', '➍', '➎', '➏', '➐',
			'➑', '➒', '➓', 'Ⅽ', 'Ⅾ', 'Ⅿ', 'Ⅼ' };

	/*--------------------------私有属性 end   -------------------------------*/
	/*--------------------------私有方法 start -------------------------------*/
	private static enum BASIC_TYPE {
		BYTE("byte"), SHORT("short"), INT("int"), LONG("long"), DOUBLE("double"), FLOAT("float"), BOOLEAN(
				"boolean"), CHAR("char");

		private Object value;

		private BASIC_TYPE(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.name() + " : " + value;
		}
	}

	/*--------------------------私有方法 end   -------------------------------*/
	/*--------------------------公有方法 start -------------------------------*/
	/*--------------------------公有方法 end   -------------------------------*/
	/**
	 * @方法:通过源字符串重复生成N次组成新的字符串
	 * @创建人:独泪了无痕
	 * @param src
	 *            源字符串 例如: 空格(" "), 星号("*"),"浙江" 等等...
	 * @param num
	 *            重复生成次数
	 * @return 返回已生成的重复字符串
	 */
	public static String repeat(String src, int num) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < num; i++)
			s.append(src);
		return s.toString();
	}

	/**
	 * @方法:判断文字内容重复
	 * @创建人:独泪了无痕
	 * @param content
	 * @return
	 */
	public static boolean isContentRepeat(String content) {
		int similarNum = 0;
		int forNum = 0;
		int subNum = 0;
		int thousandNum = 0;
		String startStr = "";
		String nextStr = "";
		float endNum = (float) 0.0;
		boolean result = false;

		if (StringUtils.isNotBlank(content) && content.length() > 0) {
			if (content.length() % 1000 > 0)
				thousandNum = (int) Math.floor(content.length() / 1000) + 1;
			else
				thousandNum = (int) Math.floor(content.length() / 1000);
			if (thousandNum < 3)
				subNum = 100 * thousandNum;
			else if (thousandNum < 6)
				subNum = 200 * thousandNum;
			else if (thousandNum < 9)
				subNum = 300 * thousandNum;
			else
				subNum = 3000;
			for (int j = 1; j < subNum; j++) {
				if (content.length() % j > 0)
					forNum = (int) Math.floor(content.length() / j) + 1;
				else
					forNum = (int) Math.floor(content.length() / j);
				if (result || j >= content.length())
					break;
				else {
					for (int m = 0; m < forNum; m++) {
						if (m * j > content.length() || (m + 1) * j > content.length()
								|| (m + 2) * j > content.length())
							break;
						startStr = content.substring(m * j, (m + 1) * j);
						nextStr = content.substring((m + 1) * j, (m + 2) * j);
						if (startStr.equals(nextStr)) {
							similarNum = similarNum + 1;
							endNum = (float) similarNum / forNum;
							if (endNum > 0.4) {
								result = true;
								break;
							}
						} else
							similarNum = 0;
					}
				}
			}
		}
		return result;
	}

	/**
	 * @方法:强制转换类型
	 * @创建人:独泪了无痕
	 * @param clazz
	 *            被转换成的类型
	 * @param value
	 *            需要转换的对象
	 * @return 转换后的对象
	 */
	public static Object cast(Class<?> clazz, Object value) {
		try {
			return clazz.cast(value);
		} catch (ClassCastException e) {
			String valueStr = String.valueOf(value);

			// 判断标准日期
			if (clazz.isAssignableFrom(Date.class)) {
				try {
					return DateUtilHelper.parseDateTime(valueStr, false);
				} catch (Exception parseE) {
					return valueStr;
				}
			}

			// 基本类型
			switch (BASIC_TYPE.valueOf(clazz.getSimpleName().toUpperCase())) {
			case BYTE:
				return Byte.parseByte(valueStr);
			case SHORT:
				return Short.parseShort(valueStr);
			case INT:
				return Integer.parseInt(valueStr);
			case LONG:
				return Long.parseLong(valueStr);
			case DOUBLE:
				return Double.parseDouble(valueStr);
			case FLOAT:
				return Float.parseFloat(valueStr);
			case BOOLEAN:
				return Boolean.parseBoolean(valueStr);
			case CHAR:
				return valueStr.charAt(0);
			default:
				return value;
			}
		}
	}

	/**
	 * @方法:转换基本类型
	 * @创建人:独泪了无痕
	 * @param clazz
	 * @return
	 */
	public static Class<?> castToPrimitive(Class<?> clazz) {
		BASIC_TYPE basicType;
		try {
			basicType = BASIC_TYPE.valueOf(clazz.getSimpleName().toUpperCase());
		} catch (Exception e) {
			return clazz;
		}
		// 基本类型
		switch (basicType) {
		case BYTE:
			return byte.class;
		case SHORT:
			return short.class;
		case INT:
			return int.class;
		case LONG:
			return long.class;
		case DOUBLE:
			return double.class;
		case FLOAT:
			return float.class;
		case BOOLEAN:
			return boolean.class;
		case CHAR:
			return char.class;
		default:
			return clazz;
		}
	}

	/**
	 * @方法:
	 * 		<ul>
	 *      <li>获得set或get方法对应的标准属性名</li>
	 *      <li>例如：setName 返回 name</li>
	 *      </ul>
	 * @创建人:独泪了无痕
	 * @param methodNameWithGet
	 * @return 如果是set或get方法名，返回field， 否则null
	 */
	public static String getGeneralField(String methodNameWithGet) {
		if (methodNameWithGet.startsWith("get") || methodNameWithGet.startsWith("set")) {
			return cutPreAndLowerFirst(methodNameWithGet, 3);
		}
		return null;
	}

	/**
	 * @方法:生成set方法名<br/>
	 * 					例如：name 返回 setName
	 * 
	 * @创建人:独泪了无痕
	 * @param fieldName
	 * @return
	 */
	public static String genSetter(String fieldName) {
		return upperFirstAndAddPre(fieldName, "set");
	}

	/**
	 * @方法:生成get方法名
	 * @创建人:独泪了无痕
	 * @param fieldName
	 * @return
	 */
	public static String genGetter(String fieldName) {
		return upperFirstAndAddPre(fieldName, "get");
	}

	/**
	 * @方法:去掉首部指定长度的字符串并将剩余字符串首字母小写. 例如：str=setName, preLength=3 -> return name
	 * 
	 * @创建人:独泪了无痕
	 * @param str
	 *            被处理的字符串
	 * @param preLength
	 *            去掉的长度
	 * @return 处理后的字符串，不符合规范返回null
	 */
	public static String cutPreAndLowerFirst(String str, int preLength) {
		if (str == null) {
			return null;
		}
		if (str.length() > preLength) {
			char first = Character.toLowerCase(str.charAt(preLength));
			if (str.length() > preLength + 1) {
				return first + str.substring(preLength + 1);
			}
			return String.valueOf(first);
		}
		return null;
	}

	/**
	 * @方法:原字符串首字母大写并在其首部添加指定字符串 例如：str=name, preString=get -> return getName
	 * @创建人:独泪了无痕
	 * @param str
	 *            被处理的字符串
	 * @param preString
	 *            被处理的字符串
	 * @return 处理后的字符串
	 */
	public static String upperFirstAndAddPre(String str, String preString) {
		if (str == null || preString == null) {
			return null;
		}
		return preString + Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	/**
	 * 
	 * @Title: newStringUtf8
	 * @Description:
	 * @author: 独泪了无痕
	 * @param bytes
	 * @param charset
	 * @return
	 */
	public static String newStringUtf8(final byte[] bytes, final Charset charset) {
		return bytes == null ? null : new String(bytes, charset);
	}

	/**
	 * @方法:检查包含空白字符在内的字符系列长度
	 * 
	 * 						<pre>
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 *                       </pre>
	 * 
	 * @创建人:独泪了无痕
	 * @param str
	 * @return
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * @方法:检查包含空白字符在内的字符系列长度
	 * @创建人:独泪了无痕
	 * @param str
	 * @return
	 */
	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}

	public static String setterName(String fieldName) {
		if (StringUtils.isNoneBlank(fieldName)) {
			fieldName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		}
		return fieldName;
	}

	/**
	 * @方法:去掉指定前缀
	 * @创建人:独泪了无痕
	 * @param str
	 *            字符串
	 * @param prefix
	 *            前缀
	 * @return 切掉后的字符串，若前缀不是 preffix， 返回原字符串
	 */
	public static String removePrefix(String str, String prefix) {
		if (str != null && str.startsWith(prefix)) {
			return str.substring(prefix.length());
		}
		return str;
	}

	/**
	 * @方法:忽略大小写去掉指定前缀
	 * @创建人:独泪了无痕
	 * @param str
	 *            字符串
	 * @param prefix
	 *            前缀
	 * @return 切掉后的字符串，若前缀不是 preffix， 返回原字符串
	 */
	public static String removePrefixIgnoreCase(String str, String prefix) {
		if (str != null && str.toLowerCase().startsWith(prefix.toLowerCase())) {
			return str.substring(prefix.length());
		}
		return str;
	}

	/**
	 * @方法:大写首字母
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static String upperFirst(String str) {
		if (isBlank(str)) {
			return str;
		}
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	/**
	 * @方法:小写首字母
	 * @创建人:独泪了无痕
	 * @param str
	 * @return
	 */
	public static String lowerFirst(String str) {
		if (isBlank(str)) {
			return str;
		}
		return Character.toLowerCase(str.charAt(0)) + str.substring(1);
	}

	/**
	 * @方法:获得StringReader
	 * @创建人:独泪了无痕
	 * @param str
	 *            字符串
	 * @return
	 */
	public static StringReader getReader(String str) {
		return new StringReader(str);
	}

	/**
	 * @方法:获得StringWriter
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static StringWriter getWriter() {
		return new StringWriter();
	}
	
	private static byte[] getBytes(final String content, final Charset charset) {
		if (content == null) {
			return null;
		}
		return content.getBytes(charset);
	}

	private static String newString(final byte[] bytes, final Charset charset) {
		return bytes == null ? null : new String(bytes, charset);
	}

	public static byte[] getBytesUtf8(final String content) {
		return getBytes(content, ConstantHelper.UTF_8);
	}

	public static String newStringUtf8(final byte[] bytes) {
		return newString(bytes, ConstantHelper.UTF_8);
	}
}
