package slogo_general;

import javafx.scene.paint.Color;

public class ColorMapper {

	public Color stringToColor(String color) {
		
		if ("Red".equals(color)) {
			return Color.RED;
		}
		else if ("Orange".equals(color)) {
			return Color.ORANGE;
		}
		else if ("Yellow".equals(color)) {
			return Color.YELLOW;
		}
		else if ("Green".equals(color)) {
			return Color.GREEN;
		}
		else if ("Blue".equals(color)) {
			return Color.BLUE;
		}
		else if ("Purple".equals(color)) {
			return Color.PURPLE;
		}
		else if ("Black".equals(color)) {
			return Color.BLACK;
		}
		else if ("White".equals(color)) {
			return Color.WHITE;
		}
		else if ("Grey".equals(color)) {
			return Color.GREY;
		}
		else if ("Cyan".equals(color)) {
			return Color.CYAN;
		} else {
			return Color.GREY;
		}
	}

}
