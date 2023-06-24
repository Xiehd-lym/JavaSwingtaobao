package utiles;

public class StringUtils {

	public static boolean isEmpty(String userName, String password) {
		if("".equals(userName)||"".equals(password)||userName==null||password==null) {
			return true;
		}
		return false;
	}
	public static boolean isEmpty(String text) {
		if("".equals(text)||text==null) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(String text) {
		if("".equals(text)||text==null) {
			return false;
			
		}
		return true;
	}
	public static boolean isNotEmpty(Integer num) {
		if(num==null) {
			return false;
		}
		return true;
	}
	
	public static boolean isTrue(Integer num) {
		if(num==null||num==-1) {
			return false;
		}
		return true;
	}
	public static boolean isNumber(String num) {
		String regex = "^[1-9]+[0-9]*+(.[0-9]{1})?$";
		if(num.matches(regex)) {
			return true;
		}
		return false;
	}
	
}
