package fr.senoria.client.features.eco.guis.elements.actions;

import fr.senoria.client.features.eco.utils.Utils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;

public class ActionConnect extends Action {

	private GuiScreen screen;
	private ServerData data;
	
	public ActionConnect(GuiScreen screen, ServerData data) {
		this.screen = screen;
		this.data = data;
	}
	
	public ActionConnect(GuiScreen screen, String adress, int port) {
		this(screen, new ServerData("Senoria", adress + ":" + port));
	}
	
	@Override
	public void execute() {
		Utils.displayScreen(new GuiConnecting(this.screen, Utils.minecraft, this.data));
	}

}
