package fr.senoria.client.features.eco.guis.elements.actions;

import fr.senoria.client.features.eco.utils.Utils;
import net.minecraft.client.gui.GuiScreen;

public class ActionOpenInterface extends Action {

	private GuiScreen screen;
	
	public ActionOpenInterface(GuiScreen screen) {
		this.screen = screen;
	}
	 
	@Override
	public void execute() {
		Utils.displayScreen(this.screen);
	}
}
