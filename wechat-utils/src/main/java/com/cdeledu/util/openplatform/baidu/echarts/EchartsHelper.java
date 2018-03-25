package com.cdeledu.util.openplatform.baidu.echarts;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.SplitLine;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.feature.DataView;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.feature.Mark;
import com.github.abel533.echarts.feature.SaveAsImage;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.MarkLine;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 生成百度Echarts帮助类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月23日 下午3:15:44
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class EchartsHelper {
	/**
	 * @方法描述: 创建折线图
	 * @param title
	 *            标题
	 * @param subtext
	 *            标题
	 * @param isHorizontal
	 *            布局方式，默认为水平布局
	 * @param dataViewMOdel
	 *            是否开启数据视图
	 * @param saveFlag
	 *            是否允许保存图片
	 * @param axisEntity
	 *            坐标实体类
	 * @param chartDataList
	 *            图表数据
	 * @return
	 */
	public static Option getlineChart(Title title, boolean isHorizontal, boolean dataViewMOdel,
			boolean saveFlag, AxisEntity axisEntity, List<ChartsData> chartDataList) {

		GsonOption option = new GsonOption();

		/**
		 * 标题，每个图表最多仅有一个标题控件，每个标题控件可设主副标题/
		 */
		option.title(title);
		/**
		 * 提示框，鼠标悬浮交互时的信息提示
		 */
		option.tooltip().trigger(Trigger.axis);

		/**
		 * 工具栏
		 */
		Toolbox toolbox = option.toolbox();
		toolbox.show(true);
		if (isHorizontal) {
			toolbox.orient(Orient.horizontal);
		} else {
			toolbox.orient(Orient.vertical);
		}
		Mark mark = new Mark();
		mark.show(false);
		DataView dataView = new DataView();
		dataView.show(dataViewMOdel);
		dataView.readOnly(false);
		SaveAsImage saveAsImage = new SaveAsImage();
		saveAsImage.show(saveFlag);
		MagicType magicType = new MagicType(Magic.line, Magic.bar);
		toolbox.feature(mark, dataView, magicType, Tool.restore, saveAsImage);

		/**
		 * 纵坐标
		 */
		ValueAxis valueAxis = new ValueAxis();
		if (StringUtils.isNotBlank(axisEntity.getyAxisFormatter())) {
			valueAxis.axisLabel().formatter("{value} " + axisEntity.getyAxisFormatter());
		} else {
			valueAxis.axisLabel().formatter("{value}");
		}
		if (StringUtils.isNotBlank(axisEntity.getyAxisName())) {
			valueAxis.name(axisEntity.getyAxisName());
		}
		option.yAxis(valueAxis);

		/**
		 * 横坐标
		 */
		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.boundaryGap(false);
		if (StringUtils.isNotBlank(axisEntity.getxAxisName())) {
			categoryAxis.name(axisEntity.getxAxisName());
		}
		categoryAxis.data(axisEntity.getxData().toArray());
		categoryAxis.splitLine(new SplitLine().show(true));
		option.xAxis(categoryAxis);

		/**
		 * 图表生成的数据内容数组
		 */

		MarkLine markLine = new MarkLine();
		Map<String, Object> lineMap = Maps.newConcurrentMap();
		lineMap.put("type", axisEntity.getDataFilter());
		markLine.data(lineMap);
		markLine.effect().show(true);
		Line line = null;
		List<String> legendList = Lists.newArrayList();
		for (ChartsData chartData : chartDataList) {
			line = new Line();
			legendList.add(chartData.getName());
			line.name(chartData.getName());
			line.type(SeriesType.line);
			line.setData(chartData.getData());
			if (axisEntity.isLineShowState()) {
				line.markLine(markLine);
			}
			option.series(line);
		}
		/**
		 * 图例，每个图表最多仅有一个图例,且居中底部显示，显示边框
		 */
		option.legend().data(legendList).x(X.center).y(Y.bottom).borderWidth(1);
		return option;
	}

	/**
	 * @方法描述: 创建柱状图/柱状堆积
	 * @return
	 */
	public static Option getBarChart() {
		return new GsonOption();
	}

	/**
	 * @方法描述: 创建散点图
	 * @return
	 */
	public static Option getScatterChart() {
		return new GsonOption();
	}

	/**
	 * @方法描述: 创建K图
	 * @return
	 */
	public static Option getKChart() {
		return new GsonOption();
	}

	/**
	 * @方法描述: 创建饼图
	 * @return
	 */
	public static Option getPieChart() {
		return new GsonOption();
	}

	/**
	 * @方法描述: 创建雷达图
	 * @return
	 */
	public static Option getRadarChart() {
		return new GsonOption();
	}

	/**
	 * @方法描述: 和弦图
	 * @return
	 */
	public static Option getChordChart() {
		return new GsonOption();
	}

	/**
	 * @方法描述: 创建仪表盘
	 * @return
	 */
	public static Option getGaugeChart() {
		return new GsonOption();
	}

	/**
	 * @方法描述: 创建漏斗图
	 * @return
	 */
	public static Option getFunnelChart() {
		return new GsonOption();
	}

	/**
	 * @方法描述: 创建力导向布局图
	 * @return
	 */
	public static Option getForceChart() {
		return new GsonOption();
	}
}
