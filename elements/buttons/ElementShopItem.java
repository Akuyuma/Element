package fr.senoria.client.features.eco.guis.elements.buttons;

import fr.senoria.client.features.eco.guis.elements.ElementItem;
import fr.senoria.client.features.eco.guis.elements.actions.Action;
import fr.senoria.client.features.eco.guis.elements.texts.ElementText;
import fr.senoria.client.features.eco.guis.elements.texts.TextSize;
import fr.senoria.client.features.eco.resources.Resource;
import fr.senoria.client.features.eco.shops.ShopItem;
import fr.senoria.client.features.eco.utils.Colors;
import fr.senoria.client.features.eco.utils.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ElementShopItem extends ElementTexturedButton {

	private ShopItem item;
	
	public ElementShopItem(Action action, ShopItem item, int posX, int posY, int width, int height) {
		super(action, Resource.AUCTION_ITEM_STAND, posX, posY, width, height);
		this.item = item;
		this.setSecondColor(Colors.WHITE);
	}

	@Override
	public void display() {	
		super.display();
	    //Items
		ElementItem item = new ElementItem(this.item.getItem(), this.getPosX() + 5, this.getPosY() + 9);
		item.display();
	}

	public void modifiers() {
		//Title
		String nameValue = Utils.ellipsis("&7" + this.item.getItem().getDisplayName(), 14);
		
		ElementText name = new ElementText(nameValue, this.getPosX() + 26, this.getPosY() + 14);
		name.setSize(TextSize.SMALL);
		name.display();
	}
}
