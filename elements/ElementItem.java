package fr.senoria.client.features.eco.guis.elements;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import fr.senoria.client.features.eco.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

@Getter
public class ElementItem extends Element {

	private Minecraft minecraft = Minecraft.getMinecraft();
	private ItemStack item;
	private @Setter boolean side, hoveredInformations;
	private @Setter int heightModifier;
	
	public ElementItem(ItemStack item, int posX, int posY) {
		super(posX, posY, 16, 16);
		this.item = item;
		this.side = true;
		this.hoveredInformations = true;
	}
	
	public void renderItem(ItemStack item, int posX, int posY, boolean overlay) {
		RenderItem itemRenderer = new RenderItem();
		itemRenderer.renderItemIntoGUI(this.minecraft.fontRenderer, this.minecraft.getTextureManager(), item, posX, posY);
		if (overlay) {
			itemRenderer.renderItemOverlayIntoGUI(this.minecraft.fontRenderer, this.minecraft.getTextureManager(), item, posX, posY);
		}
		RenderHelper.disableStandardItemLighting();
	}

	@Override
	public void display() {
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		if(this.item != null) {
			this.renderItem(item, this.getPosX(), this.getPosY(), true);
			if (this.isHovered() && this.hoveredInformations) {
				List<String> lines = item.getTooltip(minecraft.thePlayer, minecraft.gameSettings.advancedItemTooltips);
				int height = lines.size() * 10;
				if(this.side) {
					int width = Utils.getListWidth(lines);
					this.minecraft.currentScreen.renderToolTip(item, this.getPosX() - 5 - width, this.getPosY() + 20 + heightModifier - height);
				} else {
					this.minecraft.currentScreen.renderToolTip(item, this.getPosX()  + 25, this.getPosY() + 20 + heightModifier - height);
				}
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_DEPTH_TEST);
			}
		}
	}
}