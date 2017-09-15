package com.cdeledu.template.codeMaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

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
	
	private static JTextField databaseField = new JTextField("");
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
		
		topPanel.add(getFieldByBox("数据类型", databaseField,new String[]{"MySQL","SQL Server"}));
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
		// 生成代码
		codeButton.setEnabled(false);
		codeButton.setVisible(false);
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

		mybatisText.setBorder(BorderFactory.createEtchedBorder());
		beanText.setBorder(BorderFactory.createEtchedBorder());

		tab.addTab("Bean 代码", beanScroll);
		tab.addTab("Service 接口", setTextAreaByTab(serviceText));
		tab.addTab("Service 实现", setTextAreaByTab(serviceImplText));
		tab.addTab("MyBatis 配置", setTextAreaByTab(daoText));
		tab.addTab("MyBatis 配置", setTextAreaByTab(mybatisText));

		frame.setVisible(true);
		frame.setSize(800, 700);
		frame.setLayout(new BorderLayout());
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 设置窗体全屏显示

	}

	private static <E> JPanel getFieldByBox(String title, JComponent c,String[] Item) {
		JPanel tr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel(title);
		label.setPreferredSize(new Dimension(80, 30));
		tr.add(label);
		final JComboBox<String> comboBox = new JComboBox<String>(Item);
		comboBox.setSelectedItem(null);// 设置初始选项为空
		comboBox.setPreferredSize(new Dimension(679, 30));
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {  
					System.out.println(comboBox.getSelectedItem());
					codeButton.setEnabled(true);
					codeButton.setVisible(true);
				} 
			}
		});
		comboBox.setForeground(Color.red);
		tr.add(comboBox);
		return tr;
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
		codeButton.setPreferredSize(new Dimension(679, 30));
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
	 * @方法:生成代码
	 * @创建人:独泪了无痕
	 * @throws Exception
	 */
	private static void createCode() throws Exception {
		
		System.out.println("--------------------"+databaseField.getText());
		if(StringUtils.isBlank(databaseField.getText())){
			JOptionPane.showConfirmDialog(null, "参数不能为空","温馨提示",JOptionPane.DEFAULT_OPTION);
			databaseField.grabFocus();
			return;
		}
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
		String url = "jdbc:sqlserver://192.168.192.250:1433;DatabaseName=chinatet_live_302";
		String user = "sa";
		String password = "cailiqiang";
		String sql = "SELECT  st.name,ISNULL(cast([value] as varchar(500)), st.name) table_desc  FROM sys.tables st LEFT JOIN sys.extended_properties sep ON st.object_id = sep.major_id AND sep.minor_id = 0 where st.name = 'zb_comment' ORDER BY st.name";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(url, user, password);
		ResultSet rs = conn.prepareStatement(sql).executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("name"));
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
		return rows;
	}

	public static void main(String[] args) {
		createView();

		try {
			// getTable("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
