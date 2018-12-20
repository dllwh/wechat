package com.cdeledu.template.codeMaker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.template.codeMaker.config.CodeMakerUtil;
import com.cdeledu.template.codeMaker.config.Column;
import com.cdeledu.template.codeMaker.config.Configuration;
import com.cdeledu.template.codeMaker.config.MyBatisType;
import com.cdeledu.template.codeMaker.config.Table;
import com.cdeledu.util.apache.collection.ListUtilHelper;
import com.cdeledu.util.apache.lang.DateUtilHelper;
import com.cdeledu.util.application.regex.RegexUtil;
import com.google.common.collect.Lists;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:代码生成器
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2017年9月14日 下午10:03:43
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CodeHelper {
	/** ----------------------------------------------------- Fields start */
	private static JFrame frame = new JFrame("代码生成器");

	/**
	 * SQLSERVER
	 */
	
//	private static JTextField driverField = new JTextField("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//	private static JTextField urlField = new JTextField("");
//	private static JTextField usernameField = new JTextField("");
//	private static JTextField passwordField = new JTextField("");
//	private static JTextField tableField = new JTextField("chat_client");
	
	/**
	 * Mysql
	 */
	private static JTextField driverField = new JTextField("com.mysql.jdbc.Driver");
	private static JTextField urlField = new JTextField("jdbc:mysql://127.0.0.1:3306/db_wechat?useUnicode=true&characterEncoding=UTF8");
	private static JTextField usernameField = new JTextField("root");
	private static JTextField passwordField = new JTextField("");
	private static JTextField tableField = new JTextField("cms_manageruser");
	
	
	private static JTextField packageField = new JTextField("com.cdeledu");

	private static JButton codeButton = new JButton(" 生成代码 ");
	private static JButton connButton = new JButton(" 连接测试 ");

	private static JTextArea beanText = new JTextArea();
	private static JTextArea mybatisText = new JTextArea();
	private static JTextArea serviceText = new JTextArea();
	private static JTextArea serviceImplText = new JTextArea();
	private static JTextArea daoText = new JTextArea();

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:创建UI
	 * @创建人:独泪了无痕
	 */
	public static void createView() {
		final JTabbedPane tab = new JTabbedPane();
		JPanel topPanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(topPanel, BoxLayout.Y_AXIS);
		topPanel.setLayout(boxLayout);

		topPanel.add(getFieldByText("驱动", driverField));
		topPanel.add(getFieldByText("URL", urlField));
		topPanel.add(getFieldByText("用户名", usernameField));
		topPanel.add(getFieldByText("密码", passwordField));
		topPanel.add(getFieldByText("数据库表", tableField));
		topPanel.add(getFieldByText("基础路径", packageField));
		topPanel.add(getFieldSpecial("操作"));
		topPanel.setPreferredSize(new Dimension(0, 270));

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.add(tab, BorderLayout.CENTER);
		JScrollPane beanScroll = setTextAreaByTab(beanText);
		final JScrollBar beanScrollBar = beanScroll.getVerticalScrollBar();
		beanScrollBar.setUnitIncrement(100);

		codeButton.setEnabled(false);
		// 测试链接
		connButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				try {
					if (checkParam() && getConnection()) {
						showMessageDialog("连接成功", JOptionPane.INFORMATION_MESSAGE);
						setEditable(false);
						codeButton.setEnabled(true);
						// 生成代码
						codeButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent event) {
								try {
									createCode();
								} catch (Exception e) {
									e.printStackTrace();
									showMessageDialog(e.getMessage(), JOptionPane.ERROR_MESSAGE);
								} finally {
									tab.setSelectedIndex(0);
									beanScrollBar.setValue(beanScrollBar.getMinimum());
								}
							}
						});
					} else {
						showMessageDialog("无法连接，请检查参数配置是否正常", JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					tab.setSelectedIndex(0);
					beanScrollBar.setValue(beanScrollBar.getMinimum());
				}
			}
		});

		mybatisText.setBorder(BorderFactory.createEtchedBorder());
		beanText.setBorder(BorderFactory.createEtchedBorder());

		tab.addTab("Bean 代码", beanScroll);
		tab.addTab("Service 接口", setTextAreaByTab(serviceText));
		tab.addTab("Service 实现", setTextAreaByTab(serviceImplText));
		tab.addTab("Dao 实现", setTextAreaByTab(daoText));
		tab.addTab("MyBatis 配置", setTextAreaByTab(mybatisText));

		frame.setVisible(true); // 使窗体可视
		frame.setSize(800, 700);// 设置窗体大小
		frame.setLayout(new BorderLayout());
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 设置窗体全屏显示

	}

	/**
	 * @方法描述: 取得字段
	 * @param title
	 * @param c
	 * @return 标签 + 输入框
	 */
	private static JPanel getFieldByText(String title, JComponent c) {
		JPanel tr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel(title);
		label.setPreferredSize(new Dimension(80, 30));
		tr.add(label);
		tr.add(c);
		c.setPreferredSize(new Dimension(679, 30));
		return tr;
	}

	/**
	 * @方法描述: 取得字段
	 * @param title
	 * @param c
	 * @return 标签 + 按钮
	 */
	private static JPanel getFieldSpecial(String title) {
		JPanel tr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel(title);
		label.setPreferredSize(new Dimension(80, 30));
		tr.add(label);
		JRadioButton daoRandioButton = new JRadioButton("mysql");
		daoRandioButton.setSelected(true);
		daoRandioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Configuration.setCodeTemplateType(MyBatisType.mysql);
			}
		});
		JRadioButton mapperRandioButton = new JRadioButton("sqlserver");
		mapperRandioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Configuration.setCodeTemplateType(MyBatisType.sqlserver);
			}
		});
		JRadioButton pageRandioButton = new JRadioButton("分页");
		pageRandioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Configuration.setPageFlage(true);
			}
		});
		JRadioButton noPageButton = new JRadioButton("不分页");
		noPageButton.setSelected(true);
		noPageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Configuration.setPageFlage(false);
			}
		});
		ButtonGroup group = new ButtonGroup();
		group.add(daoRandioButton);
		group.add(mapperRandioButton);
		ButtonGroup group1 = new ButtonGroup();
		group1.add(pageRandioButton);
		group1.add(noPageButton);
		tr.add(daoRandioButton);
		tr.add(mapperRandioButton);
		tr.add(pageRandioButton);
		tr.add(noPageButton);
		connButton.setPreferredSize(new Dimension(150, 30));
		tr.add(connButton);
		codeButton.setPreferredSize(new Dimension(150, 30));
		tr.add(codeButton);
		return tr;
	}

	/**
	 * @方法描述: 设置文本区
	 * @param jta
	 * @return
	 */
	private static JScrollPane setTextAreaByTab(JTextArea jta) {
		jta.setLineWrap(true);// 激活自动换行功能
		jta.setWrapStyleWord(true); // 激活断行不断字功能
		jta.setEditable(false);// 设置不可编辑
		jta.setTabSize(6);
		return new JScrollPane(jta);
	}

	/**
	 * @方法描述: 检查参数(数据库密码初始时可能为空)
	 * @return
	 */
	private static boolean checkParam() {
		if (StringUtils.isNoneBlank(driverField.getText())
				&& StringUtils.isNoneBlank(urlField.getText())
				&& StringUtils.isNoneBlank(usernameField.getText())
				&& StringUtils.isNoneBlank(tableField.getText())
				&& StringUtils.isNoneBlank(packageField.getText())) {
			return true;
		}
		return false;
	}
	private static void setEditable(boolean flag) {
		driverField.setEditable(flag);
		urlField.setEditable(flag);
		usernameField.setEditable(flag);
		passwordField.setEditable(flag);
		tableField.setEditable(flag);
		packageField.setEditable(flag);
	}

	/**
	 * @方法描述: 检测参数
	 * @return
	 * @throws Exception
	 */
	private static boolean getConnection() throws Exception {
		Connection conn = null;
		try {
			Class.forName(driverField.getText());
			conn = DriverManager.getConnection(urlField.getText(), usernameField.getText(),
					passwordField.getText());
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * @方法:生成代码
	 * @创建人:独泪了无痕
	 * @throws Exception
	 */
	private static void createCode() throws Exception {

		List<Column> columns = getColumns(tableField.getText());
		Table table = getTable(tableField.getText());
		String packages = packageField.getText();
		String mybatisCode = "";
		String domainCode = "";
		String serviceCode = "";
		String serviceImplCode = "";
		String daoCode = "";

		if (ListUtilHelper.EMPTY_LIST != columns) {
			domainCode = getBeanCode(table, packages, columns);
			serviceCode = getServiceCode(table, packages);
			serviceImplCode = getServiceImplCode(table, packages);
			daoCode = getDaoCode(table, packages);
			mybatisCode = getMyBatisCode(table, packages, columns);
		}

		mybatisText.setText(mybatisCode);
		beanText.setText(domainCode);
		serviceText.setText(serviceCode);
		serviceImplText.setText(serviceImplCode);
		daoText.setText(daoCode);
	}

	/**
	 * @方法:生成实体代码
	 * @创建人:独泪了无痕
	 * @param table
	 * @param pack
	 * @param columns
	 * @return
	 * @throws Exception
	 */
	private static String getBeanCode(Table table, String pack, List<Column> columns)
			throws Exception {
		String xml = CodeMakerUtil.read(Configuration.getBeanTemplateLocation());
		long serialVersionUID = new Random().nextLong();
		serialVersionUID = serialVersionUID < 0 ? -serialVersionUID : serialVersionUID;

		String classTemplate = RegexUtil.getKeyWords("<class>([\\w\\W]+?)</class>", xml, 1);
		String fieldTemplate = RegexUtil.getKeyWords("<field>([\\w\\W]+?)</field>", xml, 1);
		String methodTemplate = RegexUtil.getKeyWords("<method>([\\w\\W]+?)</method>", xml, 1);
		String importTemplate = RegexUtil.getKeyWords("<import>([\\w\\W]+?)</import>", xml, 1);
		String className = CodeMakerUtil
				.firstCharUpperCase(CodeMakerUtil.toFieldName(table.getName()));

		StringBuilder fields = new StringBuilder();
		Map<String, String> fieldMap = null;
		for (Column column : columns) {
			String template = fieldTemplate;
			fieldMap = new LinkedHashMap<String, String>();
			// fieldMap.put("field.name", column.getField());// 转换成小写
			fieldMap.put("field.name", column.getName());// 原样输出
			fieldMap.put("field.length", String.valueOf(column.getLength()));
			fieldMap.put("field.nullable", String.valueOf(column.isNullable()));
			fieldMap.put("field.desc", String.valueOf(column.getDesc()));
			fieldMap.put("field.type", column.getFieldType());
			for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
				template = template.replace("#" + entry.getKey() + "#", entry.getValue());
			}
			fields.append(template);
		}

		StringBuilder imports = new StringBuilder();
		Set<String> fieldSet = new HashSet<String>();
		for (Column c : columns) {
			if (c.getImport() != null) {
				fieldSet.add(c.getImport());
			}
		}
		for (String field : fieldSet) {
			String template = importTemplate;
			template = template.replace("#import#", field);
			imports.append(template + "\n");
		}

		StringBuilder methods = new StringBuilder();
		for (Column c : columns) {
			String template = methodTemplate;
			fieldMap = new LinkedHashMap<String, String>();
			fieldMap.put("method.get", "get" + CodeMakerUtil.firstCharUpperCase(c.getField()));
			fieldMap.put("method.set", "set" + CodeMakerUtil.firstCharUpperCase(c.getField()));
			// fieldMap.put("field.name", c.getField());
			fieldMap.put("field.name", c.getName());
			fieldMap.put("field.desc",
					StringUtils.isEmpty(c.getDesc()) ? c.getField() : String.valueOf(c.getDesc()));
			fieldMap.put("field.type", c.getFieldType());
			for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
				template = template.replace("#" + entry.getKey() + "#", entry.getValue());
			}
			methods.append(template);
		}

		Map<String, String> classMap = new LinkedHashMap<String, String>();
		classMap.put("class.package", pack);
		classMap.put("imports", imports.toString());
		classMap.put("table.desc", table.getDesc());
		classMap.put("table.name", table.getName());
		classMap.put("class.name", className);
		classMap.put("serialVersionUID", String.valueOf(serialVersionUID));
		classMap.put("fields", fields.toString());
		classMap.put("methods", methods.toString());
		classMap.put("now", DateUtilHelper.getCurrentTime());
		for (Map.Entry<String, String> entry : classMap.entrySet()) {
			classTemplate = classTemplate.replace("#" + entry.getKey() + "#", entry.getValue());
		}
		return classTemplate;
	}

	/**
	 * @方法:生成Service代码
	 * @创建人:独泪了无痕
	 * @param table
	 * @param pack
	 * @return
	 * @throws Exception
	 */
	private static String getServiceCode(Table table, String pack) throws Exception {
		String xml = CodeMakerUtil.read(Configuration.getServiceTemplateLocation());
		// 匹配模式是非贪婪的。非贪婪模式尽可能少的匹配所搜索的字符串，而默认的贪婪模式则尽可能多的匹配所搜索的字符串。
		String serviceTemplate = RegexUtil.getKeyWords("<class>([\\w\\W]+?)</class>",xml,  1);
		String className =  CodeMakerUtil.firstCharUpperCase(CodeMakerUtil.toFieldName(table.getName()));
		Map<String, String> classMap = new LinkedHashMap<String, String>();
		classMap.put("class.package", pack);
		classMap.put("class.name", className);
		classMap.put("table.name", table.getName());
		classMap.put("table.desc", table.getDesc());
		classMap.put("now", DateUtilHelper.getCurrentTime());
		for (Map.Entry<String, String> entry : classMap.entrySet()) {
			serviceTemplate = serviceTemplate.replace("#" + entry.getKey() + "#", entry.getValue());
		}
		
		return serviceTemplate;
	}

	/**
	 * @方法:生成ServiceImpl代码
	 * @创建人:独泪了无痕
	 * @param table
	 * @param pack
	 * @return
	 * @throws Exception
	 */
	private static String getServiceImplCode(Table table, String pack) throws Exception {
		String xml = CodeMakerUtil.read(Configuration.getServiceImplTemplateLocation());
		// 匹配模式是非贪婪的。非贪婪模式尽可能少的匹配所搜索的字符串，而默认的贪婪模式则尽可能多的匹配所搜索的字符串。
		String serviceImplTemplate = RegexUtil.getKeyWords("<class>([\\w\\W]+?)</class>",xml,  1);
		String className =  CodeMakerUtil.firstCharUpperCase(CodeMakerUtil.toFieldName(table.getName()));
		Map<String, String> classMap = new LinkedHashMap<String, String>();
		classMap.put("class.package", pack);
		classMap.put("class.name", className);
		classMap.put("table.name", table.getName());
		classMap.put("table.desc", table.getDesc());
		classMap.put("now", DateUtilHelper.getCurrentTime());
		for (Map.Entry<String, String> entry : classMap.entrySet()) {
			serviceImplTemplate = serviceImplTemplate.replace("#" + entry.getKey() + "#", entry.getValue());
		}
		return serviceImplTemplate.toString();
	}

	/**
	 * @方法:生成Dao代码
	 * @创建人:独泪了无痕
	 * @param table
	 * @param pack
	 * @return
	 * @throws Exception
	 */
	private static String getDaoCode(Table table, String pack) throws Exception {
		String xml = CodeMakerUtil.read(Configuration.getDaoTemplateLocation());
		// 匹配模式是非贪婪的。非贪婪模式尽可能少的匹配所搜索的字符串，而默认的贪婪模式则尽可能多的匹配所搜索的字符串。
		String daoTemplate = RegexUtil.getKeyWords("<class>([\\w\\W]+?)</class>",xml,  1);
		String className =  CodeMakerUtil.firstCharUpperCase(CodeMakerUtil.toFieldName(table.getName()));
		
		Map<String, String> classMap = new LinkedHashMap<String, String>();
		classMap.put("table.name", table.getName());
		classMap.put("table.desc", table.getDesc());
		classMap.put("class.name", className);
		classMap.put("class.package", pack);
		classMap.put("now", DateUtilHelper.getCurrentTime());
		
		for (Map.Entry<String, String> entry : classMap.entrySet()) {
			daoTemplate = daoTemplate.replace("#" + entry.getKey() + "#", entry.getValue());
		}
		return daoTemplate.toString();
	}

	/**
	 * @方法:生成MyBatis代码
	 * @创建人:独泪了无痕
	 * @param table
	 * @param pack
	 * @param columns
	 * @return
	 * @throws Exception
	 */
	private static String getMyBatisCode(Table table, String pack, List<Column> columns)
			throws Exception {
		String xml = CodeMakerUtil.read(Configuration.getMybatisTemplateLocation());
		StringBuilder sb = new StringBuilder();
		String headTemplate =RegexUtil.getKeyWords("<head>([\\w\\W]+?)</head>",xml, 1);
		sb.append(headTemplate);
		String className =  CodeMakerUtil.firstCharUpperCase(CodeMakerUtil.toFieldName(table.getName()));
		
		String mapperTemplate =RegexUtil.getKeyWords("(<mapper[\\w\\W]+?</mapper>)",xml,  1);
		
		String useuseGeneratedKeyTemplate = RegexUtil.getKeyWords("<useuseGeneratedKeys>([\\w\\W]+?)</useuseGeneratedKeys>",xml,  1);
		String ifTemplate = RegexUtil.getKeyWords("<ifEntry>([\\w\\W]+?)</ifEntry>",xml,  1);
		String valueTemplate = RegexUtil.getKeyWords("<valueEntry>([\\w\\W]+?)</valueEntry>",xml,  1);
		
		List<String> idCols = new ArrayList<String>();
		List<String> idVals = new ArrayList<String>();
		List<String> excludeIdCols = new ArrayList<String>();
		
		StringBuilder useuseGeneratedKeys = new StringBuilder();
		
		for (int i = 0; i < columns.size(); i++) {
			Column colum = columns.get(i);
			if(colum.IsPrikey()){
				idCols.add(colum.getName());
				idVals.add(valueTemplate.replace("#value#", colum.getName()));
				if (colum.IsAutoIncrement()) {
					useuseGeneratedKeys.append(useuseGeneratedKeyTemplate.replace("#" + "id" + "#", colum.getField()));
				}
			} else {
				excludeIdCols.add(colum.getName());
			}
		}
		
		List<String> andIfEntrys = new ArrayList<String>();
		List<String> commaIfEntrys = new ArrayList<String>();
		Map<String, String> map = null;
		for (Column column : columns) {
			map = new LinkedHashMap<String, String>();
			map.put("EntryKey", column.getName());
			map.put("EntryValue", column.getName());
			map.put("preJoiner", "AND");
			map.put("sufJoiner", " ");
			String template = ifTemplate;
			for (Map.Entry<String, String> entry : map.entrySet()) {
				template = template.replace("#" + entry.getKey() + "#",  entry.getValue());
			}
			andIfEntrys.add(template.trim());
			
			
			if(!column.IsPrikey()){
				map.put("preJoiner", ",");
				map.put("sufJoiner", " ");
				template = ifTemplate;
				for (Map.Entry<String, String> entry : map.entrySet()) {
					template = template.replace("#" + entry.getKey() + "#", entry.getValue());
				}
				commaIfEntrys.add(template.trim());
			}
		}
		map = new LinkedHashMap<String, String>();
		map.put("table.name", table.getName());
		map.put("table.desc", table.getDesc());
		map.put("class.name", className);
		map.put("class.package", pack);
		map.put("useuseGeneratedKey", useuseGeneratedKeys.toString().trim());
		map.put("id", ListUtilHelper.join(idCols, ","));
		map.put("idVal", ListUtilHelper.join(idVals, ", "));
		map.put("Columns",ListUtilHelper.join(excludeIdCols, ",").trim());
		map.put("andIfEntrys", ListUtilHelper.join(andIfEntrys, "\n\t\t"));
		map.put("commaIfEntrys", ListUtilHelper.join(commaIfEntrys, "\n\t\t"));
		
		
		for (Map.Entry<String, String> entry : map.entrySet()) {
			mapperTemplate = mapperTemplate.replace("#" + entry.getKey() + "#", entry.getValue());
		}
		sb.append(mapperTemplate);
		
		return sb.toString().trim();
	}

	/**
	 * @方法:取得表信息
	 * @创建人:独泪了无痕
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	private static Table getTable(String tableName) throws Exception {
		String xml = CodeMakerUtil.read(Configuration.getSQLTemplateLocation());
		// 匹配模式是非贪婪的。非贪婪模式尽可能少的匹配所搜索的字符串，而默认的贪婪模式则尽可能多的匹配所搜索的字符串。
		String sql = RegexUtil.getKeyWords("<table>([\\w\\W]+?)</table>", xml, 1);
		sql = sql.replace("#table#", tableName);
		Connection conn = null;
		ResultSet rs = null;
		Table table = new Table(tableName, tableName);
		try {
			Class.forName(driverField.getText());
			conn = DriverManager.getConnection(urlField.getText(), usernameField.getText(),
					passwordField.getText());
			rs = conn.prepareStatement(sql.toString()).executeQuery();
			while (rs.next()) {
				table = new Table(tableName, rs.getString("table_desc"));
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return table;
	}

	/**
	 * @方法:取得表的数据列
	 * @创建人:独泪了无痕
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	private static List<Column> getColumns(String tableName) throws Exception {
		List<Column> rows = Lists.newArrayList();
		String xml = CodeMakerUtil.read(Configuration.getSQLTemplateLocation());
		// 匹配模式是非贪婪的。非贪婪模式尽可能少的匹配所搜索的字符串，而默认的贪婪模式则尽可能多的匹配所搜索的字符串。
		String sql = RegexUtil.getKeyWords("<column>([\\w\\W]+?)</column>", xml, 1);
		sql = sql.replace("#table#", tableName);
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName(driverField.getText());
			conn = DriverManager.getConnection(urlField.getText(), usernameField.getText(),
					passwordField.getText());
			rs = conn.prepareStatement(sql.toString()).executeQuery();
			while (rs.next()) {
				Column col = new Column(rs);
				rows.add(col);
			}
			
			if (Configuration.getPageFlage()) {
				Column rowNumStart = new Column("rowNumStart", "取数据起始行，用于分页", "int", false,
						Integer.MAX_VALUE);
				rows.add(rowNumStart);
				Column rowNumEnd = new Column("rowNumEnd", "取数据结束行，用于分页", "int", false,
						Integer.MAX_VALUE);
				rows.add(rowNumEnd);
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		finally {
			if (conn != null)
				conn.close();
		}
		return rows;
	}

	/**
	 * @方法描述: 消息对话框
	 * @param message
	 * @param messageType
	 *            ERROR_MESSAGE:错误 <br>
	 *            INFORMATION_MESSAGE 信息<br>
	 *            WARNING_MESSAGE 警告<br>
	 *            QUESTION_MESSAGE（问题默认类型） <br>
	 *            PLAIN_MESSAGE（无图标）
	 */
	private static void showMessageDialog(Object message, int messageType) {
		showMessageDialog(message, "", messageType);
	}

	/**
	 * @方法描述: 消息对话框
	 * @param message
	 * @param title
	 * @param messageType
	 *            ERROR_MESSAGE:错误 <br>
	 *            INFORMATION_MESSAGE 信息<br>
	 *            WARNING_MESSAGE 警告<br>
	 *            QUESTION_MESSAGE（问题默认类型） <br>
	 *            PLAIN_MESSAGE（无图标）
	 */
	private static void showMessageDialog(Object message, String title, int messageType) {
		JOptionPane.showMessageDialog(null, message, title, messageType);
	}

	public static void main(String[] args) throws Exception {
		createView();
		// getTable("");
	}
}
