package fr.senoria.client.features.eco.guis.elements.buttons;

import fr.senoria.client.features.eco.auctions.AuctionItem;
import fr.senoria.client.features.eco.guis.elements.ElementItem;
import fr.senoria.client.features.eco.guis.elements.actions.Action;
import fr.senoria.client.features.eco.guis.elements.texts.ElementText;
import fr.senoria.client.features.eco.guis.elements.texts.TextSize;
import fr.senoria.client.features.eco.resources.Resource;
import fr.senoria.client.features.eco.utils.Colors;
import fr.senoria.client.features.eco.utils.DurationFormatter;
import fr.senoria.client.features.eco.utils.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ElementAuctionItem  extends ElementTexturedButton {

	private AuctionItem item;
	private ElementItem stand;

	public ElementAuctionItem(Action action, AuctionItem item, int posX, int posY, int width, int height) {
		super(action, Resource.AUCTION_ITEM_STAND, posX, posY, width, height);
		this.item = item;
		this.setSecondColor(Colors.WHITE);
		String separator = "&7&m------------------------";
		this.addLines(separator, "&ePrix&7: &f" + this.item.getPrice() + "&6$", "&eVendeur&7: " + this.item.getOwner(), "&eExpiration&7: &6" + DurationFormatter.getRemaining(this.item.getExpirationMillis() - System.currentTimeMillis(), false),separator);
		this.stand = new ElementItem(this.item.getItem(), this.getPosX() + 4, this.getPosY() + 7);
	}

	@Override
	public void display() {
		super.display();
		//Items
		this.stand.setSide(this.isSide());
		this.stand.display();
	}

	public void modifiers() {
		if(this.stand.isHovered()) {
			this.setCancel(true);
		}
		//Title
		String nameValue = Utils.ellipsis("&7" + this.item.getItem().getDisplayName(), 13);
		ElementText name = new ElementText(nameValue, this.getPosX() + 24, this.getPosY() + 8);
		name.setSize(TextSize.SMALL);
		name.display();
		//Utils.displayString("&7" + this.item.getOwner(), this.getPosX() + 40, this.getPosY() + 16);
		String priceValue =Utils.ellipsis("&e" + this.item.getPrice() + "&6$", 13);
		ElementText price = new ElementText(priceValue, this.getPosX() + 24, this.getPosY() + 16);
		price.setSize(TextSize.SMALL);
		price.display();
		//Created
		//String expiration = this.item.hasExpired() ? "&cIndispo." : "&7" + DurationFormatter.getRemaining(this.item.getExpirationMillis() - System.currentTimeMillis(), true);
		//Utils.displayString(expiration, this.getPosX()  + this.getWidth() - Utils.getStringWidth(expiration) - 8, this.getPosY() + 12);
	}
}
