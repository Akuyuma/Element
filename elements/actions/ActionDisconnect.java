package fr.senoria.client.features.eco.guis.elements.actions;

import fr.senoria.client.features.eco.utils.Utils;
import fr.senoria.client.guis.menu.GuiHome;
import net.minecraft.client.multiplayer.WorldClient;

public class ActionDisconnect extends Action {

	@Override
	public void execute() {
		Utils.minecraft.theWorld.sendQuittingDisconnectingPacket();
		Utils.minecraft.loadWorld((WorldClient)null);
		Utils.minecraft.displayGuiScreen(new GuiHome());
	}
}
