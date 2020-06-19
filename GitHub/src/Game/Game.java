package Game;

import java.awt.Dialog;
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
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Game extends JFrame {
	JFrame f2;
	private JTextField problem, enser;
	JComboBox<String> check, combo;
	JButton set, hint;
	JLabel eventO, eventX;
	GDAO gda;
	Dialog info;
	boolean OX;
	
	private JLabel imageLabel;
	private JTextField hintField;
	Image img = new ImageIcon("C:\\Users\\inisu\\Desktop\\배경2.jpg").getImage();

	public Game() {
		
		

		gda = new GDAO();

		f2 = new JFrame("한자게임");
		f2.getContentPane().setLayout(null);

		problem = new JTextField() {
			public void setBorder(Border border) {
			}
		};
		problem.setBounds(68, 75, 242, 147);
		f2.getContentPane().add(problem);
		problem.setColumns(10);
		problem.setEditable(false);
		problem.setOpaque(false);

		enser = new JTextField();
		enser.setBounds(55, 388, 278, 21);
		f2.getContentPane().add(enser);
		enser.setColumns(10);

		check = new JComboBox<String>();
		check.addItem("한국어");
		check.addItem("日本語");
		check.setBounds(309, 10, 63, 23);
		f2.getContentPane().add(check);

		set = new JButton("Start");
		set.setBounds(126, 290, 118, 35);
		f2.getContentPane().add(set);

		imageLabel = new JLabel("");

		hint = new JButton("Hint!");
		hint.setBounds(136, 335, 97, 23);
		f2.getContentPane().add(hint);

		f2.setSize(400, 480);

		hintField = new JTextField() {
			public void setBorder(Border border) {
			}
		};
		hintField.setHorizontalAlignment(JTextField.CENTER);
		hintField.setBounds(12, 232, 360, 35);
		f2.getContentPane().add(hintField);
		hintField.setColumns(10);
		hintField.setOpaque(false);
		hintField.setEditable(false);

		JLabel bgi2 = new JLabel("") {
			{
				setOpaque(false);
			}

			public void paintComponent(Graphics g) {
				g.drawImage(img, 0, 0, null);
				super.paintComponent(g);
			}
		};
		bgi2.setBounds(0, 0, 384, 441);
		f2.getContentPane().add(bgi2);
		hintField.hide();

		f2.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f2.dispose();
				
				
			}
		});

		set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});

		hint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hintField.show();
				if (check.getSelectedIndex() == 0) {
					hintField.setText("힌트 : " + gda.selectK().km);
				}
				if (check.getSelectedIndex() == 1) {
					if (gda.selectJ().jm == " ") {
						hintField.setText("일본어 음독이 없습니다.");
					} else {
						hintField.setText("힌트  : " + gda.selectJ().jm);
					}

				}

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
					hintField.setText("");

					if (check.getSelectedIndex() == 0) { // 한국어
						if (gda.selectK().ks.contains(enser.getText())) {
							System.out.println("정답입니다.");
							OX = true;
							dialog();
							startGame();

						} else {
							System.out.println("정답이 아닙니다. 다시 입력해 주세요.");
							OX = false;
							dialog();
							enser.setText("");
						}
					} else if (check.getSelectedIndex() == 1) {
						if (gda.selectJ().js.contains(enser.getText())) {
							System.out.println("정답입니다.");
							OX = true;
							dialog();
							startGame();
						} else {
							System.out.println("정답이 아닙니다. 다시 입력해 주세요.");
							OX = false;
							dialog();
							enser.setText("");

						}
					}

				}
			}
		});
		f2.setVisible(true);
	}

	public void printO() {
//		imageLabel.setIcon(img0);

		File file = new File("C:\\Users\\inisu\\Desktop\\O.jpg"); // 이미지 파일 경로
		BufferedImage m;
		try {
			m = ImageIO.read(file); // 이미지 파일을 읽어와서 BufferedImage 에 넣음
			imageLabel.setIcon(new ImageIcon(m)); // 레이블에 이미지 표시
			System.out.println("O그림 출력");
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}

	public void printX() {
		File file2 = new File("C:\\Users\\inisu\\Desktop\\X.jpg"); // 이미지 파일 경로
		BufferedImage b;
		try {
			b = ImageIO.read(file2); // 이미지 파일을 읽어와서 BufferedImage 에 넣음
			imageLabel.setIcon(new ImageIcon(b)); // 레이블에 이미지 표시
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}

	public void startGame() {

		String result = gda.start();
		problem.setText(" " + result);
		problem.setFont(new Font("", Font.PLAIN, 150));
		enser.setText("");
	}

	public void stop() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public void dialog() {
		info = new Dialog(f2, "정답 확인", true);
		info.setSize(300, 270);
		info.setLocation(50, 50);
		info.setLayout(null);

		if (OX == true) {
			printO();
		} else {
			printX();
		}

		JButton ok = new JButton("확인");
		ok.setSize(60, 20);
		ok.setLocation(120, 220);
		info.add(ok);

		info.add(imageLabel);
		imageLabel.setBounds(30, 0, 250, 250);

		ok.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					info.dispose();
				}
			}
		});

		info.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
//				System.out.println(e);
				info.dispose();
			}
		});

		info.setVisible(true);
	}

}
