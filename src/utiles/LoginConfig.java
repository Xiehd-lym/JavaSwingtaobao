package utiles;

import java.io.*;
import java.util.ArrayList;

public class LoginConfig {

	/**
	 * 将用户的个人信息写入文件
	 * @param name
	 * @param id
	 * @param password
	 */
	public static void writeUser(String name,String id,String password,String money) {
		BufferedWriter bos = null;
			try {
				bos = new BufferedWriter(new FileWriter("password.txt"));
				bos.write(name);
				bos.newLine();
				bos.write(password);
				bos.newLine();
				bos.write(id);
				bos.newLine();
				bos.write(money);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(bos!=null) {
						bos.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
	}
	/**
	 * 返回文件中用户的个人信息集合
	 * @return
	 */
	public static ArrayList<String> getUserList(){
		ArrayList<String> list = new ArrayList();
		BufferedReader bis;
		try {
			bis = new BufferedReader(new FileReader("password.txt"));
			String s = null;
			while((s=bis.readLine())!=null) {
				list.add(s);
			}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static void reset() {
		BufferedWriter bos = null;
			try {
				bos = new BufferedWriter(new FileWriter("password.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(bos!=null) {
						bos.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
	}
}