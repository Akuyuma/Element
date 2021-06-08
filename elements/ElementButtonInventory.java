package fr.senoria.client.features.eco.guis.elements;

import fr.senoria.client.features.eco.guis.elements.actions.Action;
import fr.senoria.client.features.eco.guis.elements.buttons.ElementTexturedButton;
import fr.senoria.client.features.eco.resources.Resource;
import fr.senoria.client.features.eco.utils.Colors;
import fr.senoria.client.features.eco.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.item.ItemStack;

@Getter @Setter
public class ElementButtonInventory extends ElementTexturedButton {

	private ItemStack item;
	
	public ElementButtonInventory(Action action, ItemStack item, int posX, int posY, int width, int height) {
		super(action, Resource.BUTTON, posX, posY, width, height);
		this.item = item;
		this.setSecondColor(Colors.WHITE);
	}

	@Override
	public void display() {	
		super.display();
	    //Items
		ElementItem item = new ElementItem(this.item, this.getPosX() + 4, this.getPosY() + 3);
		item.setHoveredInformations(false);
		item.display();
	}

	public void modifiers() {
		//Title
		String nameValue = Utils.ellipsis(this.getTitle(), 18);
		Utils.displayString(nameValue,  this.getPosX() + 25, this.getPosY() + 7);		
	}
}
