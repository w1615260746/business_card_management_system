package cardcard;
import java.awt.event.*;

import javax.swing.JOptionPane;

/**
 * 
 * @author wudi
 *
 */
public class xiugaikuang extends cardcard.addkuang implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	xiugaikuang(String tempname) 
	{
		super();
		this.setTitle("�޸���ϵ��");
		super.n1 = tempname;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			if(!t_u.getText().matches("[^0-9]*"))
			{
				JOptionPane.showMessageDialog(null, "������ʽ����ȷ�����������룡");
				t_u.setText("");
			}
			else if(!t_p.getText().matches("1[3458]\\d{9}"))
			{
				JOptionPane.showMessageDialog(null, "�ֻ���ʽ����ȷ�����������룡");
				t_p.setText("");
			}
			else if (!t_e.getText().matches("?=^[\\w.@]{6,50}$\\w+@\\w+(?:\\.[\\w]{2,3}){1,2})")) 
			{
				JOptionPane.showMessageDialog(null,"email��ʽ����ȷ�����������룡");
				t_e.setText("");
			}
			else 
			{
				this.setVisible(false);
				(new people()).revise(super.n1, t_u.getText(),t_p.getText(),t_e.getText());
				cardcard.login.gongneng.showall();		
			}
		}
		if(e.getSource()==b2)
		{
			t_u.setText("");
			t_p.setText("");
			t_e.setText("");
		}		
	}
	public static void main(String[] args)
	{
		
	}
}
