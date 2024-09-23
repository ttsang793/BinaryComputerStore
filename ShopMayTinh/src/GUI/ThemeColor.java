package GUI;

import java.awt.Color;

abstract class ThemeColor {
	static final int NUM_THEME = 2;
	static final Color WHITE = new Color(255, 255, 255);
	static final Color BLACK = new Color(0, 0, 0);	
	
	static Color TEXT = BLACK;
	static Color TEXT_CON = WHITE;
	static Color TEXT_CON_HOVER = new Color(240, 240, 240);
	static Color TEXT_CON_ACTIVE = new Color(225, 225, 225);
	static Color BORDER = new Color(127, 127, 127);
	
	static Color LIGHT_1 = new Color(239, 246, 246);
	static Color LIGHT_2 = new Color(204, 226, 228);
	static Color LIGHT_3 = new Color(147, 194, 196);
	static Color MID_1 = new Color(72, 163, 167);
	static Color MID_2 = new Color(0, 132, 137);
	static Color MID_3 = new Color(0, 116, 118);
	static Color DARK_1 = new Color(0, 100, 101);
	static Color DARK_2 = new Color(0, 84, 82);
	static Color DARK_3 = new Color(0, 42, 41);
	
//	static Color TEXT = WHITE;
//	static Color TEXT_CON = new Color(30, 30, 30);
//	static Color BORDER = new Color(127, 127, 127);
//	
//	static Color LIGHT_1 = new Color(50, 50, 50);
//	static Color LIGHT_2 = new Color(60, 60, 60);
//	static Color LIGHT_3 = new Color(70, 70, 70);
//	static Color MID_1 = new Color(110, 110, 110);
//	static Color MID_2 = new Color(200, 0, 0);
//	static Color MID_3 = new Color(175, 0, 0);
//	static Color DARK_1 = new Color(150, 0, 0);
//	static Color DARK_2 = new Color(125, 0, 0);
//	static Color DARK_3 = new Color(100, 0, 0);
	
	static String LOGO = "..\\img\\logo_1.png";
	static String BIG_LOGO = "..\\img\\logo_big_1.png";
	
	static void setColor(int mode) {	
		BIG_LOGO = "..\\img\\logo_big_" + mode + ".png";
		LOGO = "..\\img\\logo_" + mode + ".png";
		
		switch (mode) {
			case 1: { //mau xanh
				TEXT = BLACK;
				TEXT_CON = WHITE;
				TEXT_CON_HOVER = new Color(240, 240, 240);
				TEXT_CON_ACTIVE = new Color(225, 225, 225);
				BORDER = new Color(127, 127, 127);
				
				LIGHT_1 = new Color(239, 246, 246);
				LIGHT_2 = new Color(204, 226, 228);
				LIGHT_3 = new Color(147, 194, 196);
				MID_1 = new Color(72, 163, 167);
				MID_2 = new Color(0, 132, 137);
				MID_3 = new Color(0, 116, 118);
				DARK_1 = new Color(0, 100, 101);
				DARK_2 = new Color(0, 84, 82);
				DARK_3 = new Color(0, 42, 41);
				
				break;
			}
			
			case 2: { //mau den
				TEXT = WHITE;
				TEXT_CON = new Color(30, 30, 30);
				BORDER = new Color(127, 127, 127);
				TEXT_CON_HOVER = new Color(50, 50, 50);
				TEXT_CON_ACTIVE = new Color(100, 100, 100);
				
				LIGHT_1 = new Color(50, 50, 50);
				LIGHT_2 = new Color(60, 60, 60);
				LIGHT_3 = new Color(70, 70, 70);
				MID_1 = new Color(90, 90, 90);
				MID_2 = new Color(200, 0, 0);
				MID_3 = new Color(175, 0, 0);
				DARK_1 = new Color(150, 0, 0);
				DARK_2 = new Color(125, 0, 0);
				DARK_3 = new Color(100, 0, 0);
				
				break;
			}

			
			case 0: { //mau tim
				TEXT = BLACK;
				TEXT_CON = WHITE;
				TEXT_CON_HOVER = new Color(240, 240, 240);
				TEXT_CON_ACTIVE = new Color(225, 225, 225);
				BORDER = new Color(127, 127, 127);
				
				LIGHT_1 = new Color(243, 239, 246);
				LIGHT_2 = new Color(220, 204, 228);
				LIGHT_3 = new Color(176, 147, 196);
				MID_1 = new Color(131, 72, 167);
				MID_2 = new Color(84, 0, 137);
				MID_3 = new Color(69, 0, 118);
				DARK_1 = new Color(54, 0, 101);
				DARK_2 = new Color(46, 0, 84);
				DARK_3 = new Color(25, 0, 42);
				
				break;
			}
			
			case 4: { //mau den
				LIGHT_1 = new Color(246, 246, 246);
				LIGHT_2 = new Color(227, 227, 227);
				LIGHT_3 = new Color(140, 140, 140);
				MID_1 = new Color(125, 125, 125);
				MID_2 = new Color(110, 110, 110);
				MID_3 = new Color(95, 95, 95);
				DARK_1 = new Color(80, 80, 80);
				DARK_2 = new Color(65, 65, 65);
				DARK_3 = new Color(50, 50, 50);
				break;
			}
			
			case 5: { //mau do
				LIGHT_1 = new Color(246, 246, 246);
				LIGHT_2 = new Color(227, 227, 227);
				LIGHT_3 = new Color(196, 196, 196);
				MID_1 = new Color(167, 72, 72);
				MID_2 = new Color(137, 0, 0);
				MID_3 = new Color(118, 0, 0);
				DARK_1 = new Color(101, 0, 0);
				DARK_2 = new Color(82, 0, 0);
				DARK_3 = new Color(41, 0, 0);
				break;
			}
		}
	}
}