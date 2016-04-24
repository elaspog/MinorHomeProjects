package net.prokhyon.viewfieldcorrector.view;

import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorRectCell extends ListCell<String> {
	@Override
	public void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		Rectangle rect = new Rectangle(35, 18);
		if (item != null) {
			rect.setFill(Color.web(item));
			setGraphic(rect);
		}
	}
}