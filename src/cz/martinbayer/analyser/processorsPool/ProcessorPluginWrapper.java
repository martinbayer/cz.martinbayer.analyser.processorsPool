package cz.martinbayer.analyser.processorsPool;

import cz.martinbayer.e4.analyser.description.gui.ProcessorsPaletteItem;
import cz.martinbayer.e4.analyser.description.logic.ProcessorLogic;

/**
 * used to wrap GUI and logic part of the processor plugin
 * 
 * @author Martin
 * 
 */
public class ProcessorPluginWrapper {

	private ProcessorsPaletteItem paletteItem;
	private ProcessorLogic processorLogic;

	public ProcessorPluginWrapper(ProcessorsPaletteItem paletteItem,
			ProcessorLogic processorLogic) {
		this.paletteItem = paletteItem;
		this.processorLogic = processorLogic;
	}

	public ProcessorsPaletteItem getPaletteItem() {
		return paletteItem;
	}

	public void setPaletteItem(ProcessorsPaletteItem paletteItem) {
		this.paletteItem = paletteItem;
	}

	public ProcessorLogic getProcessorLogic() {
		return processorLogic;
	}

	public void setProcessorLogic(ProcessorLogic processorLogic) {
		this.processorLogic = processorLogic;
	}

}
