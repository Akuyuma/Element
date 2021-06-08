package fr.senoria.client.features.eco.guis.elements.buttons;

import java.util.List;

import com.google.common.collect.Lists;

import fr.senoria.client.features.eco.guis.elements.actions.Action;
import fr.senoria.client.features.eco.utils.Colors;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ElementButtonQuantity extends ElementButton {

	private List<Integer> amounts = Lists.newArrayList(1, 2, 8, 16, 20, 32, 48, 64, 128, 256);
	private int amountSelected, quantity;
	private String prefix;
	
	public ElementButtonQuantity(Action action, String title, int posX, int posY, int width, int height) {
		super(action, title, posX, posY, width, height, Colors.WHITE);
		this.prefix = title;
		this.updateQuantity();
	}
	
	public void updateQuantity() {
		this.quantity = this.amounts.get(this.amountSelected);
		String finalTitle = this.prefix + "&lx" + quantity;
		this.setTitle(finalTitle);
	}
	
	@Override
	public void display() {	
		this.modifiers();
	}
}
