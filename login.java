package cardcard;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * @author wudi
 *
 */
public class login extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField f1,f2;
	JButton b1,b2,b3;
	Label l1,l2;
	JPanel p1,p2,p3;
	static Interface gongneng;
	public login()
	{
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
	
		l1 = new Label("用户名");
		f1 = new JTextField(10);
		p1.add(l1);p1.add(f1);	
		l2 = new Label("密   码");
		f2 = new JPasswordField(10);
		p2.add(l2);p2.add(f2);		
		b1 = new JButton("登陆");
		b2 = new JButton("重输");
		b3 = new JButton("退出");
		p3.add(b1);p3.add(b2);p3.add(b3);
		
		this.setLayout(new GridLayout(3,1));
		this.add(p1);this.add(p2);this.add(p3);
		
		this.setSize(250,120);
		this.setResizable(false);
		this.setVisible(true);
		this.setTitle("用户登陆");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(300,200);
		this.getRootPane().setDefaultButton(b1);
		b1.addActionListener(this);	
		b2.addActionListener(this);
		b3.addActionListener(this);
		this.setIconImage((new ImageIcon("image/qq.jpg")).getImage());
	}
	public static void main(String[] args)
	{
		new login();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1)
		{
			if((f1.getText()).equals("wudi")&&(f2.getText().equals("wudi")))
			{
				JOptionPane.showMessageDialog(null,"亲爱的吴迪，欢迎登陆名片管理系统！");
				this.setVisible(false);
				gongneng = new Interface();
				gongneng.showall();
			}
			else if((f1.getText()).equals("hanzhao")&&(f2.getText().equals("hanzhao")))
			{
				JOptionPane.showMessageDialog(null,"亲爱的韩钊，欢迎登陆名片管理系统！");
				this.setVisible(false);
				gongneng = new Interface();
				gongneng.showall();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"用户名或者密码错误！");
				f2.setText("");
				f1.setText("");	
			}
		}
		else if(e.getSource()==b2)
		{
			f1.setText(null);
			f2.setText(null);

		}
		else if(e.getSource()==b3)
		{
			System.exit(0);
		}
		
	}
	
}


