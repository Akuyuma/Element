package fr.senoria.client.features.eco.guis.screens;

import java.util.List;

import fr.senoria.client.features.eco.guis.elements.Element;
import fr.senoria.client.features.eco.guis.elements.buttons.ElementButton;
import fr.senoria.client.features.eco.guis.elements.ElementTextBox;
import fr.senoria.client.features.eco.guis.elements.actions.Action;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

@Getter
public abstract class IGuiScreen extends GuiScreen {

	private List<Element> elements;
	private ElementButton currentButton;
	private @Setter boolean canClose;

	public IGuiScreen() {
		this.elements = Lists.newArrayList();
		this.canClose = true;
	}

	public void addElement(Element element) {
		this.addElement(element.toString(), element);
	}

	public void addElement(String key, Element element) {
		Element current = this.getElement(key);
		if(current != null) {
			return;
		}
		element.setUniqueId(key);
		this.elements.add(element);
	}

	public void clearOld(String key) {
		List<Element> deletedElements = Lists.newArrayList();
		if(this.elements.isEmpty()) {
			return;
		}
		for(Element current : this.elements) {
			String uniqueId = current.getUniqueId();
			if(uniqueId.startsWith(key)) {
				deletedElements.add(current);
			}
		}
		this.elements.removeAll(deletedElements);
	}

	public void setElementsStatus(String key, boolean value) {
		for(Element current : this.elements) {
			if(current.getUniqueId().startsWith(key)) {
				current.setActive(value);
			}
		}
	}

	public Element getElement(String uniqueId) {
		for(Element current : this.elements) {
			if(current.getUniqueId().equalsIgnoreCase(uniqueId)) {
				return current;
			}
		}
		return null;
	}

	public List<Element> getElementsByPriority(boolean priority) {
		List<Element> elementsByPriority = Lists.newArrayList();
		for(Element element : this.elements) {
			if(element.isPriority() == priority) {
				elementsByPriority.add(element);
			}
		}
		return elementsByPriority;
	}
	public abstract void initInterface();
	public abstract void updateInterface();
	public abstract void displayObjects();

	public void hoveredObjects() {}

	public int getSplitWidth() {
		return this.width / 2;
	}
	public int getSplitHeight() {
		return this.height / 2;
	}

	public void displayDefaultBackground() {
		this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
	}

	/**
	 * ----------------------------------------------------------
	 * 					 Minecraft - GuiScreen
	 * ----------------------------------------------------------
	 */

	public void initGui() {

		if(!this.elements.isEmpty()) {
			this.elements.clear();
		}
		Keyboard.enableRepeatEvents(true);
		this.initInterface();
		super.initGui();
	}

	public void updateScreen() {
		super.updateScreen();
		List<Element> refreshedElements = Lists.newArrayList(this.elements);
		for (Element element : refreshedElements) {
			if (element instanceof ElementTextBox) {
				ElementTextBox textBox = (ElementTextBox) element;
				GuiTextField field = textBox.getTextField();
				field.updateCursorCounter();
			}
		}
		this.updateInterface();
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	public void drawScreen(int mouseX, int mouseY, float updateTicks) {
		this.displayObjects();
		for(Element element : this.getElementsByPriority(false)) {
			if(element.isVisible()) {
				element.display();
			}
		}
		this.hoveredObjects();
		for(Element element : this.getElementsByPriority(true)) {
			if(element.isVisible()) {
				element.display();
			}
		}
		super.drawScreen(mouseX, mouseY, updateTicks);
	}

	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		List<Element> refreshedElements = Lists.newArrayList(this.elements);
		for (Element element : refreshedElements) {
			if(element.isActive() && element.isVisible()) {
				if (element instanceof ElementTextBox) {
					ElementTextBox textBox = (ElementTextBox) element;
					GuiTextField field = textBox.getTextField();
					field.mouseClicked(mouseX, mouseY, mouseButton);
				} else if (element instanceof ElementButton) {
					ElementButton button = (ElementButton) element;
					if (button.isPressed()) {
						this.currentButton = button;
						this.mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
						Action action = button.getAction();
						if (action != null) {
							action.execute();
						}
					}
				}
			}
		}
	}

	public void mouseMovedOrUp(int mouseX, int mouseY, int state) {
		if (this.currentButton != null && state == 0) {
			this.currentButton = null;
		}
		super.mouseMovedOrUp(mouseX, mouseY, state);
	}

	protected void keyTyped(char character, int key) {
		if(this.canClose) {
			super.keyTyped(character, key);
		}
		List<Element> refreshedElements = Lists.newArrayList(this.elements);
		for (Element element : refreshedElements) {
			if (element instanceof ElementTextBox) {
				ElementTextBox textBox = (ElementTextBox) element;
				GuiTextField field = textBox.getTextField();
				if (field.getText().equalsIgnoreCase(textBox.getDefaultText())) {
					field.setText("");
				}
				field.textboxKeyTyped(character, key);
				ElementTextBox.TextUpdate textUpdate = textBox.getOnTextUpdate();
				if (textUpdate != null) {
					textUpdate.onTextUpdate(textBox.getTextField().getText());
				}
			}
		}
	}
}
