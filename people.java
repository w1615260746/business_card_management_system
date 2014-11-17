package cardcard;
import java.io.*;
import java.util.*;
import java.util.List;
/**
 * 
 * @author wudi
 *
 */
public class people {
	static String name;
	static String phone;
	static String email;
	static List<String> list = getAllLineFromFile("txt/data.txt");
	people(){};
	people(String a,String b,String c)
	{
		name = a;
		phone = b;
		email = c;
	}
	static List<String> getAllLineFromFile(String path)
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
	
	
	
	static void show()
	{
		cardcard.Interface.Message.append(("\n"+"name="+name+",phone="+phone+",email="+email));
	}
	
	
	void add()
	{
		DESPlus des = new DESPlus(cardcard.a.password);     //DES类 解密  
		String x=name+","+phone+","+email;
		list.add(des.encrypt(x));
		try {
			File f = new File("txt/data.txt");
			FileWriter fw = new FileWriter(f);
			fw.write("");
			for (String o : list) 
			{
				fw.write(o+"\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	void find()
	{
		int temp = 0;//标记是否找到
		DESPlus des = new DESPlus(cardcard.a.password);
		try
		{
			BufferedInputStream in = new BufferedInputStream(new FileInputStream("txt/data.txt"));
			System.setIn(in);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s;
			while((s=br.readLine())!=null)
			{
				StringTokenizer st = new StringTokenizer(des.decrypt(s),",");
				String ss;
				if((ss=st.nextToken()).equals(name)||ss.indexOf(name)!=-1)
				{
					cardcard.Interface.Message.setText("找到啦~");
					name = ss;
					phone = st.nextToken();
					email = st.nextToken();
					show();
					temp=1;
				}
			}
		}catch(Exception e){}
		if(temp==0)cardcard.Interface.Message.setText("sorry sir,我们没找到~");
	}
	
	
	void delect(String nameago)
	{
		int temp = 0; //标记是否找到
		DESPlus des = new DESPlus(cardcard.a.password);     //DES类 解密   
		String ss;
		try
		{
			for(int i=0;i<list.size();i++)  
				//遍历这个list，删掉要删的行
			{
				StringTokenizer st = new StringTokenizer(des.decrypt((String)list.get(i)),",");
				if((ss=st.nextToken()).equals(nameago)||ss.indexOf(nameago)!=-1)
				{
					list.remove(i);
					temp = 1;
					break;
				}
			}
			if(temp==0)
				cardcard.Interface.Message.setText("sorry sir,我们没找到~");
			//重新把list写入文件
			File f = new File("txt/data.txt");
			FileWriter fw = new FileWriter(f);
			fw.write("");
			for(String o:list)
			{
				fw.write(o+"\n");
			}
			fw.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	void revise(String nameago,String nameafter,String phoneafter,String emailafter)
	{
		int temp = 0;//标记是否找到
		DESPlus des = new DESPlus(a.password); //解密
		String ss;
		for (int i = 0; i < list.size(); i++) 
		{//遍历这个List，修改要改的行
			StringTokenizer st = new StringTokenizer(des.decrypt((String)list.get(i)),",");
			if ((ss = st.nextToken()).equals(nameago)||ss.indexOf(nameago)!=-1) 
			{
				name = nameafter;
				phone = phoneafter;
				email = emailafter;
				temp = 1;
				list.set(i, des.encrypt(name+","+phone+","+email));
			}
		}
		if (temp==0)cardcard.Interface.Message.setText("sorry sir,我们没找到~");
		//重新写入文件
		File f = new File("txt/data.txt");
		try {
			FileWriter fw = new FileWriter(f);
			fw.write("");
			for (String o:list) 
			{
				fw.write(o+"\n");
			}
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
























