import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

// �˻���� â�� ���� �ܾ ������ �� �ٽ� �� �˻�ȭ���� ���� �� �ֵ���
// sql�δ��� �̿��ؼ� ������ ���̽��� ���ڰ� ������ �ʰ� �Է°������� Ȯ��

public class Dictionary {
	public JFrame f;
	JCheckBox kor, jp;
	private JTextField search, list, hanja;
	JButton button;
	Choice sel;
	ButtonGroup BGC;
	
	private JTable table;
	JLabel listname;
	DefaultTableModel model;
	JScrollPane jsp;

	String[] slist = new String[50];
	Object ob[][] = new Object[0][5];

	DAO dao;
	

	Image img = new ImageIcon("C:\\Users\\inisu\\Desktop\\���2.jpg").getImage();
	Image img3 = new ImageIcon("C:\\Users\\inisu\\Desktop\\����.png").getImage();
	private JLabel bgi;
	private JButton Game;
	private JLabel lblNewLabel;

	public Dictionary() {

		dao = new DAO();
		

		for (int i = 0; i < 50; i++) {
			slist[i] = "";
		}

		f = new JFrame("dictionary");
		f.getContentPane().setBackground(Color.white);
		f.setBounds(330, 200, 885, 689);
		f.getContentPane().setLayout(null);

		button = new JButton("�˻�");
		button.setSize(20, 40);
		button.setBounds(734, 87, 76, 33);
		f.getContentPane().add(button);

		search = new JTextField(" �˻� �� ���ڸ� �����ּ���");
		search.setBounds(145, 87, 590, 33);
		f.getContentPane().add(search);
		search.setColumns(10);
		search.setOpaque(false);

		list = new JTextField();
		list.setBounds(476, 525, 334, 33);
		f.getContentPane().add(list);
		list.setColumns(10);
		list.setEditable(false);
		list.setOpaque(false);
		list.setFont(new Font("", Font.BOLD, 20));
		list.setForeground(Color.WHITE);

		listname = new JLabel("�˻� �� �ܾ�");
		listname.setBounds(336, 524, 128, 34);
		listname.setFont(new Font("", Font.BOLD, 20));
//		listname.setForeground(Color.WHITE);
		f.getContentPane().add(listname);
		
		BGC=new ButtonGroup();
		kor = new JCheckBox(" �ѱ���", true);
		kor.setBounds(675, 46, 65, 23);
		f.getContentPane().add(kor);
		kor.setOpaque(false);
		jp = new JCheckBox(" �Ϻ���");
		jp.setBounds(746, 46, 76, 23);
		f.getContentPane().add(jp);
		jp.setOpaque(false);
		BGC.add(kor);
		BGC.add(jp);
		
		sel = new Choice();
		sel.setBounds(39, 87, 97, 60);

		sel.add(" ��  �� ");
		sel.add(" ��  �� ");
		sel.add(" ��  �� ");
		f.getContentPane().add(sel);

//		model = new DefaultTableModel(ob, str);
		model = dao.model;
		table = new JTable(model);
		jsp = new JScrollPane(table);
		jsp.setBounds(39, 142, 497, 285);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);
		f.getContentPane().add(jsp);

		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);
		table.getColumn("�� ��").setPreferredWidth(40);
		table.getColumn(" �� ��").setPreferredWidth(40);

		hanja = new JTextField() {
			public void setBorder(Border border) {
			}
		};
		hanja.setOpaque(false);
		hanja.setBounds(589, 142, 221, 285);
		f.getContentPane().add(hanja);
		hanja.setColumns(10);
		hanja.setEditable(false);

		bgi = new JLabel() {
			{
				setOpaque(false);
			}

			public void paintComponent(Graphics g) {
				g.drawImage(img, 0, 0, null);
				super.paintComponent(g);
			}
		};
		bgi.setBounds(0, 0, 869, 650);
		f.getContentPane().add(bgi);

		Game = new JButton(new ImageIcon("C:\\Users\\inisu\\Desktop\\����2.png"));
		Game.setToolTipText("");
		Game.setBounds(10, 10, 150, 70);
		Game.setBorderPainted(false);
		Game.setContentAreaFilled(false);
		Game.setFocusPainted(false);
		bgi.add(Game);
		
		

		Game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				f.hide();
				Game g =new Game();
			}
		});
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
				hanja.setFont(new Font("", Font.PLAIN, 220));
			}
		});

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select();
			}
		});

		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				search.setText("");
			}
		});

		search.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					select();
				}
			}
		});
		f.setVisible(true);
	}

	public void getHanja() {
		table.getColumn("����").setWidth(0);
		table.getColumn("����").setMinWidth(0);
		table.getColumn("����").setMaxWidth(0);
	}

	public void select() {
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
				search.setText("���� ������ ���� ���ּ���.");
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
				search.setText("���� ������ ���� ���ּ���.");
			}
		} else {
			search.setText("�ѱ�� �Ϻ�� üũ�� �ּ���.");
		}
	}

	public static void main(String[] args) {
		new Dictionary();
	}
}
