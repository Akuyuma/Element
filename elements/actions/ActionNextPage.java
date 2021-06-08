package fr.senoria.client.features.eco.guis.elements.actions;

import fr.senoria.client.features.eco.guis.screens.Pagination;

public class ActionNextPage extends Action {

	private Pagination current;
	
	public ActionNextPage(Pagination current) {
		this.current = current;
	}
	
	@Override
	public void execute() {
		int page = this.current.getPage();
		if (page == this.current.getTotalPages()) return;
		page += 1;
		this.current.setPage(page);
	}
}
