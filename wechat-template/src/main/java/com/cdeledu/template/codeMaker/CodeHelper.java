package com.cdeledu.template.codeMaker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import com.cdeledu.template.codeMaker.config.Column;
import com.cdeledu.template.codeMaker.config.Table;
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
	private static JTextField driverField = new JTextField("");

	private static JTextField urlField = new JTextField("");
	private static JTextField usernameField = new JTextField("");
	private static JTextField passwordField = new JTextField("");
	private static JTextField tableField = new JTextField("");
	private static JTextField packageField = new JTextField("");

	private static JButton codeButton = new JButton(" 生成代码 ");
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
		topPanel.add(getField("驱动", driverField));
		topPanel.add(getField("URL", urlField));
		topPanel.add(getField("用户名", usernameField));
		topPanel.add(getField("密码", passwordField));
		topPanel.add(getField("数据库表", tableField));
		topPanel.add(getField("基础路径", packageField));
		topPanel.add(getFieldSpecial("操作"));
		topPanel.setPreferredSize(new Dimension(0, 270));

		mybatisText.setBorder(BorderFactory.createEtchedBorder());
		beanText.setBorder(BorderFactory.createEtchedBorder());
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.add(tab, BorderLayout.CENTER);
		JScrollPane beanScroll = new JScrollPane(beanText);
		final JScrollBar beanScrollBar = beanScroll.getVerticalScrollBar();
		beanScrollBar.setUnitIncrement(100);
		tab.addTab("Bean 代码", beanScroll);
		tab.addTab("Service 接口", new JScrollPane(serviceText));
		tab.addTab("Service 实现", new JScrollPane(serviceImplText));
		tab.addTab("Dao 实现", new JScrollPane(daoText));
		tab.addTab("MyBatis 配置", new JScrollPane(mybatisText));
		frame.setVisible(true);
		frame.setSize(800, 700);
		frame.setLayout(new BorderLayout());
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 设置窗体全屏显示
		codeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				try {
					createCode();
				} catch (Exception e) {
					e.printStackTrace();

				} finally {
					tab.setSelectedIndex(0);
					beanScrollBar.setValue(beanScrollBar.getMinimum());
				}
			}
		});
		beanText.setLineWrap(true);// 激活自动换行功能
		beanText.setTabSize(4);
		serviceText.setLineWrap(true);// 激活自动换行功能
		serviceText.setTabSize(4);
		serviceImplText.setLineWrap(true);// 激活自动换行功能
		serviceImplText.setTabSize(4);
		daoText.setLineWrap(true);// 激活自动换行功能
		daoText.setTabSize(4);
		mybatisText.setLineWrap(true);// 激活自动换行功能
		mybatisText.setTabSize(4);
	}

	private static JPanel getField(String title, JComponent c) {
		JPanel tr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel(title);
		label.setPreferredSize(new Dimension(80, 30));
		tr.add(label);
		tr.add(c);
		c.setPreferredSize(new Dimension(579, 30));
		return tr;
	}

	private static JPanel getFieldSpecial(String title) {
		JPanel tr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel(title);
		label.setPreferredSize(new Dimension(80, 30));
		tr.add(label);
		JCheckBox box = new JCheckBox();
		box.setText("lombok");
		box.setSelected(true);
		box.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showConfirmDialog(null, "choose one", "choose one",
						JOptionPane.YES_NO_OPTION);
			}
		});
		tr.add(box);
		JRadioButton daoRandioButton = new JRadioButton("Dao");
		daoRandioButton.setSelected(true);
		daoRandioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showConfirmDialog(null, "choose one", "choose one",
						JOptionPane.YES_NO_OPTION);
			}
		});
		JRadioButton mapperRandioButton = new JRadioButton("Mapper");
		mapperRandioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showConfirmDialog(null, "choose one", "choose one",
						JOptionPane.YES_NO_OPTION);
			}
		});
		ButtonGroup group = new ButtonGroup();
		group.add(daoRandioButton);
		group.add(mapperRandioButton);
		tr.add(daoRandioButton);
		tr.add(mapperRandioButton);
		codeButton.setPreferredSize(new Dimension(356, 30));
		tr.add(codeButton);
		return tr;
	}

	/**
	 * @方法:生成代码
	 * @创建人:独泪了无痕
	 * @throws Exception
	 */
	private static void createCode() throws Exception {
		List<Column> columns = getColumns(tableField.getText());
		Table table = getTable(tableField.getText());
		String packages = "";

		mybatisText.setText("");
		beanText.setText("");
		serviceText.setText("");
		serviceImplText.setText("");

		mybatisText.setText(getMyBatisCode(table, packages, columns));
		beanText.setText(getBeanCode(table, packages, columns));
		serviceText.setText(getServiceCode(table, packages));
		serviceImplText.setText(getServiceImplCode(table, packages));
		daoText.setText(getDaoCode(table, packages));
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
		String classTemplate = "", fieldTemplate = "", methodTemplate, importTemplate = "";
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
		String serviceTemplate = "", className = "";
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
		String serviceImplTemplate = "", className = "";
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
		String daoTemplate = "", className = "";
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
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	/**
	 * @方法:取得表信息
	 * @创建人:独泪了无痕
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	private static Table getTable(String tableName) throws Exception {
		Table table = new Table(tableName, tableName);
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
		return rows;
	}

	public static void main(String[] args) {
		createView();
	}
}
