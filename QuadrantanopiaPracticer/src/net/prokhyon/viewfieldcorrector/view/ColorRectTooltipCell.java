package net.prokhyon.viewfieldcorrector.view;

import javafx.scene.control.Tooltip;

public class ColorRectTooltipCell extends ColorRectCell {
	@Override
	public void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		if (item != null) {
			Tooltip.install(this.getParent(), new Tooltip(item));
		}
	}
}