package fr.senoria.client.features.eco.guis.elements.buttons;

import java.util.List;

import fr.senoria.client.features.eco.guis.elements.actions.Action;
import fr.senoria.client.features.eco.resources.Resource;
import fr.senoria.client.features.eco.utils.Colors;
import fr.senoria.client.features.eco.utils.Utils;
import lombok.Getter;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.common.collect.Lists;

import lombok.Setter;
import net.minecraft.client.renderer.RenderHelper;

@Getter
public class ElementTexturedButton extends ElementButton {

	private @Setter Resource resource, hoverResource;
	private List<String> lines;
	private @Setter boolean cancel;
	private @Setter boolean side;

	public ElementTexturedButton(Action action, Resource resource, int posX, int posY, int width, int height) {
		this(action, resource, "", posX, posY, width, height);
	}

	public ElementTexturedButton(Action action, Resource resource, String title, int posX, int posY, int width, int height) {
		super(action, title, posX, posY, width, height, Colors.WHITE);
		this.resource = resource;
		this.lines = Lists.newArrayList();
		this.side = true;
	}

	public void integrateItemDescription(ItemStack item) {
		List descriptionLines = item.getTooltip(Utils.minecraft.thePlayer, Utils.minecraft.gameSettings.advancedItemTooltips);
		for(int count = 0; count < descriptionLines.size(); count++) {
			String descriptionLine = (String) descriptionLines.get(count);
			String finalLine = "&7" + descriptionLine;
			if (count == 0) {
				finalLine = item.getRarity().rarityColor + descriptionLine;
			}
			this.lines.add(Utils.color(finalLine));
		}
	}

	public void addLines(String... lines) {
		for(String line : lines) {
			this.lines.add(Utils.color(line));
		}
	}

	@Override
	public void display() {
		Colors color = this.isHovered() ? Colors.LIGHT_GRAY : this.getColor();
		//Resource currentResource = !this.isActive() ? this.resource : (this.isHovered() ? (this.hoverResource != null ? this.hoverResource : this.resource) : this.resource);
		Utils.displayIcon(color.getLightValue(), this.resource, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
		this.modifiers();
		if(!this.lines.isEmpty() && !this.cancel) {
			if(this.isHovered()) {
				int height = this.lines.size() * 10;
				if(this.side) {
					Utils.minecraft.currentScreen.func_146283_a(this.lines, this.getPosX() - 10 - Utils.getListWidth(this.lines), this.getPosY() + 35 - height);
				} else {
					Utils.minecraft.currentScreen.func_146283_a(this.lines, this.getPosX()  + 25, this.getPosY() + 20);
				}
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				RenderHelper.disableStandardItemLighting();
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_DEPTH_TEST);
			}
		}
	}
}
