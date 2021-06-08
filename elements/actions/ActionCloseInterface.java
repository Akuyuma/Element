package fr.senoria.client.features.eco.guis.elements.actions;

import fr.senoria.client.features.eco.utils.Utils;



public class ActionCloseInterface extends Action {

	@Override
	public void execute() {
		Utils.closeCurrentScreen();
	}

}
