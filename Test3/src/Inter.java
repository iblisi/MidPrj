import java.awt.Button;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;



public class Inter {
	static TextField serch, tf;
	static Button button;
	static Frame f;
	static private String input;
	static KDAO kd;

	public static String getInput() {
		return input;
	}

	public static void setInput(String input) {
		Inter.input = input;
	}

	public static void main(String[] args) {
		Frame f = new Frame("Dictionary");
		kd = new KDAO();

		f.setLayout(null);
		f.setSize(500, 500);

		Checkbox all = new Checkbox("All", true);
		Checkbox kor = new Checkbox("한국어", false);
		Checkbox jp = new Checkbox("일본어", false);
		all.setBounds(260, 60, 50, 25);
		f.add(all);
		kor.setBounds(320, 60, 50, 25);
		f.add(kor);
		jp.setBounds(380, 60, 50, 25);
		f.add(jp);

		serch = new TextField("검색 할 한자를 적어주세요");
		button = new Button("검색");
		serch.setBounds(80, 100, 300, 20);
		f.add(serch);
		button.setBounds(390, 98, 40, 20);
		f.add(button);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (serch.getText().length() == 1) {
					setInput("KSound ='" + serch.getText());
				}
				if (serch.getText().length() >= 2) {
					setInput("KMean ='" + serch.getText());
				}
				if (serch.getText().length() == 0) {
					serch.setText("찾으려는 한자의 음독이나 훈독을 입력해 주세요.");
				}
			
				ArrayList<KVO> list = kd.list(Inter.getInput());
				
				for(int i=0;i<list.size();i++) {
					KVO data=(KVO)list.get(i);
					int No=data.getNo();
					String KMean=data.getKMean();
					String KSound=data.getKSound();
					tf.setText(kd.list(KSound).toString()+kd.list(KMean).toString());
					System.out.println(No+" : "+KMean+" : "+KSound);
				}
				
				
				
			}
		});

		Canvas kanji = new Canvas();
		kanji.setBackground(Color.pink);
		kanji.setBounds(300, 160, 150, 150);
		f.add(kanji);

		TextField tf = new TextField();
		f.add(tf);
		tf.setBounds(80, 160, 150, 150);
		
		

		tf.setVisible(true);

		f.setVisible(true);

		serch.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				serch.setText("");
			}
		});

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

}
