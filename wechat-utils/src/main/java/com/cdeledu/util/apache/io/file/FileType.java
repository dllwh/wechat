package com.cdeledu.util.apache.io.file;

/**
 * 
 * @ClassName: FileType
 * @Description: 
 *               <ul>
 *               <li>http://happyprince.iteye.com/blog/1508358</li>
 *               </ul>
 * @author: 独泪了无痕
 * @date: 2015年8月3日 上午9:25:23
 * @version: V1.0
 * @history:
 */
public enum FileType {
	JPEG("FFD8FF"), 
	PNG("89504E47"),
	GIF("47494638"), 
	TIFF("49492A00"), 
	BMP("424D"), 
	DWG("41433130"), 
	PSD("38425053"),
	RTF("7B5C727466"), 
	XML("3C3F786D6C"), 
	HTML("68746D6C3E"),
	EML("44656C69766572792D646174653A"), 
	DBX("CFAD12FEC5FD746F"), 
	PST("2142444E"), 
	XLS_DOC("D0CF11E0"), 
	MDB("5374616E64617264204A"), 
	WPD("FF575043"), 
	EPS("252150532D41646F6265"), 
	PDF("255044462D312E"), 
	QDF("AC9EBD8F"), 
	PWL("E3828596"), 
	ZIP("504B0304"), 
	RAR("52617221"), 
	WAV("57415645"), 
	AVI("41564920"), 
	RAM("2E7261FD"), 
	RM("2E524D46"), 
	MPG("000001B"), 
	MOV("6D6F6F76"), 
	ASF("3026B2758E66CF11"), 
	MID("4D546864");

	private String value = "";

	private FileType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
