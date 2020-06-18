package Game;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Game extends JFrame{
	JFrame f;
	private JTextField problem, enser;
	JComboBox<String> check, combo;
	JButton set;
	JLabel eventO, eventX;
	GDAO gda;

	Image img = new ImageIcon("C:\\Users\\inisu\\Desktop\\O.jpg").getImage();
	Image img2 = new ImageIcon("C:\\Users\\inisu\\Desktop\\X.jpg").getImage();
	
	 String[] OX = {"nomal", "O", "X"}; 

	 ImageIcon[] images = { 
	   new ImageIcon(""), 
	   new ImageIcon("C:\\Users\\inisu\\Desktop\\O.jpg"), 
	   new ImageIcon("C:\\Users\\inisu\\Desktop\\X.jpg"), 
	 };
	 JLabel imgLabel = new JLabel(images[0]);
	 
	public Game() {
		gda = new GDAO();

		f = new JFrame("Word Search Game");
		f.getContentPane().setLayout(null);

		problem = new JTextField();
		problem.setBounds(73, 74, 234, 147);
		f.getContentPane().add(problem);
		problem.setColumns(10);
		problem.setEditable(false);
		problem.setOpaque(false);

		enser = new JTextField();
		enser.setBounds(39, 458, 307, 21);
		f.getContentPane().add(enser);
		enser.setColumns(10);

		check = new JComboBox<String>();
		check.addItem("한국어");
		check.addItem("일본어");
		check.setBounds(309, 10, 63, 23);
		f.getContentPane().add(check);

		set = new JButton("Game Start");
		set.setBounds(124, 258, 120, 23);
		f.getContentPane().add(set);
		f.setSize(400, 600);

//		eventO = new JLabel() {
//			{
//				setOpaque(false);
//			}
//
//			public void paintComponent(Graphics g) {
//				g.drawImage(img, 0, 0, null);
//				super.paintComponent(g);
//
//			}
//		};
//		eventO.hide();
//		eventO.setBounds(117, 283, 255, 147);
//		f.getContentPane().add(eventO);
//
//		eventX = new JLabel() {
//			{
//				setOpaque(false);
//			}
//
//			public void paintComponent(Graphics g) {
//				g.drawImage(img2, 0, 0, null);
//				super.paintComponent(g);
//			}
//		};
//		eventX.hide();
//		eventX.setBounds(73, 283, 255, 147);
//		f.getContentPane().add(eventX);
		
		
		 
		  combo = new JComboBox(OX); 
		  combo.setBounds(100, 10, 63, 23);
		  f.add(combo); 
		  f.add(imgLabel); 
		  imgLabel.setBounds(73, 283, 255, 147);
		  combo.hide();
		  
		  combo.addActionListener(new ActionListener(){ 
		   public void actionPerformed(ActionEvent e){ 
		    JComboBox cb = (JComboBox) e.getSource(); 
		    int index = cb.getSelectedIndex(); 
		    imgLabel.setIcon(images[index]); 
		   }
		   });
		    
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();

			}
		});

		enser.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				enser.setText("");
			}
		});

		enser.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					if (check.getSelectedIndex() == 0) {
						
						if (gda.selectK().ks.contains(enser.getText()) || gda.selectK().km.contains(enser.getText())) {
							System.out.println("정답입니다.");
							combo.setSelectedIndex(1);
							
							stop();
							startGame();	

						} else {
							System.out.println("정답이 아닙니다. 다시 입력해 주세요.");
							combo.setSelectedIndex(2);
						}
					} else if (check.getSelectedIndex() == 1) {
						if (gda.selectJ().js.contains(enser.getText()) || gda.selectJ().jm.contains(enser.getText())) {
							System.out.println("정답입니다.");
							stop();
							startGame();
						} else {
							System.out.println("정답이 아닙니다. 다시 입력해 주세요.");
						}
					} else {
						System.out.println("아무것도아님");
					}

				}
			}
		});

	}

	public void startGame() {
		
		String result = gda.start();
		problem.setText(" " + result);
		problem.setFont(new Font("", Font.PLAIN, 150));
		enser.setText("");
		combo.setSelectedIndex(0);
	}

	public void stop() {
		int num = 80;
		for (int i = 0; i < num; i++) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}		
	}


	public static void main(String[] args) {
		new Game();
	}
}
