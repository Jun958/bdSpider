package cn.baidu.spider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {
	
	public static void main(String args[]) {
		String str = "<img hidefocus='true' src='//www.baidu.com/img/bd_logo1.png' width='270' height='129'>";
		String pattern = "<img [^>]*src=['\"]([^'\"]+)[^>]*>";

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(str);
		System.out.println(m.matches());
	}

}