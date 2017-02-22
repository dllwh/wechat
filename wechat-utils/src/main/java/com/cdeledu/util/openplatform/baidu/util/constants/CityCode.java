package com.cdeledu.util.openplatform.baidu.util.constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @ClassName: CityCode
 * @Description: <ul>
 *               <li>百度地图城市名称-城市代码（cityCode）关系对照文本</li>
 *               <li>1.百度地图城市名称不重复</li>
 *               <li>2.城市代码有重复值</li>
 *               </ul>
 * @author: 独泪了无痕
 * @date: 2015年8月31日 下午3:15:42
 * @version: V1.0
 * @history:
 */
public class CityCode {
	/** 百度地图城市名称-城市代码 */
	private static Map<String, String> cityMap = new HashMap<String, String>();

	static {
		cityMap.put("拉萨市", "100");
		cityMap.put("那曲地区", "101");
		cityMap.put("日喀则地区", "102");
		cityMap.put("阿里地区", "103");
		cityMap.put("昆明市", "104");
		cityMap.put("楚雄彝族自治州", "105");
		cityMap.put("玉溪市", "106");
		cityMap.put("红河哈尼族彝族自治州", "107");
		cityMap.put("普洱市", "108");
		cityMap.put("西双版纳傣族自治州", "109");
		cityMap.put("临沧市", "110");
		cityMap.put("大理白族自治州", "111");
		cityMap.put("保山市", "112");
		cityMap.put("怒江傈僳族自治州", "113");
		cityMap.put("丽江市", "114");
		cityMap.put("迪庆藏族自治州", "115");
		cityMap.put("德宏傣族景颇族自治州", "116");
		cityMap.put("张掖市", "117");
		cityMap.put("武威市", "118");
		cityMap.put("东莞市", "119");
		cityMap.put("东沙群岛", "120");
		cityMap.put("三亚市", "121");
		cityMap.put("鄂州市", "122");
		cityMap.put("乌海市", "123");
		cityMap.put("莱芜市", "124");
		cityMap.put("海口市", "125");
		cityMap.put("蚌埠市", "126");
		cityMap.put("河南省直辖县级行政单位", "1277");
		cityMap.put("济源市", "1277");
		cityMap.put("合肥市", "127");
		cityMap.put("阜阳市", "128");
		cityMap.put("芜湖市", "129");
		cityMap.put("安庆市", "130");
		cityMap.put("北京市", "131");
		cityMap.put("重庆市", "132");
		cityMap.put("南平市", "133");
		cityMap.put("泉州市", "134");
		cityMap.put("庆阳市", "135");
		cityMap.put("定西市", "136");
		cityMap.put("韶关市", "137");
		cityMap.put("佛山市", "138");
		cityMap.put("茂名市", "139");
		cityMap.put("珠海市", "140");
		cityMap.put("梅州市", "141");
		cityMap.put("桂林市", "142");
		cityMap.put("河池市", "143");
		cityMap.put("崇左市", "144");
		cityMap.put("钦州市", "145");
		cityMap.put("贵阳市", "146");
		cityMap.put("六盘水市", "147");
		cityMap.put("秦皇岛市", "148");
		cityMap.put("沧州市", "149");
		cityMap.put("石家庄市", "150");
		cityMap.put("邯郸市", "151");
		cityMap.put("新乡市", "152");
		cityMap.put("洛阳市", "153");
		cityMap.put("商丘市", "154");
		cityMap.put("许昌市", "155");
		cityMap.put("襄樊市", "156");
		cityMap.put("荆州市", "157");
		cityMap.put("长沙市", "158");
		cityMap.put("衡阳市", "159");
		cityMap.put("镇江市", "160");
		cityMap.put("南通市", "161");
		cityMap.put("淮安市", "162");
		cityMap.put("南昌市", "163");
		cityMap.put("新余市", "164");
		cityMap.put("通化市", "165");
		cityMap.put("锦州市", "166");
		cityMap.put("大连市", "167");
		cityMap.put("乌兰察布市", "168");
		cityMap.put("巴彦淖尔市", "169");
		cityMap.put("渭南市", "170");
		cityMap.put("宝鸡市", "171");
		cityMap.put("枣庄市", "172");
		cityMap.put("日照市", "173");
		cityMap.put("东营市", "174");
		cityMap.put("威海市", "175");
		cityMap.put("太原市", "176");
		cityMap.put("文山壮族苗族自治州", "177");
		cityMap.put("温州市", "178");
		cityMap.put("杭州市", "179");
		cityMap.put("宁波市", "180");
		cityMap.put("中卫市", "181");
		cityMap.put("临夏回族自治州", "182");
		cityMap.put("辽源市", "183");
		cityMap.put("抚顺市", "184");
		cityMap.put("阿坝藏族羌族自治州", "185");
		cityMap.put("宜宾市", "186");
		cityMap.put("中山市", "187");
		cityMap.put("亳州市", "188");
		cityMap.put("滁州市", "189");
		cityMap.put("宣城市", "190");
		cityMap.put("廊坊市", "191");
		cityMap.put("宁德市", "192");
		cityMap.put("龙岩市", "193");
		cityMap.put("厦门市", "194");
		cityMap.put("莆田市", "195");
		cityMap.put("天水市", "196");
		cityMap.put("清远市", "197");
		cityMap.put("湛江市", "198");
		cityMap.put("阳江市", "199");
		cityMap.put("河源市", "200");
		cityMap.put("潮州市", "201");
		cityMap.put("来宾市", "202");
		cityMap.put("百色市", "203");
		cityMap.put("防城港市", "204");
		cityMap.put("铜仁地区", "205");
		cityMap.put("毕节地区", "206");
		cityMap.put("承德市", "207");
		cityMap.put("衡水市", "208");
		cityMap.put("濮阳市", "209");
		cityMap.put("开封市", "210");
		cityMap.put("焦作市", "211");
		cityMap.put("三门峡市", "212");
		cityMap.put("平顶山市", "213");
		cityMap.put("信阳市", "214");
		cityMap.put("鹤壁市", "215");
		cityMap.put("十堰市", "216");
		cityMap.put("荆门市", "217");
		cityMap.put("武汉市", "218");
		cityMap.put("常德市", "219");
		cityMap.put("岳阳市", "220");
		cityMap.put("娄底市", "221");
		cityMap.put("株洲市", "222");
		cityMap.put("盐城市", "223");
		cityMap.put("苏州市", "224");
		cityMap.put("景德镇市", "225");
		cityMap.put("抚州市", "226");
		cityMap.put("本溪市", "227");
		cityMap.put("盘锦市", "228");
		cityMap.put("包头市", "229");
		cityMap.put("阿拉善盟", "230");
		cityMap.put("榆林市", "231");
		cityMap.put("铜川市", "232");
		cityMap.put("西安市", "233");
		cityMap.put("临沂市", "234");
		cityMap.put("滨州市", "235");
		cityMap.put("青岛市", "236");
		cityMap.put("朔州市", "237");
		cityMap.put("晋中市", "238");
		cityMap.put("巴中市", "239");
		cityMap.put("绵阳市", "240");
		cityMap.put("广安市", "241");
		cityMap.put("资阳市", "242");
		cityMap.put("衢州市", "243");
		cityMap.put("台州市", "244");
		cityMap.put("舟山市", "245");
		cityMap.put("固原市", "246");
		cityMap.put("甘南藏族自治州", "247");
		cityMap.put("内江市", "248");
		cityMap.put("曲靖市", "249");
		cityMap.put("淮南市", "250");
		cityMap.put("巢湖市", "251");
		cityMap.put("黄山市", "252");
		cityMap.put("淮北市", "253");
		cityMap.put("三明市", "254");
		cityMap.put("漳州市", "255");
		cityMap.put("陇南市", "256");
		cityMap.put("广州市", "257");
		cityMap.put("云浮市", "258");
		cityMap.put("揭阳市", "259");
		cityMap.put("贺州市", "260");
		cityMap.put("南宁市", "261");
		cityMap.put("遵义市", "262");
		cityMap.put("安顺市", "263");
		cityMap.put("张家口市", "264");
		cityMap.put("唐山市", "265");
		cityMap.put("邢台市", "266");
		cityMap.put("安阳市", "267");
		cityMap.put("郑州市", "268");
		cityMap.put("驻马店市", "269");
		cityMap.put("宜昌市", "270");
		cityMap.put("黄冈市", "271");
		cityMap.put("益阳市", "272");
		cityMap.put("邵阳市", "273");
		cityMap.put("湘西土家族苗族自治州", "274");
		cityMap.put("郴州市", "275");
		cityMap.put("泰州市", "276");
		cityMap.put("宿迁市", "277");
		cityMap.put("宜春市", "278");
		cityMap.put("鹰潭市", "279");
		cityMap.put("朝阳市", "280");
		cityMap.put("营口市", "281");
		cityMap.put("丹东市", "282");
		cityMap.put("鄂尔多斯市", "283");
		cityMap.put("延安市", "284");
		cityMap.put("商洛市", "285");
		cityMap.put("济宁市", "286");
		cityMap.put("潍坊市", "287");
		cityMap.put("济南市", "288");
		cityMap.put("上海市", "289");
		cityMap.put("晋城市", "290");
		cityMap.put("澳门特别行政区", "2911");
		cityMap.put("香港特别行政区", "2912");
		cityMap.put("南充市", "291");
		cityMap.put("丽水市", "292");
		cityMap.put("绍兴市", "293");
		cityMap.put("湖州市", "294");
		cityMap.put("北海市", "295");
		cityMap.put("海南省直辖县级行政单位", "296");
		cityMap.put("赤峰市", "297");
		cityMap.put("六安市", "298");
		cityMap.put("池州市", "299");
		cityMap.put("福州市", "300");
		cityMap.put("惠州市", "301");
		cityMap.put("江门市", "302");
		cityMap.put("汕头市", "303");
		cityMap.put("梧州市", "304");
		cityMap.put("柳州市", "305");
		cityMap.put("黔南布依族苗族自治州", "306");
		cityMap.put("保定市", "307");
		cityMap.put("周口市", "308");
		cityMap.put("南阳市", "309");
		cityMap.put("孝感市", "310");
		cityMap.put("黄石市", "311");
		cityMap.put("张家界市", "312");
		cityMap.put("湘潭市", "313");
		cityMap.put("永州市", "314");
		cityMap.put("南京市", "315");
		cityMap.put("徐州市", "316");
		cityMap.put("无锡市", "317");
		cityMap.put("吉安市", "318");
		cityMap.put("葫芦岛市", "319");
		cityMap.put("鞍山市", "320");
		cityMap.put("呼和浩特市", "321");
		cityMap.put("吴忠市", "322");
		cityMap.put("咸阳市", "323");
		cityMap.put("安康市", "324");
		cityMap.put("泰安市", "325");
		cityMap.put("烟台市", "326");
		cityMap.put("吕梁市", "327");
		cityMap.put("运城市", "328");
		cityMap.put("广元市", "329");
		cityMap.put("遂宁市", "330");
		cityMap.put("泸州市", "331");
		cityMap.put("天津市", "332");
		cityMap.put("金华市", "333");
		cityMap.put("嘉兴市", "334");
		cityMap.put("石嘴山市", "335");
		cityMap.put("昭通市", "336");
		cityMap.put("铜陵市", "337");
		cityMap.put("肇庆市", "338");
		cityMap.put("汕尾市", "339");
		cityMap.put("嘉峪关市", "33");
		cityMap.put("深圳市", "340");
		cityMap.put("贵港市", "341");
		cityMap.put("黔东南苗族侗族自治州", "342");
		cityMap.put("黔西南布依族苗族自治州", "343");
		cityMap.put("漯河市", "344");
		cityMap.put("湖北省直辖县级行政单位", "345");
		cityMap.put("扬州市", "346");
		cityMap.put("连云港市", "347");
		cityMap.put("常州市", "348");
		cityMap.put("九江市", "349");
		cityMap.put("金昌市", "34");
		cityMap.put("萍乡市", "350");
		cityMap.put("辽阳市", "351");
		cityMap.put("汉中市", "352");
		cityMap.put("菏泽市", "353");
		cityMap.put("淄博市", "354");
		cityMap.put("大同市", "355");
		cityMap.put("长治市", "356");
		cityMap.put("阳泉市", "357");
		cityMap.put("马鞍山市", "358");
		cityMap.put("平凉市", "359");
		cityMap.put("白银市", "35");
		cityMap.put("银川市", "360");
		cityMap.put("玉林市", "361");
		cityMap.put("咸宁市", "362");
		cityMap.put("怀化市", "363");
		cityMap.put("上饶市", "364");
		cityMap.put("赣州市", "365");
		cityMap.put("聊城市", "366");
		cityMap.put("忻州市", "367");
		cityMap.put("临汾市", "368");
		cityMap.put("达州市", "369");
		cityMap.put("兰州市", "36");
		cityMap.put("宿州市", "370");
		cityMap.put("随州市", "371");
		cityMap.put("德州市", "372");
		cityMap.put("恩施土家族苗族自治州", "373");
		cityMap.put("酒泉市", "37");
		cityMap.put("大兴安岭地区", "38");
		cityMap.put("黑河市", "39");
		cityMap.put("伊春市", "40");
		cityMap.put("齐齐哈尔市", "41");
		cityMap.put("佳木斯市", "42");
		cityMap.put("鹤岗市", "43");
		cityMap.put("绥化市", "44");
		cityMap.put("双鸭山市", "45");
		cityMap.put("鸡西市", "46");
		cityMap.put("七台河市", "47");
		cityMap.put("哈尔滨市", "48");
		cityMap.put("牡丹江市", "49");
		cityMap.put("大庆市", "50");
		cityMap.put("白城市", "51");
		cityMap.put("松原市", "52");
		cityMap.put("长春市", "53");
		cityMap.put("延边朝鲜族自治州", "54");
		cityMap.put("吉林市", "55");
		cityMap.put("四平市", "56");
		cityMap.put("白山市", "57");
		cityMap.put("沈阳市", "58");
		cityMap.put("阜新市", "59");
		cityMap.put("铁岭市", "60");
		cityMap.put("呼伦贝尔市", "61");
		cityMap.put("兴安盟", "62");
		cityMap.put("锡林郭勒盟", "63");
		cityMap.put("通辽市", "64");
		cityMap.put("海西蒙古族藏族自治州", "65");
		cityMap.put("西宁市", "66");
		cityMap.put("海北藏族自治州", "67");
		cityMap.put("海南藏族自治州", "68");
		cityMap.put("海东地区", "69");
		cityMap.put("黄南藏族自治州", "70");
		cityMap.put("玉树藏族自治州", "71");
		cityMap.put("果洛藏族自治州", "72");
		cityMap.put("甘孜藏族自治州", "73");
		cityMap.put("德阳市", "74");
		cityMap.put("成都市", "75");
		cityMap.put("雅安市", "76");
		cityMap.put("眉山市", "77");
		cityMap.put("自贡市", "78");
		cityMap.put("乐山市", "79");
		cityMap.put("凉山彝族自治州", "80");
		cityMap.put("攀枝花市", "81");
		cityMap.put("和田地区", "82");
		cityMap.put("喀什地区", "83");
		cityMap.put("克孜勒苏柯尔克孜自治州", "84");
		cityMap.put("阿克苏地区", "85");
		cityMap.put("巴音郭楞蒙古自治州", "86");
		cityMap.put("新疆直辖县级行政单位", "87");
		cityMap.put("新疆维吾尔自治区直辖县级行政单位", "87");
		cityMap.put("博尔塔拉蒙古自治州", "88");
		cityMap.put("吐鲁番地区", "89");
		cityMap.put("伊犁哈萨克自治州", "90");
		cityMap.put("哈密地区", "91");
		cityMap.put("乌鲁木齐市", "92");
		cityMap.put("昌吉回族自治州", "93");
		cityMap.put("塔城地区", "94");
		cityMap.put("克拉玛依市", "95");
		cityMap.put("阿勒泰地区", "96");
		cityMap.put("山南地区", "97");
		cityMap.put("林芝地区", "98");
		cityMap.put("昌都地区", "99");

	}

	/**
	 * 
	 * @Title：getAllCityName
	 * @Description：获取百度地图城市名称
	 * @return
	 * @return：List<String> 返回类型
	 */
	public static List<String> getAllCityName() {
		List<String> cityNameList = new ArrayList<String>();
		for (Map.Entry<String, String> entry : cityMap.entrySet()) {
			cityNameList.add(entry.getKey());
		}
		Collections.sort(cityNameList);
		return cityNameList;
	}

	/**
	 * 
	 * @Title：getAllcityCode
	 * @Description：获取百度地图城市代码
	 * @return
	 * @return：List<String> 返回类型
	 */
	public static List<String> getAllcityCode() {
		List<String> cityCodeList = new ArrayList<String>();

		for (String cityCode : cityMap.values()) {
			cityCodeList.add(cityCode);
		}
		Collections.sort(cityCodeList);
		return cityCodeList;
	}

	/**
	 * 
	 * @Title：findCityNameByCode
	 * @Description： <ul>
	 *               <li>通过cityCode查询cityName</li>
	 *               <li>citycode存在重复值，故此cityName可能会有多值</li>
	 *               </ul>
	 * @param cityCode
	 * @return
	 * @return：List<String> 返回类型
	 */
	public static List<String> findCityNameByCode(String cityCode) {
		List<String> cityNameList = new ArrayList<String>();
		Set<Entry<String, String>> sets = cityMap.entrySet();
		Iterator<Entry<String, String>> is = sets.iterator();
		while (is.hasNext()) {
			Entry<String, String> entry = is.next();
			if (entry.getValue().equals(cityCode)) {
				cityNameList.add(entry.getKey());
			}
		}
		return cityNameList;
	}

	/**
	 * 
	 * @Title：findCityCodeByName
	 * @Description：通过cityName查询cityCode
	 * @param cityName
	 * @return
	 * @return：String 返回类型
	 */
	public static String findCityCodeByName(String cityName) {
		return cityMap.get(cityName);
	}
	

}
