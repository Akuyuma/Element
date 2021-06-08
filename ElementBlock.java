package fr.senoria.client.features.eco.guis;

import org.lwjgl.opengl.GL11;

import fr.senoria.client.features.eco.utils.Colors;
import fr.senoria.client.features.eco.utils.Utils;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;

@Getter
public class ElementBlock extends Element {

	private Colors color;
	private float transparency;
	private boolean borders;

	public ElementBlock(int posX, int posY, int width, int height, Colors color, float transparency) {
		super(posX, posY, width, height);
		this.color = color;
		this.transparency = transparency;
		this.borders = true;
	}
	
	public ElementBlock(int posX, int posY, int width, int height, Colors color, float transparency, boolean borders) {
		super(posX, posY, width, height);
		this.color = color;
		this.transparency = transparency;
		this.borders = borders;
	}

	public void display() {
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		Utils.setGlColor(this.color.getLightValue(), this.transparency);
		tessellator.startDrawingQuads();
		tessellator.addVertex(this.getPosX(), this.getPosY() + this.getHeight(), 0.0D);
		tessellator.addVertex(this.getPosX() + this.getWidth(), this.getPosY() + this.getHeight(), 0.0D);
		tessellator.addVertex(this.getPosX() + this.getWidth(), this.getPosY(), 0.0D);
		tessellator.addVertex(this.getPosX(), this.getPosY(), 0.0D);
		tessellator.draw();
		GL11.glColor4f(0.0F, 0.0F, 0.0F, transparency);
		int blackValue = Colors.BLACK.getDarkValue();
		if (this.borders) {
			Utils.displayGradientColor(this.getPosX() - 1, this.getPosY() - 1, this.getPosX(), this.getPosY() + this.getHeight() + 1, blackValue, blackValue);
			Utils.displayGradientColor(this.getPosX() + this.getWidth(), this.getPosY(), this.getPosX() + this.getWidth() + 1, this.getPosY() + this.getHeight(), blackValue, blackValue);
			Utils.displayGradientColor(this.getPosX(), this.getPosY() - 1, this.getPosX() + this.getWidth() + 1, this.getPosY(), blackValue, blackValue);
			Utils.displayGradientColor(this.getPosX(), this.getPosY() + this.getHeight(), this.getPosX() + this.getWidth() + 1, this.getPosY() + this.getHeight() + 1, blackValue, blackValue);
		}
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		Minecraft.getMinecraft().currentScreen.drawGradientRect(this.getPosX() + this.getWidth(), this.getPosY() + this.getHeight(), this.getPosX(), this.getPosY(), Integer.MIN_VALUE, 0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
	}
}
