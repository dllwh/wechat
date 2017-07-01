package com.cdeledu.monitor.listener;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

/**
 * @类描述: Session详情
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月1日 下午6:52:21
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SessionInformations implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SESSION_COUNTRY_KEY = "monitor.country";
	public static final String SESSION_REMOTE_ADDR = "monitor.remoteAddr";
	public static final String SESSION_REMOTE_USER = "monitor.remoteUser";

	@SuppressWarnings("all")
	private static final ByteArrayOutputStream TEMP_OUTPUT = new ByteArrayOutputStream(8 * 1024);
	private final String id;
	private final Date lastAccess; // 最后访问时间
	private final Date age; // session 存活时间
	private final Date expirationDate; // 过期时间
	private final int attributeCount; // session中存储对象属性个数
	private final boolean serializable; // session对象中是否有序列化元素
	private final String country; // 访问用户所在国家
	private final String remoteAddr; // 远程地址
	private final String remoteUser; // 远程用户
	private final int serializedSize; // session存储的序列化对象序列化字节大小
	@SuppressWarnings("all")
	private final List<SessionAttribute> attributes;

	static class SessionAttribute implements Serializable {
		private static final long serialVersionUID = 4786854834871331127L;
		private final String name; // 属性名称
		private final String type; // 属性类型
		private final String content; // 属性内容String.valueOf
		private final boolean serializable; // 是否可以序列化
		private final int serializedSize; // 序列化大小

		SessionAttribute(HttpSession session, String attributeName) {
			super();
			assert session != null;
			assert attributeName != null;
			name = attributeName;
			final Object value = session.getAttribute(attributeName);
			serializable = value == null || value instanceof Serializable;
			serializedSize = getObjectSize(value);
			if (value == null) {
				content = null;
				type = null;
			} else {
				String tmp;
				try {
					tmp = String.valueOf(value);
				} catch (final Exception e) {
					tmp = e.toString();
				}
				content = tmp;
				type = value.getClass().getName();
			}
		}

		String getName() {
			return name;
		}

		String getType() {
			return type;
		}

		String getContent() {
			return content;
		}

		boolean isSerializable() {
			return serializable;
		}

		int getSerializedSize() {
			return serializedSize;
		}
	}

	SessionInformations(HttpSession session, boolean includeAttributes) {
		super();
		assert session != null;
		id = session.getId();
		final long now = System.currentTimeMillis();
		lastAccess = new Date(now - session.getLastAccessedTime());
		age = new Date(now - session.getCreationTime());
		expirationDate = new Date(
				session.getLastAccessedTime() + session.getMaxInactiveInterval() * 1000L);

		final List<String> attributeNames = Collections.list(session.getAttributeNames());
		attributeCount = attributeNames.size();
		serializable = computeSerializable(session, attributeNames);

		final Object countryCode = session.getAttribute(SESSION_COUNTRY_KEY);
		if (countryCode == null) {
			country = null;
		} else {
			country = countryCode.toString().toLowerCase(Locale.getDefault());
		}

		final Object addr = session.getAttribute(SESSION_REMOTE_ADDR);
		if (addr == null) {
			remoteAddr = null;
		} else {
			remoteAddr = addr.toString();
		}

		Object user = session.getAttribute(SESSION_REMOTE_USER);
		if (user == null) {
			user = session.getAttribute("ACEGI_SECURITY_LAST_USERNAME");
			if (user == null) {
				user = session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
			}
		}
		if (user == null) {
			remoteUser = null;
		} else {
			remoteUser = user.toString();
		}

		serializedSize = computeSerializedSize(session, attributeNames);

		if (includeAttributes) {
			attributes = new ArrayList<SessionAttribute>(attributeCount);
			for (final String attributeName : attributeNames) {
				attributes.add(new SessionAttribute(session, attributeName));
			}
		} else {
			attributes = null;
		}
	}

	/**
	 * 判断session对象中是否有序列化元素
	 * 
	 * @param session
	 * @param attributeNames
	 * @return
	 */
	private boolean computeSerializable(HttpSession session, List<String> attributeNames) {
		for (final String attributeName : attributeNames) {
			final Object attributeValue = session.getAttribute(attributeName);
			if (!(attributeValue == null || attributeValue instanceof Serializable)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * session 中可序列化大小
	 * 
	 * @param session
	 * @param attributeNames
	 * @return
	 */
	private int computeSerializedSize(HttpSession session, List<String> attributeNames) {
		if (!serializable) {
			return -1;
		}
		final List<Serializable> serializableAttributes = new ArrayList<Serializable>(
				attributeNames.size());
		for (final String attributeName : attributeNames) {
			final Object attributeValue = session.getAttribute(attributeName);
			serializableAttributes.add((Serializable) attributeValue);
		}
		return getObjectSize(serializableAttributes);
	}

	public String getId() {
		return id;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public Date getAge() {
		return age;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public int getAttributeCount() {
		return attributeCount;
	}

	public boolean isSerializable() {
		return serializable;
	}

	public String getCountry() {
		return country;
	}

	public String getCountryDisplay() {
		final String myCountry = getCountry();
		if (myCountry == null) {
			return null;
		}
		return new Locale("fr", myCountry).getDisplayCountry(Locale.getDefault());
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public String getRemoteUser() {
		return remoteUser;
	}

	public int getSerializedSize() {
		return serializedSize;
	}

	/**
	 * 获取当前session所有的attribute
	 * 
	 * @return
	 */
	List<SessionAttribute> getAttributes() {
		return Collections.unmodifiableList(attributes);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[id=" + getId() + ", remoteAddr=" + getRemoteAddr()
				+ ", serializedSize=" + getSerializedSize() + ']';
	}

	/**
	 * 获取对象大小，如果不可以序列化直接返回-1
	 * 
	 * @param object
	 * @return
	 */
	static int getObjectSize(Object object) {
		if (!(object instanceof Serializable)) {
			return -1;
		}
		final Serializable serializable = (Serializable) object;
		synchronized (TEMP_OUTPUT) {
			TEMP_OUTPUT.reset();
			try {
				final ObjectOutputStream out = new ObjectOutputStream(TEMP_OUTPUT);
				try {
					out.writeObject(serializable);
				} finally {
					out.close();
				}
				return TEMP_OUTPUT.size();
			} catch (final Throwable e) {
				return -1;
			}
		}
	}
}
