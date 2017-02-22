package com.cdeledu.common.exception;

/**
 * @ClassName: ElementNotFoundException
 * @author: 独泪了无痕
 * @date: 2015年9月14日 上午9:49:28
 * @version: V1.0
 * @history:
 */
public class ElementNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -6351880890462443259L;
	private final String elementName_;
	private final String attributeName_;
	private final String attributeValue_;

	public ElementNotFoundException(String elementName, String attributeName,
			String attributeValue) {
		super("elementName=[" + elementName + "] attributeName=["
				+ attributeName + "] attributeValue=[" + attributeValue + "]");

		this.elementName_ = elementName;
		this.attributeName_ = attributeName;
		this.attributeValue_ = attributeValue;
	}

	public String getElementName() {
		return this.elementName_;
	}

	public String getAttributeName() {
		return this.attributeName_;
	}

	public String getAttributeValue() {
		return this.attributeValue_;
	}
}
