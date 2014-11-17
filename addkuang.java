package cardcard;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * 
 * @author wudi
 *
 */
public class addkuang extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton b1,b2;
	JLabel l_u,l_p,l_e;
	JTextField t_u,t_p,t_e;
	String n1,p1,e1;
	JPanel mb1,mb2;
	addkuang()
	{
		mb1 = new JPanel();
		mb2 = new JPanel();
		this.setTitle("增加联系人");
		this.setSize(220,120);
		this.setLocation(240,240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l_u = new JLabel("用户名");
		l_p = new JLabel("手机");
		l_e = new JLabel("email");
		t_u = new JTextField();
		t_p = new JTextField();
		t_e = new JTextField();
		b1 = new JButton("确定");
		b2 = new JButton("清除");
		this.getRootPane().setDefaultButton(b1);
		mb1.setLayout(new GridLayout(3,2));
		mb1.add(l_u);mb1.add(t_u);
		mb1.add(l_p);mb1.add(t_p);
		mb1.add(l_e);mb1.add(t_e);
		
		mb2.add(b1);mb2.add(b2);
		
		this.add(mb1,BorderLayout.CENTER);
		this.add(mb2,BorderLayout.SOUTH);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			if(!t_u.getText().matches("[^0-9]*"))
			{
				JOptionPane.showMessageDialog(null,"姓名格式不正确!请重新输入！");
				t_u.setText("");
			}
			else if(!t_p.getText().matches("1[3458]\\d{9}"))
			{
				JOptionPane.showMessageDialog(null,"手机格式不正确!请重新输入！");
				t_p.setText("");
			} 
			else if(!t_e.getText().matches("(?=^[\\w.@]{6,50}$)\\w+@\\w+(?:\\.[\\w]{2,3}){1,2}"))
			{
				JOptionPane.showMessageDialog(null,"email格式不正确!请重新输入！");
				t_e.setText("");
			}
			else 
			{
				this.setVisible(false);
				(new people(t_u.getText(),t_p.getText(),t_e.getText())).add();
				cardcard.login.gongneng.showall();
			}
		}
		else if(e.getSource()==b2)
		{
			t_u.setText(null);
			t_p.setText(null);
			t_e.setText(null);
		}
	}
	public static void main(String[] args)
	{
		new addkuang();
	}
}
