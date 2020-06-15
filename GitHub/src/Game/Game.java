package Game;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Game {
	JFrame f;
	private JTextField problem, enser;
	JComboBox<String> check;
	JButton set;

	GDAO gda;

	public Game() {
		gda = new GDAO();

		f = new JFrame();
		f.getContentPane().setLayout(null);

		problem = new JTextField();
		problem.setBounds(70, 109, 234, 154);
		f.getContentPane().add(problem);
		problem.setColumns(10);
		problem.setEditable(false);
		problem.setOpaque(false);

		enser = new JTextField();
		enser.setBounds(39, 320, 307, 21);
		f.getContentPane().add(enser);
		enser.setColumns(10);

		check = new JComboBox<String>();
		check.addItem("한국어");
		check.addItem("일본어");
		check.setBounds(283, 34, 63, 23);
		f.getContentPane().add(check);

		set = new JButton("Game Start");
		set.setBounds(131, 287, 120, 23);
		f.getContentPane().add(set);
		f.setSize(400, 600);

		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = gda.start();
				problem.setText(" " + result);
				problem.setFont(new Font("", Font.PLAIN, 150));

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

						if (gda.selectK().ks.equals(enser.getText())||gda.selectK().km.equals(enser.getText())) {
							System.out.println("정답입니다.");
						} else {
							System.out.println("정답이 아닙니다. 다시 입력해 주세요.");
						}
					} else if (check.getSelectedIndex() == 1) {
						
						if (gda.selectJ().js.equals(enser.getText())||gda.selectJ().jm.equals(enser.getText())) {
							System.out.println("정답입니다.");
						} else {
							System.out.println("정답이 아닙니다. 다시 입력해 주세요.");
						}
					}

				}
			}
		});

	}

	public static void main(String[] args) {
		new Game();
	}
}
