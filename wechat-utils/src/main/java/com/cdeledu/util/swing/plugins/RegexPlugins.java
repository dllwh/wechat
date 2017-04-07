package com.cdeledu.util.swing.plugins;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.property.PropertyHelper;
import com.cdeledu.util.swing.services.GuiJPanel;
import com.cdeledu.util.swing.util.GuiUtils;

/**
 * @ClassName: RegexTester
 * @Description: 正则表达式
 * @author: 独泪了无痕
 * @date: 2015年9月21日 下午4:29:00
 * @version: V1.0
 * @history:
 */
public class RegexPlugins extends GuiJPanel {
	private static final long serialVersionUID = 1L;
	/** -------------------------- 属性 begin ------------------------------- */
	/** 正则表达式文本域 */
	private JTextArea regexTextArea = createJTextArea(GuiUtils.font14_cn);
	/** 验证字符文本域 */
	private JTextArea sourceTextArea = createJTextArea(GuiUtils.font14_cn);
	/** 匹配结果文本域 */
	private JTextArea matchTextArea = createJTextArea(GuiUtils.font14_cn);
	/** 替换匹配字符文本域 */
	private JTextField replaceTextField = new JTextField("");
	/** 帮助文本 */
	private String helpText = "";
	/** 常用正则表达式-名称 */
	private String[] expNames = null;
	/** 常用正则表达式-表达式 */
	private Map<String, String> expsMap = new HashMap<String, String>();

	{
		/**
		 * 正则帮助
		 */
		String helpPropsFile = GuiUtils.getActualPath("properties/expression/help.txt");
		InputStream helpInputStream = null;
		try {
			helpInputStream = new FileInputStream(helpPropsFile);
			int count = 0;
			while (count == 0) {
				count = helpInputStream.available();
			}
			byte[] bytes = new byte[count];
			helpInputStream.read(bytes);
			helpText = new String(bytes, "UTF-8");
		} catch (Exception e) {
			GuiUtils.logLoadPropertiesException(helpPropsFile, e);
		} finally {
			IOUtils.closeQuietly(helpInputStream);
		}

		/**
		 * 常用正则表达式
		 */
		Map<String, String> expressionsMap = PropertyHelper
				.getMapByProperties("properties/expression/expression.properties");

		List<String> expsSortAndNamesList = new ArrayList<String>();
		for (String key : expressionsMap.keySet()) {
			expsSortAndNamesList.add(key);
		}
		Collections.sort(expsSortAndNamesList);// 排序

		int expSize = expsSortAndNamesList.size() + 1;
		expNames = new String[expSize];
		expNames[0] = "常用正则表达式";

		for (int i = 1; i < expSize; i++) {
			String key = expsSortAndNamesList.get(i - 1);
			String[] keySplit = key.split("_");
			// 避免表达式名字存在多个"_"的情况
			expNames[i] = keySplit.length > 1 ? key.substring(keySplit[0].length() + 1) : keySplit[0];
			expsMap.put(expNames[i], expressionsMap.get(key));
		}
	}

	/** 是否忽略大小写，默认否 */
	private boolean ignoreCase = false;
	/** 是否显示详细匹配，默认否 */
	private boolean viewDetail = false;

	/** -------------------------- 属性 end ------------------------------- */
	/** -------------------------- 私有方法 begin ------------------------------- */

	/** -------------------------- 私有方法 end ------------------------------- */
	/** -------------------------- 公有方法 begin ------------------------------- */
	public RegexPlugins() {
		/**
		 * 1. 主面板：边界布局，只有Center部分
		 */
		setLayout(new BorderLayout());
		/**
		 * 2. Center:输出域，使用3行1列的Grid布局,使其平均显示
		 */
		JPanel textAreaPanel = new JPanel(new GridLayout(3, 1));
		add(textAreaPanel, BorderLayout.CENTER);

		// 2.1界面之匹配正则
		JPanel regexPanel = new JPanel(new BorderLayout());
		addJLabel(regexPanel, "匹配正则:", GuiUtils.font14b_cn, BorderLayout.WEST);
		regexPanel.add(new JScrollPane(regexTextArea), BorderLayout.CENTER);
		textAreaPanel.add(regexPanel);
		// 2.2 界面之匹配文本
		JPanel sourcePanel = new JPanel(new BorderLayout());
		addJLabel(sourcePanel, "匹配文本:", GuiUtils.font14b_cn, BorderLayout.WEST);
		sourcePanel.add(new JScrollPane(sourceTextArea), BorderLayout.CENTER);
		textAreaPanel.add(sourcePanel);
		// 2.3 界面之匹配结果
		JPanel matchPanel = new JPanel(new BorderLayout());
		addJLabel(matchPanel, "匹配结果:", GuiUtils.font14b_cn, BorderLayout.WEST);
		matchPanel.add(new JScrollPane(matchTextArea), BorderLayout.CENTER);
		textAreaPanel.add(matchPanel);

		/**
		 * 3. East:操作区域，使用BorderLayout布局
		 */
		final JPanel actionPanel = new JPanel(new BorderLayout());
		add(actionPanel, BorderLayout.EAST);
		JPanel actionGridPanel = new JPanel(new GridLayout(10, 1));
		actionPanel.add(actionGridPanel);
		// 3.1 帮助按钮
		JPanel helpButtonPanel = new JPanel(new GridLayout(1, 2));
		helpButtonPanel.add(new Panel());// 仅作填充
		addJButton(helpButtonPanel, "帮助", "", GuiUtils.font13_cn, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTextAreaMessage(helpText, "帮助说明", JOptionPane.INFORMATION_MESSAGE, null, null);
			}
		});
		actionGridPanel.add(helpButtonPanel);
		// 3.2 常用的正则表达式下拉框
		JPanel expressionsBoxPanel = new JPanel(new BorderLayout());
		addJLabel(expressionsBoxPanel, "  ", GuiUtils.font14_cn, BorderLayout.WEST);

		if (ArrayUtils.isNotEmpty(expNames)) {
			// 仅作填充
			actionGridPanel.add(new JLabel(""));

			addJComboBox(expressionsBoxPanel, expNames, GuiUtils.font13_cn, new ActionListener() {

				public void actionPerformed(ActionEvent event) {
					@SuppressWarnings("unchecked")
					String exp = expsMap.get(((JComboBox<String>) event.getSource()).getSelectedItem().toString());
					if (StringUtils.isNoneBlank(exp))
						regexTextArea.setText(exp);
				}
			}, BorderLayout.CENTER);
		}
		actionGridPanel.add(expressionsBoxPanel);

		// 仅作填充
		actionGridPanel.add(new JLabel(""));

		// 3.3.1 是否忽略大小写
		JPanel ignoreCasePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		addJCheckBox(ignoreCasePanel, "忽略大小写", false, GuiUtils.font14_cn, new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				JCheckBox checkBox = (JCheckBox) event.getSource();
				ignoreCase = checkBox.isSelected();
			}
		});
		actionGridPanel.add(ignoreCasePanel);

		// 3.3.2 是否显示详细匹配
		JPanel viewDetailPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		addJCheckBox(viewDetailPanel, "显示详细匹配", false, GuiUtils.font14_cn, new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				JCheckBox checkBox = (JCheckBox) event.getSource();
				viewDetail = checkBox.isSelected();
			}
		});
		actionGridPanel.add(viewDetailPanel);

		// 仅作填充
		actionGridPanel.add(new JLabel(""));

		// 替换匹配
		JPanel replaceLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		addJLabel(replaceLabelPanel, " 替换匹配: ", GuiUtils.font14_cn);
		actionGridPanel.add(replaceLabelPanel);
		JPanel replaceTextPanel = new JPanel(new BorderLayout());
		addJLabel(replaceTextPanel, "  ", GuiUtils.font14_cn, BorderLayout.WEST);
		addJTextField(replaceTextPanel, replaceTextField, GuiUtils.font14_un, BorderLayout.CENTER);
		actionGridPanel.add(replaceTextPanel);

		// 仅作填充
		actionGridPanel.add(new JLabel(""));

		// "匹配"按钮
		JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
		addJButton(buttonPanel, "匹配", "", GuiUtils.font14b_cn, new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				String regexText = regexTextArea.getText().trim();
				String sourceText = sourceTextArea.getText().trim();
				String replaceText = replaceTextField.getText();

				Pattern pattern = null;
				if (ignoreCase) {
					// 不区分大小写
					pattern = Pattern.compile(regexText, Pattern.CASE_INSENSITIVE);
				} else {
					pattern = Pattern.compile(regexText);
				}
				// 用Pattern类的matcher()方法生成一个Matcher对象
				Matcher matcher = pattern.matcher(sourceText);

				// 结果:需要显示在匹配结果区域的字段
				StringBuffer sb = new StringBuffer();
				StringBuffer rsb = new StringBuffer(); // 替换匹配

				// 使用find()方法查找第一个匹配的对象
				boolean result = matcher.find();
				int cnt = 0;// 匹配总数
				int start = 0;
				int end = 0;
				while (result) {
					matcher.appendReplacement(rsb, replaceText); // 替换匹配
					cnt++;
					sb.append("\n");
					start = matcher.start();
					end = matcher.end();
					String matchText = sourceText.substring(start, end);
					if (viewDetail) {
						sb.append("Match[").append(cnt).append("]: ");
					}
					sb.append(matchText);
					if (viewDetail) {
						sb.append(" [start: " + start);
						sb.append(", end: " + end);
						sb.append("]");
					}
					result = matcher.find();
				}
				sb.append("\n\n匹配总数: " + cnt);
				if (replaceText.length() != 0) {
					matcher.appendTail(rsb);
					sb.append("\n\n替换匹配: ").append(rsb);
				}

				matchTextArea.setText(sb.length() > 0 ? sb.substring(1) : "");
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}
		});
		actionPanel.add(buttonPanel, BorderLayout.SOUTH);
	}
	/** -------------------------- 公有方法 end ------------------------------- */
}
