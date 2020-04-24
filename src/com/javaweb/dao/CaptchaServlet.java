package com.javaweb.dao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 楠岃瘉鐮�
 */
@WebServlet("/checkCode")
public class CaptchaServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		//鏈嶅姟鍣ㄩ�氱煡娴忚鍣ㄤ笉瑕佺紦瀛�
		response.setHeader("pragma","no-cache");
		response.setHeader("cache-control","no-cache");
		response.setHeader("expires","0");
		
		//鍦ㄥ唴瀛樹腑鍒涘缓涓�涓暱80锛屽30鐨勫浘鐗囷紝榛樿榛戣壊鑳屾櫙
		//鍙傛暟涓�锛氶暱
		//鍙傛暟浜岋細瀹�
		//鍙傛暟涓夛細棰滆壊
		int width = 80;
		int height = 30;
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		//鑾峰彇鐢荤瑪
		Graphics g = image.getGraphics();
		//璁剧疆鐢荤瑪棰滆壊涓虹伆鑹�
		g.setColor(Color.GRAY);
		//濉厖鍥剧墖
		g.fillRect(0,0, width,height);
		
		//浜х敓4涓殢鏈洪獙璇佺爜锛�12Ey
		String checkCode = getCheckCode();
		//灏嗛獙璇佺爜鏀惧叆HttpSession涓�
		request.getSession().setAttribute("CHECKCODE_SERVER",checkCode);
		
		//璁剧疆鐢荤瑪棰滆壊涓洪粍鑹�
		g.setColor(Color.YELLOW);
		//璁剧疆瀛椾綋鐨勫皬澶�
		g.setFont(new Font("榛戜綋",Font.BOLD,24));
		//鍚戝浘鐗囦笂鍐欏叆楠岃瘉鐮�
		g.drawString(checkCode,15,25);
		
		//灏嗗唴瀛樹腑鐨勫浘鐗囪緭鍑哄埌娴忚鍣�
		//鍙傛暟涓�锛氬浘鐗囧璞�
		//鍙傛暟浜岋細鍥剧墖鐨勬牸寮忥紝濡侾NG,JPG,GIF
		//鍙傛暟涓夛細鍥剧墖杈撳嚭鍒板摢閲屽幓
		ImageIO.write(image,"PNG",response.getOutputStream());
	}
	/**
	 * 浜х敓4浣嶉殢鏈哄瓧绗︿覆 
	 */
	private String getCheckCode() {
		String base = "0123456789ABCDEFGabcdefg";
		int size = base.length();
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i=1;i<=4;i++){
			//浜х敓0鍒皊ize-1鐨勯殢鏈哄��
			int index = r.nextInt(size);
			//鍦╞ase瀛楃涓蹭腑鑾峰彇涓嬫爣涓篿ndex鐨勫瓧绗�
			char c = base.charAt(index);
			//灏哻鏀惧叆鍒癝tringBuffer涓幓
			sb.append(c);
		}
		return sb.toString();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request,response);
	}
}



