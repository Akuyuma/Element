package fr.senoria.client.features.eco.guis.screens;

import java.awt.Color;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.GuiScreen;

@Getter @Setter
public abstract class Pagination extends IGuiScreen {

	private int[] datas;
	private int page, maxItemPerPage = 1, currentData, currentObject, maximumData;
	private String key;
	private static List content;

	public Pagination() {
		this.page = 1;
	}

	public void initPagination(String key, List content, int maxItemPerPage) {
		this.key = key;
		this.maxItemPerPage = maxItemPerPage;
		this.content = content;
	}

	public static int getBlackCustomColor() {
		return new Color(40, 40, 40, 200).getRGB();
	}

	public void reset() {
		int size = this.content.size();
		if(this.currentObject >= size) {
			this.currentObject = (size - 1);
		}
	}

	public abstract void contentPage();
	public void preDisplayPage() {}

	public void displayPage() {
		this.preDisplayPage();
		int totalPages = this.getTotalPages();
		if(this.page > totalPages) {
			this.page = totalPages;
		}
		this.clearOld();
		this.reset();
		int objects = 0;
		this.datas = this.createPageData(this.content.size(), this.maxItemPerPage, this.page);
		for(int count = this.datas[0]; count < this.datas[1]; count++) {
			this.contentPage();
			this.currentData = objects;
			objects++;
			this.currentObject = count;
		}
		//Correction de l'affichage des objets
		this.maximumData = this.maxItemPerPage;
		if(this.currentData != this.maxItemPerPage) {
			this.maximumData = this.currentData;
		}
	}

	public void clearOld() {
		if(this.getKey() != null) {
			this.clearOld(this.getKey());
		}
	}

	//Changement du système de pages maximums
	public int getTotalPages() {
		int size = this.content.size() - 1;
		return size <= this.maxItemPerPage ? 1 : (size / this.maxItemPerPage) + 1;
	}

	public int[] createPageData(int contentSize, int maxItemPerMage, int current) {
		int[] datas = new int[2];
		datas[0] = current == 1 ? 0 : maxItemPerMage * (current - 1);
		datas[1] = current == 1 ? maxItemPerMage : maxItemPerMage * current;
		if (datas[1] > contentSize) {
			datas[1] = contentSize;
		}
		return datas;
	}
}
