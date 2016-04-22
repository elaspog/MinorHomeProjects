package net.prokhyon.viewfieldcorrector.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public enum Shape {

	NONE, SQUARE, CIRCLE, TRIANGLE;

	public void draw(GraphicsContext gc, float centerOffsetX, float centerOffsetY, float objectRadius,
			float objectDiameter) {

		switch (this) {

			case SQUARE:
				drawRectangle(gc, centerOffsetX, centerOffsetY, objectRadius, objectDiameter);
				break;

			case CIRCLE:
				drawCircle(gc, centerOffsetX, centerOffsetY, objectRadius, objectDiameter);
				break;

			case TRIANGLE:
				drawTriangle(gc, centerOffsetX, centerOffsetY, objectRadius, objectDiameter);
				break;

			default:
				break;
		}
	}

	private void drawCircle(GraphicsContext gc, float centerOffsetX, float centerOffsetY, float objectRadius,
			float objectDiameter) {

		gc.setStroke(Color.BLACK);
		gc.strokeOval(centerOffsetX - objectRadius, centerOffsetY - objectRadius, objectDiameter, objectDiameter);
	}

	private void drawRectangle(GraphicsContext gc, float centerOffsetX, float centerOffsetY, float objectRadius,
			float objectDiameter) {

		gc.setStroke(Color.BLACK);
		gc.strokeRect(centerOffsetX - objectRadius, centerOffsetY - objectRadius, objectDiameter, objectDiameter);
	}

	private void drawTriangle(GraphicsContext gc, float centerOffsetX, float centerOffsetY, float objectRadius,
			float objectDiameter) {

		gc.setStroke(Color.BLACK);

		float X1 = centerOffsetX;
		float X2 = centerOffsetX + objectRadius;
		float X3 = centerOffsetX - objectRadius;
		float Y1 = centerOffsetY - objectRadius;
		float Y2 = centerOffsetY + objectRadius;
		float Y3 = centerOffsetY + objectRadius;

		gc.strokePolygon(new double[] { X1, X2, X3 }, new double[] { Y1, Y2, Y3 }, 3);

	}
}