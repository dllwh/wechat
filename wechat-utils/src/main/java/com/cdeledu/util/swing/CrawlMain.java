package com.cdeledu.util.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import com.cdeledu.common.property.PropertyHelper;
import com.cdeledu.util.apache.lang.DateUtilHelper;
import com.cdeledu.util.swing.util.GuiUtils;

/**
 * @ClassName: CrawlMain
 * @Description: swing主界面
 * @author: 独泪了无痕
 * @date: 2015年9月18日 下午4:23:45
 * @version: V1.0
 * @history:
 */
public class CrawlMain extends JFrame {
	private static final long serialVersionUID = 1L;
	/** -------------------------- 属性 begin ------------------------------- */
	private static final String BASE = "/properties/swing/tools.properties";
	private static final String MORE_TOOLS = "/properties/swing/more_tools.properties";
	private static final String NETWORK_TOOLS = "/properties/swing/network_tools.properties";
	/** 软件的名称 */
	private String softName = "Common GUI Tools";
	/** GUI配置属性Map */
	private static Map<String, String> propsMap;
	/** GUI配置属性Map(更多工具) */
	private static Map<String, String> more_propsMap;
	/** GUI配置属性Map(网络工具) */
	private static Map<String, String> network_propsMap;
	/** 常用插件ID */
	private static String commonUsePlugins;
	/** 宽度, 满足宽高比例1024:768 */
	private static int gui_width;
	/** 高度 */
	private int gui_height = gui_width * 768 / 1024;

	/** -------------------------- 属性 end ------------------------------- */

	/** -------------------------- 私有方法 begin ------------------------------- */
	/** 设定显示位置及大小 */
	private void setLocatinAndSize() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension dimension = kit.getScreenSize();
		int screen_width = (int) dimension.getWidth();
		int screen_height = (int) dimension.getHeight();
		// 如果显示屏幕分辨率没有已设定的面板大, 则按显示屏幕分辨率显示
		gui_width = screen_width < gui_width ? screen_width : gui_width;
		gui_height = screen_height < gui_height ? screen_height : gui_height;
		setLocation((screen_width - gui_width) / 2, (screen_height - gui_height) / 2); // 默认距屏幕左上角(0,
																						// 0),
																						// 此处设为居中显示屏幕
		// JFrame大小
		setSize(gui_width, gui_height);
	}

	private void addMenuBarAction(ActionEvent event, Icon closeIcon, MouseListener closeLabelListener) {
		JMenuItem item = (JMenuItem) event.getSource();
		Integer pluginId = Integer.parseInt(item.getName());
		// 如果菜单面板已经add到tablePanel上,则选中;否则,增加add
		if (pluginsTabIndex.get(pluginId) != null) {
			tabbedPane.setSelectedIndex(pluginsTabIndex.get(pluginId));
			return;
		}
		List<String> pluginProps = pluginsProperties.get(pluginId);
		// 懒加载未加载的插件
		JPanel pluginPanel = pluginsPanel.get(pluginId);
		if (pluginPanel == null) {
			try {
				String clazz = pluginProps.get(2);
				pluginPanel = (JPanel) (Class.forName(clazz)).newInstance();
				pluginsPanel.put(pluginId, pluginPanel);
			} catch (Exception e) {
				GuiUtils.log(e);
			}
		}

		// 2.设置title栏左侧图标，中间插件名称，右侧关闭图标
		JPanel titlePanel = titlesPanel.get(pluginId);
		if (titlePanel == null) {
			titlePanel = new JPanel();
			titlePanel.setOpaque(false);
			String iconPath = pluginProps.get(3).trim();
			if (iconPath.length() > 0) {
				Icon image = GuiUtils.getIcon(iconPath, Toolkit.getDefaultToolkit());
				titlePanel.add(new JLabel(image), BorderLayout.WEST);
			}
			String _title = pluginProps.get(1) + "  ";
			titlePanel.add(new JLabel(_title), BorderLayout.CENTER);
			JLabel closeLabel = new JLabel(closeIcon);
			closeLabel.addMouseListener(closeLabelListener);
			titlePanel.add(closeLabel, BorderLayout.EAST);
			titlesPanel.put(pluginId, titlePanel);
		}

		tabbedPane.addTab(null, pluginPanel);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, titlePanel);

		pluginsTabIndex.put(pluginId, tabbedPane.getTabCount() - 1);
		tabPluginsId.put(tabbedPane.getTabCount() - 1, pluginId);

		// 选中当前新add的插件面板
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
	}

	/** -------------------------- 私有方法 end ------------------------------- */

	/** -------------------------- 公有方法 begin ------------------------------- */
	/** 入口程序 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					propsMap = PropertyHelper.getMapByProperties(BASE);
					more_propsMap = PropertyHelper.getMapByProperties(MORE_TOOLS);
					network_propsMap = PropertyHelper.getMapByProperties(NETWORK_TOOLS);
					propsMap.putAll(more_propsMap);
					propsMap.putAll(network_propsMap);
					gui_width = Integer.parseInt(propsMap.get("GUIWidth"));
					commonUsePlugins = propsMap.get("CommonUseTools");

					/**
					 * 设置皮肤外观
					 */
					String guiSkinStr = propsMap.get("GUISkin").trim();
					if (guiSkinStr.length() > 0) {
						String[] guiSkins = guiSkinStr.split(",");
						for (String skin : guiSkins) {
							if (setLookAndFeel(skin)) {
								break;
							}
						}
					}
					/**
					 * 设置显示字体
					 */
					GuiUtils.fontStyles_cn = propsMap.get("fontStyles_cn").split(",");
					GuiUtils.fontStyle_cn = GuiUtils.getAvailableFont(GuiUtils.fontStyles_cn);
					// 2.英文字体
					GuiUtils.fontStyles = (new String(propsMap.get("fontStyles").getBytes("ISO-8859-1"), "UTF-8") + ","
							+ GuiUtils.fontStyle_cn).split(",");
					GuiUtils.fontStyle = GuiUtils.getAvailableFont(GuiUtils.fontStyles);
					// 3.支持Unicode的字体
					GuiUtils.fontStyles_un = (new String(propsMap.get("fontStyles_un").getBytes("ISO-8859-1"), "UTF-8")
							+ "," + GuiUtils.fontStyle_cn).split(",");
					GuiUtils.fontStyle_un = GuiUtils.getAvailableFont(GuiUtils.fontStyles_un);
					// 4.初始化字体
					GuiUtils.initFont();

					CrawlMain frame = new CrawlMain();
					frame.setVisible(true);
				} catch (Exception e) {
					GuiUtils.log(e);
				}
			}

			/**
			 * 设置皮肤外观.
			 */
			private boolean setLookAndFeel(String lookAndFeel) {
				try {
					UIManager.setLookAndFeel(lookAndFeel);
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		});
	}

	/** Swing 主面板 */
	private JPanel contextPanel;
	/** Tab Panel */
	private JTabbedPane tabbedPane = new JTabbedPane();
	/** 警告、错误消息输出表单 */
	public static JTextArea msgTextArea = new JTextArea();
	/** 插件属性---key：插件ID_插件名称,value:{插件ID, 插件名称, 插件类名全称, 插件图标}. */
	private Map<Integer, List<String>> pluginsProperties = new LinkedHashMap<Integer, List<String>>();
	/** 用来记录插件(菜单)在tabbedPane的位置---key:插件ID,value:Index索引位置. */
	private Map<Integer, Integer> pluginsTabIndex = new HashMap<Integer, Integer>();
	/** 插件面板---key:插件ID,value:JPanel面板. */
	private Map<Integer, JPanel> pluginsPanel = new HashMap<Integer, JPanel>();
	/** 插件title面板---key:插件ID,value:title面板 */
	private Map<Integer, JPanel> titlesPanel = new HashMap<Integer, JPanel>();
	/** 记录当前TabPanel位置的插件---key:Index索引位置,value:插件ID */
	private Map<Integer, Integer> tabPluginsId = new HashMap<Integer, Integer>();

	public CrawlMain() {
		contextPanel = (JPanel) getContentPane();
		/**
		 * ㈠ 面板基本设置
		 */
		// 标题
		setTitle(softName);
		// 默认不设置时是true
		setResizable(false);
		// 位置与大小
		setLocatinAndSize();
		// 边界布局管理器
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit kit = Toolkit.getDefaultToolkit();
		setIconImage(GuiUtils.getImage("images/icon/cgt_Icon.png", kit));

		/**
		 * ㈡ 选项卡设置
		 */
		// 1.添加
		contextPanel.add(tabbedPane, BorderLayout.CENTER);
		// 2.关闭图标
		final Icon closeIcon = GuiUtils.getIcon("images/icon/cgt_Close.png", kit);
		// 3.关闭Label事件
		final MouseListener closeLabelListener = new MouseListener() {
			/** 鼠标松开 */

			public void mouseReleased(MouseEvent e) {
				// 当前点击Label的父Panel
				int closeIndex = tabbedPane.indexOfTabComponent(((JLabel) e.getSource()).getParent());
				tabbedPane.remove(closeIndex);
				// 移除插件在当前TabPanel的位置的记录
				pluginsTabIndex.remove(tabPluginsId.get(closeIndex));
				// 清空原来的tabPluginsId位置的记录,重新记录
				tabPluginsId.clear();
				for (Integer it : pluginsTabIndex.keySet()) {
					Integer index = pluginsTabIndex.get(it);
					if (index > closeIndex) {
						tabPluginsId.put(it, index - 1);
					}
					tabPluginsId.put(index, it);
				}
			}

			/** 鼠标按下 */

			public void mousePressed(MouseEvent e) {
			}

			/** 鼠标离开（某组件区域） */

			public void mouseExited(MouseEvent e) {
			}

			/** 鼠标进入（某组件区域） */

			public void mouseEntered(MouseEvent e) {
			}

			/** 鼠标点击（单或双） */

			public void mouseClicked(MouseEvent e) {
			}
		};

		// 4.加载选项卡
		tabbedPane.setFont(GuiUtils.font14b_cn);
		/**
		 * ㈢ 菜单工具条
		 */
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// 1 File菜单
		JMenu fileMenu = new JMenu("  File  ");
		menuBar.add(fileMenu);
		// 1.1 二级菜单 -新建
		JMenuItem newItem = new JMenuItem("新建");
		newItem.setIcon(GuiUtils.getIcon("images/icon/cgt_New.png", kit));
		fileMenu.add(newItem);
		// 1.2 二级菜单 -打开
		JMenuItem openItem = new JMenuItem("打开");
		fileMenu.add(openItem);
		// 1.2 二级菜单 -分割线
		fileMenu.addSeparator();
		// 1.4 二级菜单 -退出
		JMenuItem exitItem = new JMenuItem("退出");
		exitItem.setIcon(GuiUtils.getIcon("images/icon/cgt_Exit.png", kit));
		fileMenu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// 2.工具菜单
		JMenu toolsMenu = new JMenu("  工具  ");
		menuBar.add(toolsMenu);
		// 3.更多工具菜单
		JMenu more_toolsMenu = new JMenu("  更多工具  ");
		menuBar.add(more_toolsMenu);
		// 4.网络工具菜单
		JMenu network_toolsMenu = new JMenu("  网络工具  ");
		menuBar.add(network_toolsMenu);
		// 5.Help菜单
		// 5.1 创建一级菜单
		JMenu helpMenu = new JMenu("  帮助  ");
		menuBar.add(helpMenu);
		JMenuItem fontItem = new JMenuItem("系统字体"); // 二级菜单
		fontItem.setIcon(GuiUtils.getIcon("images/icon/cgt_Font.png", kit));
		helpMenu.add(fontItem);
		fontItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(contextPanel,
						softName + " 使用的字体\n\n中文字体:  " + GuiUtils.fontStyle_cn + "\n英文字体:  " + GuiUtils.fontStyle
								+ "\nUnicode字体:  " + GuiUtils.fontStyle_un + "\n",
						"Font", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		// 5.2 分隔符
		helpMenu.addSeparator();
		// 5.3 创建二级菜单
		JMenuItem aboutItem = new JMenuItem("关于");
		aboutItem.setIcon(GuiUtils.getIcon("images/icon/cgt_About.png", kit));
		helpMenu.add(aboutItem);
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(contextPanel,
						"版本号: " + propsMap.get("Version") + "\n作者: " + propsMap.get("Author") + "\n电子邮箱: "
								+ propsMap.get("email") + "\n开发日期: " + DateUtilHelper.getCurrentDate(),
						"关于系统信息", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// 6 加载菜单
		String toolPrefix = "Tool_";
		try {
			List<String> pluginSortAndNames = new ArrayList<String>();
			for (String key : propsMap.keySet()) {
				if (key.startsWith(toolPrefix)) {
					pluginSortAndNames.add(key.substring(toolPrefix.length()));
				}
			}
			Collections.sort(pluginSortAndNames); // 排序
			// 插件属性，按ID升序排列
			int moreTools_cnt = more_propsMap.size();
			int networkTools_cnt = network_propsMap.size();

			for (int i = 0; i < pluginSortAndNames.size(); i++) {
				String key = pluginSortAndNames.get(i);
				final List<String> pluginProps = new ArrayList<String>();
				String[] sortAndName = key.split("_");

				String pluginName = sortAndName[1];
				pluginProps.add(sortAndName[0]);
				pluginProps.add(pluginName);

				String classAndIcon = propsMap.get(toolPrefix + key);
				String className = classAndIcon.split("_")[0];
				pluginProps.add(className);
				pluginProps.add(classAndIcon.indexOf("_") > 0 ? classAndIcon.substring(className.length() + 1) : "");

				Integer pluginId = Integer.parseInt(sortAndName[0]);

				try {
					Class.forName(className);
					pluginsProperties.put(pluginId, pluginProps);
					// 插件图标
					String iconPath = pluginProps.get(3).trim();
					Icon toolIcon = null;
					if (iconPath.length() > 0) {
						toolIcon = GuiUtils.getIcon(iconPath, kit);
					}

					// 初始只加载的插件
					if (commonUsePlugins.contains(Integer.toString(pluginId))) {
						// 1.初始只加载的插件
						JPanel pluginPanel = (JPanel) Class.forName(className).newInstance();
						pluginsPanel.put(pluginId, pluginPanel);

						// 2.设置title栏左侧图标，中间插件名称，右侧关闭图标
						JPanel titlePanel = new JPanel();
						titlePanel.setOpaque(false);
						titlePanel.add(new JLabel(toolIcon), BorderLayout.WEST);
						titlePanel.add(new JLabel(pluginName + "  "), BorderLayout.CENTER);
						JLabel closeLabel = new JLabel(closeIcon);
						closeLabel.addMouseListener(closeLabelListener);
						titlePanel.add(closeLabel, BorderLayout.EAST);

						titlesPanel.put(pluginId, titlePanel);

						tabbedPane.addTab(null, pluginPanel);
						tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, titlePanel);

						pluginsTabIndex.put(pluginId, tabbedPane.getTabCount() - 1);
						tabPluginsId.put(tabbedPane.getTabCount() - 1, pluginId);
					}

					// 设置二级菜单菜单
					JMenuItem toolItem = new JMenuItem(pluginName);
					toolItem.setName(Integer.toString(pluginId));
					toolItem.setIcon(toolIcon);
					/** 根据pluginId的条件不同的菜单加载不同的子菜单 */
					if (pluginId > 50 && pluginId < 71) {
						more_toolsMenu.add(toolItem);
						moreTools_cnt--;
						if (moreTools_cnt > 0) {
							more_toolsMenu.addSeparator(); // 分隔符
						}
					} else if (pluginId > 70 && pluginId < 99) {
						network_toolsMenu.add(toolItem);
						networkTools_cnt--;
						if (networkTools_cnt > 0) {
							network_toolsMenu.addSeparator(); // 分隔符
						}
					} else {
						toolsMenu.add(toolItem);
						if (pluginId != 99) {
							toolsMenu.addSeparator(); // 分隔符
						}
					}

					toolItem.addActionListener(new ActionListener() {
						// 菜单事件

						public void actionPerformed(ActionEvent event) {
							addMenuBarAction(event, closeIcon, closeLabelListener);
						}
					});
				} catch (Exception e) {
					GuiUtils.log("Warn: Ignore plugin \"" + sortAndName[1] + "\", because can not find it's Class \""
							+ className + "\".");
				}
			}
		} catch (Exception e) {
			GuiUtils.log(e);
		}

		/**
		 * ㈣ 警告、错误消息输出域
		 */
		// 4.1 输出区域
		JPanel msgPanel = new JPanel(new BorderLayout());
		msgPanel.setBorder(BorderFactory.createTitledBorder("Console"));
		msgTextArea.setFont(GuiUtils.font13_cn);
		msgTextArea.setRows(8);
		msgTextArea.setEditable(false);
		msgTextArea.setLineWrap(true); // 自动换行
		msgPanel.add(new JScrollPane(msgTextArea), BorderLayout.CENTER);
		// 4.2 paste复制按钮
		JPanel msgButtonPanel = new JPanel(new GridLayout(2, 1));
		JButton pasteButton = new JButton("复制");
		pasteButton.setFont(GuiUtils.font13_cn);
		pasteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				StringSelection selection = new StringSelection(msgTextArea.getText());
				// 获取系统剪切板，复制输出消息
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
			}
		});
		msgButtonPanel.add(pasteButton);
		// 4.3 clear清楚按钮
		JButton clearButton = new JButton("清除");
		clearButton.setFont(GuiUtils.font13_cn);
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				msgTextArea.setText("");
			}
		});
		msgButtonPanel.add(clearButton);
		msgPanel.add(msgButtonPanel, BorderLayout.EAST);
		// 4.4 添加到面板
		contextPanel.add(msgPanel, BorderLayout.SOUTH);

	}
	/** -------------------------- 公有方法 end ------------------------------- */
}
