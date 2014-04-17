package cz.martinbayer.analyser.processorsPool;

import org.eclipse.swt.events.MouseEvent;

import cz.martinbayer.analyser.processors.IProcessorItemWrapper;
import cz.martinbayer.analyser.processors.IProcessorLogic;
import cz.martinbayer.analyser.processors.IProcessorsPaletteItem;
import cz.martinbayer.analyser.processors.model.IE4LogsisLog;

/**
 * used to wrap GUI and logic part of the processor plugin
 * 
 * @author Martin
 * 
 */
public class ProcessorPluginWrapper implements IProcessorItemWrapper<IE4LogsisLog> {

	private IProcessorsPaletteItem paletteItem;
	private IProcessorLogic<IE4LogsisLog> processorLogic;

	public ProcessorPluginWrapper(IProcessorsPaletteItem paletteItem,
			IProcessorLogic<IE4LogsisLog> processorLogic) {
		this.paletteItem = paletteItem;
		this.processorLogic = processorLogic;
	}

	public void setPaletteItem(IProcessorsPaletteItem paletteItem) {
		this.paletteItem = paletteItem;
	}

	@Override
	public IProcessorLogic<IE4LogsisLog> getProcessorLogic() {
		return processorLogic;
	}

	public void setProcessorLogic(IProcessorLogic<IE4LogsisLog> processorLogic) {
		this.processorLogic = processorLogic;
	}

	@Override
	public IProcessorsPaletteItem getProcessorPaletteItem() {
		return paletteItem;
	}

	@Override
	public void mouseDoubleClicked(MouseEvent e) {
		System.out.println(this.processorLogic.getProcessor()
				.getNextProcsCount());
	}

	@Override
	public IProcessorItemWrapper<IE4LogsisLog> getInstance() {
		return new ProcessorPluginWrapper(paletteItem, processorLogic);
	}

}
