package cardcard;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.util.*;
import java.util.List;
/**
 * 
 * @author wudi
 *
 */
public class Interface extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	JMenuBar menubar;
	JMenu menu;
	JMenuItem item1,item2,item3,item4,item5,item6,item7;
	float a,b;
	public static JTextArea Message;
	JButton n1,n2,n3,n4,n5,cl;
	JPanel p1,p2;
	JScrollPane gd;
	List<String> getAllLineFromFile(String path)
	{
		try
		{
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			List<String> list = new ArrayList<String>();
			String ss = "";
			while((ss = br.readLine())!=null)
			{
				list.add(ss);
			}
			fr.close();
			return list;
		}catch(IOException e){return null;}
	}
	private List<String> list = cardcard.people.list;
	public void showall()
	{
		DESPlus des = new DESPlus(cardcard.a.password);
		Message.setText("");
		for(int i=0;i<list.size();i++)  
		{				
			StringTokenizer st = new StringTokenizer(des.decrypt((String)list.get(i)),",");
			Message.append("������"+st.nextToken()+"   �ֻ���"+st.nextToken()+"   email:"+st.nextToken()+"\n");			
		}
	}
	Interface()
	{	
		p1 = new JPanel();
		p2 = new JPanel();
		Message = new JTextArea(10,30);
		this.setTitle("��Ƭ����ϵͳ");
		this.setSize(450,320);
		this.setLocation(120,120);
		//Message.setBackground(Color.white);
		Message.setForeground(Color.black);
		Message.setFont(new Font("Arial",Font.BOLD,16));
		Message.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
		n1 = new JButton("������ϵ��");
		n2 = new JButton("������ϵ��");
		n3 = new JButton("������ϵ��");
		n4 = new JButton("�޸���ϵ��");
		n5 = new JButton("ɾ����ϵ��");
		cl = new JButton("�����ϵ��");
		gd = new JScrollPane(Message);
		p1.add(gd);
		p2.add(n1);
		p2.add(n2);
		p2.add(n3);
		p2.add(n4);
		p2.add(n5);
		p2.add(cl);
		//Container con = getContentPane();
		this.add(p1,BorderLayout.NORTH);
		this.add(p2,BorderLayout.CENTER);
		menubar = new JMenuBar();
		menu = new JMenu("�˵�");
		item1 = new JMenuItem("������ϵ��");
		item2 = new JMenuItem("������ϵ��");
		item3 = new JMenuItem("������ϵ��");
		item4 = new JMenuItem("�޸���ϵ��");
		item5 = new JMenuItem("ɾ����ϵ��");
		item6 = new JMenuItem("�����ϵ��");
		item7 = new JMenuItem("�˳�");
		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		menu.add(item4);
		menu.add(item5);
		menu.add(item6);
		menu.add(item7);
		menubar.add(menu);
		this.setJMenuBar(menubar);
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);
		item5.addActionListener(this);
		item6.addActionListener(this);
		item7.addActionListener(this);
		n1.addActionListener(this);
		n2.addActionListener(this);
		n3.addActionListener(this);
		n4.addActionListener(this);
		n5.addActionListener(this);
		cl.addActionListener(this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
		this.setVisible(true);
		this.setBackground(Color.cyan);
		this.setResizable(false);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==item7)    //�˳�
		{
			System.exit(0);
		}
		else if(e.getSource()==n1||e.getSource()==item1)   //������ϵ��
		{
			showall();
		}
		else if(e.getSource()==n2||e.getSource()==item2)	 //������ϵ��
		{
			new addkuang();
		}
		else if(e.getSource()==n3||e.getSource()==item3)	 //������ϵ��
		{
			String st = JOptionPane.showInputDialog("������Ҫ����������");
			if(!st.equals(""))(new people(st,null,null)).find();
			else JOptionPane.showMessageDialog(null,"������Ϊ�ղ��ܼ�����");
		}
		else if(e.getSource()==n4||e.getSource()==item4)	 //�޸���ϵ��
		{
			String st = JOptionPane.showInputDialog("������Ҫ�޸ĵ�����");
			if(!st.equals(""))
			{
				new xiugaikuang(st); 
			}
			else JOptionPane.showMessageDialog(null, "û���޸ģ�");
		}
		else if(e.getSource()==n5||e.getSource()==item5)	 //ɾ����ϵ��
		{
			String st = JOptionPane.showInputDialog("������Ҫɾ��������");
			if(!st.equals(""))
			{
				(new people()).delect(st);
				showall();
			}
			else JOptionPane.showMessageDialog(null, "û��ɾ����");
		}
		else if(e.getSource()==cl||e.getSource()==item6)   //�����ϵ��
		{
			try
			{
				File f = new File("txt/data.txt");
				FileWriter fw = new FileWriter(f);
				fw.write("");
				fw.close();
			}catch(IOException w)
			{
				w.printStackTrace();
			}
			showall();
		}
	}
	public static void main(String[] args)
	{
		new Interface();
	}
}
