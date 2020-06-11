import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;

public class Dictionary {
	JFrame f;
	JCheckBox kor, jp;
	private JTextField search, list, hanja;
	JButton button;
	Choice sel;
	
	private JTable table;
	JLabel listname;
	DefaultTableModel model;
	JScrollPane jsp;

	String[] slist = new String[50];
	Object ob[][] = new Object[0][5];

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

		button = new JButton("검색");
		button.setSize(20, 40);
		button.setBounds(476, 71, 60, 23);
		f.getContentPane().add(button);

		search = new JTextField(" 검색 할 한자를 적어주세요");
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

		listname = new JLabel("검색 한 단어");
		listname.setBounds(227, 390, 76, 23);
		f.getContentPane().add(listname);

		kor = new JCheckBox("한국어",true);
		kor.setBounds(403, 31, 61, 23);
		f.getContentPane().add(kor);
		kor.setOpaque(false);

		jp = new JCheckBox("일본어");
		jp.setBounds(476, 31, 64, 23);
		f.getContentPane().add(jp);
		jp.setOpaque(false);

		sel = new Choice();
		sel.setBounds(30, 71, 64, 13);
		
		sel.add(" 선  택 ");
		sel.add(" 음  독 ");
		sel.add(" 훈  독 ");
		f.getContentPane().add(sel);
   
//		model = new DefaultTableModel(ob, str);
		model = dao.model;
		table = new JTable(model);
		jsp = new JScrollPane(table);
		jsp.setBounds(30, 130, 350, 200);
		f.getContentPane().add(jsp);
		
		
		
		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);
		table.getColumn("음 독").setPreferredWidth(40);
		table.getColumn(" 음 독").setPreferredWidth(40);
	
		hanja = new JTextField();
		hanja.setBounds(395, 130, 150, 200);
		f.getContentPane().add(hanja);
		hanja.setColumns(10);
		hanja.setEditable(false);
		
		f.setVisible(true);
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int sel = table.getSelectedRow();
			    String result_han = (String) table.getValueAt(sel, 4);	
				hanja.setText(result_han);
				hanja.setFont(new Font("", Font.PLAIN, 150));
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
						getHanja();
					} else if (sel.getSelectedIndex() == 2) {
						model.setNumRows(0);
						dao.selectKm(search.getText());
						model = dao.getModel();
						getHanja();
					} else {
						search.setText("선택 사항을 선택 해주세요.");
					}
				} else if (jp.isSelected()) {
					if (sel.getSelectedIndex() == 1) {
						model.setNumRows(0);
						dao.selectJs(search.getText());
						model = dao.getModel();
						getHanja();
					} else if (sel.getSelectedIndex() == 2) {
						model.setNumRows(0);
						dao.selectJm(search.getText());
						model = dao.getModel();
						getHanja();
					} else {
						search.setText("선택 사항을 선택 해주세요.");
					}
				} else {
					search.setText("한국어나 일본어를 체크해 주세요.");
				}
			}
		});

	}

	public void getHanja() {
		table.getColumn("한자").setWidth(0);
		table.getColumn("한자").setMinWidth(0);
		table.getColumn("한자").setMaxWidth(0);
	}

	public static void main(String[] args) {
		new Dictionary();
	}
}
