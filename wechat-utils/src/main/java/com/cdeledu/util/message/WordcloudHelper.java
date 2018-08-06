package com.cdeledu.util.message;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.LinearGradientColorPalette;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 词云
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年8月3日 下午2:25:50
 * @版本: V1.0
 * @since: JDK 1.8
 */
public final class WordcloudHelper {
	/** ----------------------------------------------------- Fields start */

	/**
	 * ----------------------------------------------------- Fields end
	 * 
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {
		// 建立词频分析器，设置词频，以及词语最短长度，此处的参数配置视情况而定即可
		FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
		frequencyAnalyzer.setWordFrequenciesToReturn(600);
		frequencyAnalyzer.setMinWordLength(2);

		// 这边要注意,需要引入中文解析器
		frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());
		// 指定文本文件路径，生成词频集合
		final List<WordFrequency> wordFrequencyList = frequencyAnalyzer.load("C://Users/dell/Desktop/1.txt");
		// 设置图片分辨率
		Dimension dimension = new Dimension(1920, 1080);
		// 此处的设置采用内置常量即可，生成词云对象
		WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
		// 设置图片相关的属性,这边是大小和形状,更多的形状属性,可以从CollisionMode源码里面查找
		wordCloud.setPadding(2);
		// 这边要注意意思,是设置中文字体的,如果不设置,得到的将会是乱码,
		Font font = new Font("STSong-Light", 2, 20);
		// 设置词云显示的三种颜色，越靠前设置表示词频越高的词语的颜色
		wordCloud.setColorPalette(new LinearGradientColorPalette(Color.RED, Color.BLUE, Color.GREEN, 30, 30));
		wordCloud.setKumoFont(new KumoFont(font));
		// 设置背景色
		wordCloud.setBackgroundColor(new Color(255, 255, 255));
		// 设置背景图片
		// wordCloud.setBackground(new PixelBoundryBackground("E:\\爬虫/google.jpg"));
		// 设置背景图层为圆形
	 	wordCloud.setBackground(new CircleBackground(255));
		wordCloud.setFontScalar(new SqrtFontScalar(12, 45));
		//将文字写入图片
		wordCloud.build(wordFrequencyList);
		// 生成词云
		wordCloud.writeToFile("C://Users/dell/Desktop/wy.png");
	}
}
