package fr.senoria.client.features.eco.guis.elements.actions;

import fr.senoria.client.features.eco.guis.screens.Pagination;

public class ActionLastPage extends Action {

	private Pagination current;
	
	public ActionLastPage(Pagination current) {
		this.current = current;
	}
	
	@Override
	public void execute() {
		int page = this.current.getPage();
		if (page <= 1) return;
		page -= 1;
		this.current.setPage(page);
	}
}
