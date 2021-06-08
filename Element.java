package fr.senoria.client.features.eco.guis;

import fr.senoria.client.features.eco.utils.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class Element {
	
	private String uniqueId;
	private int posX, posY, width, height;
	private boolean visible, active, priority;
	
	public Element(int posX, int posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.visible = true;
		this.active = true;
		this.priority = false;
	}
	
	public boolean isHovered() {
		return Utils.getMouseX() >= this.getPosX() && Utils.getMouseY() >= this.getPosY() && Utils.getMouseX() < this.getPosX() + this.width && Utils.getMouseY() < this.getPosY() + this.height;
	}

	public boolean isPressed() {
		return this.isActive() && this.isHovered();
	}
	
	public abstract void display();
}
