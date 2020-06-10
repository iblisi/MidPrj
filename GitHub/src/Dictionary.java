import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

public class Dictionary {
	JFrame f;
	JCheckBox kor, jp;
	private JTextField search, list;
	JButton button;
	Choice sel;

	private JTable table;
	JLabel listname;
	DefaultTableModel model;
	JScrollPane jsp;

	String[] slist = new String[50];
	Object ob[][] = new Object[0][5];
//	String str[] = { "�Ƶ�", "����", "��-�Ƶ�", "��-����", "����" };

	DAO dao;

	public Dictionary() {
		
		dao = new DAO();
		
		for (int i = 0; i < 50; i++) {
			slist[i] = "";
		}

		f = new JFrame("dictionary");
		f.getContentPane().setBackground(new Color(0, 191, 255));
		f.setBounds(30, 50, 600, 500);
		f.getContentPane().setLayout(null);

		button = new JButton("�˻�");
		button.setSize(20, 40);
		button.setBounds(476, 71, 60, 23);
		f.getContentPane().add(button);

		search = new JTextField(" �˻� �� ���ڸ� �����ּ���");
		search.setBounds(100, 71, 364, 23);
		f.getContentPane().add(search);
		search.setColumns(10);
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				search.setText("");
			}
		});

		list = new JTextField();
		list.setBounds(308, 390, 232, 21);
		f.getContentPane().add(list);
		list.setColumns(10);
		list.setEditable(false);

		listname = new JLabel("�˻� �� �ܾ�");
		listname.setBounds(227, 390, 76, 23);
		f.getContentPane().add(listname);

		kor = new JCheckBox("�ѱ���",true);
		kor.setBounds(403, 31, 61, 23);
		f.getContentPane().add(kor);
		kor.setOpaque(false);

		jp = new JCheckBox("�Ϻ���");
		jp.setBounds(476, 31, 64, 23);
		f.getContentPane().add(jp);
		jp.setOpaque(false);
		
		sel = new Choice();
		sel.setBounds(30, 71, 64, 13);

		sel.add(" ��  �� ");
		sel.add(" ��  �� ");
		sel.add(" ��  �� ");
		f.getContentPane().add(sel);

		f.setVisible(true);

//		model = new DefaultTableModel(ob, str);
		model = dao.model;
		table = new JTable(model);
		jsp = new JScrollPane(table);
		jsp.setBounds(30, 130, 350, 200);
		f.getContentPane().add(jsp);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (search.getText() == null) {
					list.setText(" ");
				} else {
					for (int i = 0; i < slist.length; i++) {
						slist[i] += search.getText() + ", ";
						list.setText(" " + slist[i]);
					}
				}

				if (kor.isSelected()) {
					if (sel.getSelectedIndex() == 1) {
						model.setNumRows(0);
						dao.selectKs(search.getText());
						model = dao.getModel();

					} else if (sel.getSelectedIndex() == 2) {
						model.setNumRows(0);
						dao.selectKm(search.getText());
						model = dao.getModel();

					} else {
						search.setText("���� ������ ���� ���ּ���.");
					}
				} else if (jp.isSelected()) {
					if (sel.getSelectedIndex() == 1) {
						model.setNumRows(0);
						dao.selectJs(search.getText());
						model = dao.getModel();

					} else if (sel.getSelectedIndex() == 2) {
						model.setNumRows(0);
						dao.selectJm(search.getText());
						model = dao.getModel();

					} else {
						search.setText("���� ������ ���� ���ּ���.");
					}
				} else {
					search.setText("�ѱ�� �Ϻ�� üũ�� �ּ���.");
				}
			}
		});

	}

	public static void main(String[] args) {
		new Dictionary();
	}
}
