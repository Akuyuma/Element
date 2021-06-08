package fr.senoria.client.features.eco.guis.elements.texts;

import org.lwjgl.opengl.GL11;

import fr.senoria.client.features.eco.guis.Element;
import fr.senoria.client.features.eco.utils.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ElementText extends Element {

	private String title;
	private @Setter TextSize size;
	
	public ElementText(String title, int posX, int posY) {
		super(posX, posY, 0, 0);
		this.title = title;
		this.size = TextSize.DEFAULT;
	}

	@Override
	public void display() {
		float finalSize = this.size.getSize();
		GL11.glPushMatrix();
		GL11.glScalef(finalSize, finalSize, finalSize);
		Utils.displayString(this.title, (int) (this.getPosX() / finalSize), (int) (this.getPosY() /finalSize));
		GL11.glPopMatrix();
	}
}
