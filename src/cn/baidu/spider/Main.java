package cn.baidu.spider;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	// 获取img标签正则
	private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
	// 获取src路径的正则
	 private static final String IMGSRC_REG = "(([A-Za-z0-9~]+).)+(gif|png)";
	//private static final String IMGSRC_REG = "[^\u4e00-\u9fa5\\s]*\\.(gif|png)";
	public static void main(String[] args) throws Exception {
		String url = "https://www.baidu.com";
		String html = getHtml(url);
		Set<String> imageUrl = getImageUrl(html);
		System.out.println(imageUrl);
		Set<String> imageSrc = getImageSrc(imageUrl);
		System.out.println(imageSrc);
	}

	// 获取HTML内容
	private static String getHtml(String url) throws Exception {
		URL url1 = new URL(url);
		URLConnection connection = url1.openConnection();
		InputStream in = connection.getInputStream();
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);

		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			sb.append(line, 0, line.length());
			sb.append('\n');
		}
		br.close();
		isr.close();
		in.close();
		return sb.toString();
	}

	// 获取ImageUrl地址
	private static Set<String> getImageUrl(String html) {
		Matcher matcher = Pattern.compile(IMGURL_REG).matcher(html);
		Set<String> listimgurl = new HashSet<String>();
		while (matcher.find()) {
			listimgurl.add(matcher.group());
		}
		return listimgurl;
	}

	// 获取ImageSrc地址
	private static Set<String> getImageSrc(Set<String> listimageurl) {
		Set<String> listImageSrc = new HashSet<String>();
		for (String image : listimageurl) {
			Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);
			while (matcher.find()) {
				listImageSrc.add(matcher.group().substring(0,
						matcher.group().length()));
			}
		}
		return listImageSrc;
	}
}