package com.cdeledu.util.swing.services;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @ClassName: GuiJPanel
 * @Description:面板
 * @author: 独泪了无痕
 * @date: 2015年9月18日 下午4:22:55
 * @version: V1.0
 * @history:
 */
public class GuiJPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/** 获取当前面板 */
	JPanel getContextPanel() {
		return this;
	}

	/** 给面板增加指定标题及字体的Label */
	public void addJLabel(JPanel panel, String name, Font font) {
		JLabel label = new JLabel(name);
		label.setFont(font);
		panel.add(label);
	}

	/** 给面板增加指定标题及字体的Label，并指定布局位置 */
	public void addJLabel(JPanel panel, String name, Font font, String layout) {
		JLabel label = new JLabel(name);
		label.setFont(font);
		panel.add(label, layout);
	}

	/** 创建指定字体、自动换行的JTextArea. */
	public JTextArea createJTextArea(Font font) {
		JTextArea textArea = new JTextArea();
		textArea.setFont(font);
		textArea.setLineWrap(true);
		return textArea;
	}

	/** 创建指定标题、名称、字体的JButton */
	public JButton createJButton(String title, String name, Font font) {
		JButton button = new JButton(title);
		button.setName(name);
		button.setFont(font);
		return button;
	}

	/** 给面板增加指定标题、名称、字体及ActionListener的JButton */
	public void addJButton(JPanel panel, String title, String name, Font font,
			ActionListener listener) {
		JButton button = createJButton(title, name, font);
		button.addActionListener(listener);
		panel.add(button);
	}

	/** 给面板增加指定标题、名称、字体及MouseListener的JButton */
	public void addJButton(JPanel panel, String title, String name, Font font,
			MouseListener listener) {
		JButton button = createJButton(title, name, font);
		button.addMouseListener(listener);
		panel.add(button);
	}

	/** 消息提示 */
	public void showTextAreaMessage(String message, String title, int type,
			Font font, Color background) {
		JOptionPane.showMessageDialog(this, message, title, type);
	}

	/** 创建指定下拉Items、字体及ActionListener的JComboBox */
	public JComboBox<String> createJComboBox(String[] items, Font font,
			ActionListener listener) {
		return createJComboBox(items, 0, font, listener);
	}

	/** 创建指定下拉Items、字体及ActionListener的JComboBox,并选择特定位置的Item */
	public JComboBox<String> createJComboBox(String[] items,
			Integer selectIndex, Font font, ActionListener listener) {
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(font);
		for (String item : items) {
			comboBox.addItem(item);
		}
		comboBox.setSelectedIndex(selectIndex);
		comboBox.addActionListener(listener);
		return comboBox;
	}

	/** 给面板增加指定下拉Items、字体及ActionListener的JComboBox，并指定布局位置 */
	public void addJComboBox(JPanel panel, String[] items, Font font,
			ActionListener listener, String layout) {
		panel.add(createJComboBox(items, font, listener));
	}

	/** 给面板增加指定下拉Items、字体及ActionListener的JComboBox，并指定布局位置，并选择特定位置的Item */
	public void addJComboBox(JPanel panel, String[] items, Integer selectIndex,
			Font font, ActionListener listener, String layout) {
		panel.add(createJComboBox(items, selectIndex, font, listener));
	}

	/** 指定标题、是否selected、字体以及actionListener的JCheckBox */
	public JCheckBox createJCheckBox(String tilte, boolean isSelected,
			Font font, ActionListener listener) {
		JCheckBox checkBox = new JCheckBox();
		checkBox.setText(tilte);
		checkBox.setFont(font);
		checkBox.setSelected(isSelected);
		checkBox.addActionListener(listener);
		return checkBox;
	}

	/** 给面板增加指定标题、是否selected、字体及ActionListener的JCheckbox */
	public void addJCheckBox(JPanel panel, String tilte, boolean isSelected,
			Font font, ActionListener listener) {
		panel.add(createJCheckBox(tilte, isSelected, font, listener));
	}

	/** 给面板增加指定标题、是否selected、字体及ActionListener的JCheckbox,并且指定布局位置 */
	public void addJCheckBox(JPanel panel, String tilte, boolean isSelected,
			Font font, ActionListener listener, String layout) {
		panel.add(createJCheckBox(tilte, isSelected, font, listener), layout);
	}

	/** 给面板增加指定字体的JTextField */
	public void addJTextField(JPanel panel, JTextField textField, Font font) {
		textField.setFont(font);
		panel.add(textField);
	}

	/** 给面板增加指定字体的JTextField，并指定布局位置 */
	public void addJTextField(JPanel panel, JTextField textField, Font font,
			String layout) {
		textField.setFont(font);
		panel.add(textField, layout);
	}
}
