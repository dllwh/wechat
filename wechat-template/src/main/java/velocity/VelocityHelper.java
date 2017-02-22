package velocity;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * @类描述: Velocity模板引擎辅助类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年1月20日 下午5:20:24
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class VelocityHelper {
	/** ----------------------------------------------------- Fields start */
	private static VelocityEngine ve;
	/**
	 * <b>实例化Velocity模板引擎并返回</b> 为避免冲突并独享配置参数，此处自己Velocity生成一个VelocityEngine实例
	 * 而非直接使用Velocity提供的单例引擎类org.apache.velocity.app.Velocity。
	 * 
	 * @return 返回VelocityEngine实例
	 */
	static {
		ve = getInstance();
	}

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	private static VelocityEngine getInstance() {
		if (null == ve) {
			synchronized (VelocityHelper.class) {
				if (null == ve) {
					ve = new VelocityEngine();
					try {
						ve.init(getDefaultProperties());
					} catch (Exception e) {
						throw new RuntimeException("初始化Velocity模板引擎实例失败", e);
					}
				}
			}

		}
		return ve;
	}

	/**
	 * 加载Velocity模板引擎属性配置文件
	 * 
	 * @return
	 */
	private static Properties getDefaultProperties() {
		InputStream is = VelocityHelper.class.getResourceAsStream("velocity.aos.properties");
		Properties props = new Properties();
		try {
			props.load(is);
			is.close();
		} catch (Exception e) {
			throw new RuntimeException("读取Velocity模板引擎属性配置文件出错[velocity.aos.properties]", e);
		}
		return props;
	}

	/**
	 * @方法描述: 驱动字符串模板
	 * @param pTemplateString
	 *            模板对象
	 * @param pDto
	 *            合并参数集合(将模板中所需变量全部压入Dto)
	 * @return 返回StringWriter对象
	 * @throws Exception
	 */
	private static String mergeStringTemplate(String pTemplateString, Map<String, Object> pDto) {

		if (StringUtils.isBlank(pTemplateString)) {
			throw new RuntimeException("文件模板资源路径不能为空");
		}
		StringWriter writer = new StringWriter();
		VelocityContext context = convertDto2VelocityContext(pDto);
		try {
			ve.evaluate(context, writer, "", pTemplateString);
		} catch (Exception e) {
			throw new RuntimeException("字符串模板合并失败", e);
		}
		return writer.toString();
	}

	/**
	 * 将Dto对象转换为VelocityContext对象
	 * 
	 * @param pDto
	 *            传入的Dto对象
	 * 
	 * @return 返回VelocityContext对象
	 */
	private static VelocityContext convertDto2VelocityContext(Map<String, Object> pDto) {
		if (MapUtils.isEmpty(pDto))
			return null;
		@SuppressWarnings("rawtypes")
		Iterator it = pDto.keySet().iterator();
		VelocityContext context = new VelocityContext();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object value = pDto.get(key);
			context.put(key, value);
		}
		return context;
	}

	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 参数填充式合并字符串
	 * @param template
	 *            待填充的模版字符串。用法：我{0}你，{1}!
	 * @param param
	 *            可变的参数，顺序填充模版占位符...
	 * @return
	 */
	public static String merge(String template, Object... param) {
		int i = 0;
		Map<String, Object> dto = new HashMap<>();
		for (Object object : param) {
			String key = "p" + i;
			dto.put(key, object);
			i++;
		}
		template = StringUtils.replace(template, "{", "${p");
		return mergeStringTemplate(template, dto);
	}

	/**
	 * @方法描述: 测试方法
	 */
	public static void main(String[] args) {
		String template = "我{0}你，{1}!";
		System.out.println(merge(template, "喜欢", "咋的"));
	}

}
