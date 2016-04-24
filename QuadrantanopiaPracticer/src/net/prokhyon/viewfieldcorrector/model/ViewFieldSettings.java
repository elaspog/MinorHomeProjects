package net.prokhyon.viewfieldcorrector.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ViewFieldSettings {

	private final IntegerProperty resolutionWidth;
	private final IntegerProperty resolutionHeight;
	private final IntegerProperty centerAxisOffsetX;
	private final IntegerProperty centerAxisOffsetY;
	private final IntegerProperty obectStartingPositionX;
	private final IntegerProperty obectStartingPositionY;
	private final IntegerProperty objectSize;
	private final IntegerProperty axisSize;
	private final IntegerProperty objectStartingRadius;
	private final IntegerProperty animationSpeed;

	public ViewFieldSettings() {
		this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	public ViewFieldSettings(int resolutionWidth, int resolutionHeight, int centerAxisOffsetX, int centerAxisOffsetY,
			int obectStartingPositionX, int obectStartingPositionY, int objectSize, int axisSize,
			int objectStartingRadius, int animationSpeed) {

		this.resolutionWidth = new SimpleIntegerProperty(resolutionWidth);
		this.resolutionHeight = new SimpleIntegerProperty(resolutionHeight);
		this.centerAxisOffsetX = new SimpleIntegerProperty(centerAxisOffsetX);
		this.centerAxisOffsetY = new SimpleIntegerProperty(centerAxisOffsetY);
		this.obectStartingPositionX = new SimpleIntegerProperty(obectStartingPositionX);
		this.obectStartingPositionY = new SimpleIntegerProperty(obectStartingPositionY);
		this.objectSize = new SimpleIntegerProperty(objectSize);
		this.axisSize = new SimpleIntegerProperty(axisSize);
		this.objectStartingRadius = new SimpleIntegerProperty(objectStartingRadius);
		this.animationSpeed = new SimpleIntegerProperty(animationSpeed);
	}

	public int getResolutionWidth() {
		return resolutionWidth.get();
	}

	public void setResolutionWidth(int resolutionWidth) {
		this.resolutionWidth.set(resolutionWidth);
	}

	public IntegerProperty resolutionWidthProperty() {
		return resolutionWidth;
	}

	public int getResolutionHeight() {
		return resolutionHeight.get();
	}

	public void setResolutionHeight(int resolutionHeight) {
		this.resolutionHeight.set(resolutionHeight);
	}

	public IntegerProperty resolutionHeightProperty() {
		return resolutionHeight;
	}

	public int getCenterAxisOffsetX() {
		return centerAxisOffsetX.get();
	}

	public void setCenterAxisOffsetX(int centerAxisOffsetX) {
		this.centerAxisOffsetX.set(centerAxisOffsetX);
	}

	public IntegerProperty centerAxisOffsetXProperty() {
		return centerAxisOffsetX;
	}

	public int getCenterAxisOffsetY() {
		return centerAxisOffsetY.get();
	}

	public void setCenterAxisOffsetY(int centerAxisOffsetY) {
		this.centerAxisOffsetY.set(centerAxisOffsetY);
	}

	public IntegerProperty centerAxisOffsetYProperty() {
		return centerAxisOffsetY;
	}

	public int getObectStartingPositionX() {
		return obectStartingPositionX.get();
	}

	public void setObectStartingPositionX(int obectStartingPositionX) {
		this.obectStartingPositionX.set(obectStartingPositionX);
	}

	public IntegerProperty obectStartingPositionXProperty() {
		return obectStartingPositionX;
	}

	public int getObectStartingPositionY() {
		return obectStartingPositionY.get();
	}

	public void setObectStartingPositionY(int obectStartingPositionY) {
		this.obectStartingPositionY.set(obectStartingPositionY);
	}

	public IntegerProperty obectStartingPositionYProperty() {
		return obectStartingPositionY;
	}

	public int getObjectSize() {
		return objectSize.get();
	}

	public void setObjectSize(int objectSize) {
		this.objectSize.set(objectSize);
	}

	public IntegerProperty objectSizeProperty() {
		return objectSize;
	}

	public int getAxisSize() {
		return axisSize.get();
	}

	public void setAxisSize(int axisSize) {
		this.axisSize.set(axisSize);
	}

	public IntegerProperty axisSizeProperty() {
		return axisSize;
	}

	public int getObjectStartingRadius() {
		return objectStartingRadius.get();
	}

	public void setObjectStartingRadius(int objectStartingRadius) {
		this.objectStartingRadius.set(objectStartingRadius);
	}

	public IntegerProperty objectStartingRadiusProperty() {
		return objectStartingRadius;
	}

	public int getAnimationSpeed() {
		return animationSpeed.get();
	}

	public void setAnimationSpeed(int animationSpeed) {
		this.animationSpeed.set(animationSpeed);
	}

	public IntegerProperty animationSpeedProperty() {
		return animationSpeed;
	}

}
