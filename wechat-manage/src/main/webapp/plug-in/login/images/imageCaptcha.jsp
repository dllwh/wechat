<%@page import="com.cdeledu.util.WebUtilHelper"%>
<%@page import="com.cdeledu.util.webpage.WebHelper"%>
<%@ page import="com.cdeledu.common.constants.GlobalConstants"%>
<%@ page import="java.awt.*"%>
<%@ page import="java.awt.image.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.imageio.*"%>
<%@ page contentType="image/jpeg" pageEncoding="utf-8"%>

<%!Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}%>
<%
	out.clear();
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	int width = 80, height = 20;
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	Graphics g = image.getGraphics();
	Random random = new Random();
	g.setColor(getRandColor(200, 250));
	g.fillRect(0, 0, width, height);
	g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	g.setColor(getRandColor(160, 200));
	for (int i = 0; i < 155; i++) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(12);
		int yl = random.nextInt(12);
		g.drawLine(x, y, x + xl, y + yl);
	}
	String sRand = "";
	for (int i = 0; i < 4; i++) {
		String temp[] = { "A", "a", "B", "b", "C", "c", "D", "d", "E", "e", "F", "f", "G",
				"g", "H", "h", "I", "i", "J", "j", "K", "k", "L", "l", "M", "m", "N", "n",
				"O", "o", "P", "p", "Q", "q", "R", "r", "S", "s", "T", "t", "U", "u", "V",
				"v", "W", "w", "X", "x", "Y", "y", "Z", "z", "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9" };
		int index = (int) (Math.random() * temp.length);
		String rand = String.valueOf(temp[index]);
		sRand += rand;
		g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110),
				20 + random.nextInt(110)));
		g.drawString(rand.toUpperCase(), 18 * i + 10, 16);
	}
	// 将验证码存入SESSION 
	HttpSession  imageSession = WebUtilHelper.getSession();
	imageSession.removeAttribute(GlobalConstants.IMAGECAPTCHA);
	imageSession.setAttribute(GlobalConstants.IMAGECAPTCHA, sRand);
	g.dispose();
	ImageIO.write(image, "JPEG", response.getOutputStream());
%>