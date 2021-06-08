package fr.senoria.client.features.eco.guis.elements.buttons;

import fr.senoria.client.features.eco.guis.elements.ElementItem;
import fr.senoria.client.features.eco.guis.elements.actions.Action;
import fr.senoria.client.features.eco.guis.elements.buttons.ElementButton;
import fr.senoria.client.features.eco.utils.Colors;
import fr.senoria.client.features.eco.utils.Utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ElementButtonTextured extends ElementButton {

	private ItemStack item;
	
	public ElementButtonTextured(Item item, Action action, String title, int posX, int posY, int width, int height, Colors color) {
		super(action, title, posX, posY, width, height, color);
		this.item = new ItemStack(item);
		this.item.setStackDisplayName(Utils.color(title));
	}
	
	@Override
	public void display() {
		super.display();
		ElementItem item = new ElementItem(this.item, this.getPosX() + 3, this.getPosY() + 3);
		item.setSide(true);
		item.display();
	}
	
	public void modifiers() {}
}
