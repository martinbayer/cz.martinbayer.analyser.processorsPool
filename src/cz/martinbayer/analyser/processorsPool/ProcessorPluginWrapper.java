package cz.martinbayer.analyser.processorsPool;

import cz.martinbayer.analyser.processors.IProcessorItemWrapper;
import cz.martinbayer.analyser.processors.IProcessorLogic;
import cz.martinbayer.analyser.processors.IProcessorsPaletteItem;
import cz.martinbayer.analyser.processors.model.IXMLog;

/**
 * used to wrap GUI and logic part of the processor plugin
 * 
 * @author Martin
 * 
 */
public class ProcessorPluginWrapper implements IProcessorItemWrapper<IXMLog> {

	private IProcessorsPaletteItem paletteItem;
	private IProcessorLogic<IXMLog> processorLogic;

	public ProcessorPluginWrapper(IProcessorsPaletteItem paletteItem,
			IProcessorLogic<IXMLog> processorLogic) {
		this.paletteItem = paletteItem;
		this.processorLogic = processorLogic;
	}

	public void setPaletteItem(IProcessorsPaletteItem paletteItem) {
		this.paletteItem = paletteItem;
	}

	@Override
	public IProcessorLogic<IXMLog> getProcessorLogic() {
		return processorLogic;
	}

	public void setProcessorLogic(IProcessorLogic<IXMLog> processorLogic) {
		this.processorLogic = processorLogic;
	}

	@Override
	public IProcessorsPaletteItem getProcessorPaletteItem() {
		return paletteItem;
	}

}
