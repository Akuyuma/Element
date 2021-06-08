package fr.senoria.client.features.eco.guis.elements.actions;

import net.minecraft.client.Minecraft;

public class ActionCloseClient extends Action {

    protected Minecraft mc;
    
	@Override
	public void execute() {
		this.mc.shutdown();
	}

}
