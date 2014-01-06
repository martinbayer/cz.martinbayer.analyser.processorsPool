package cz.martinbayer.analyser.processorsPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import cz.martinbayer.analyser.processorsPool.fakedata.InputProcessors;
import cz.martinbayer.e4.analyser.description.gui.ProcessorsPaletteItem;
import cz.martinbayer.e4.analyser.description.logic.ProcessorLogic;

public class ProcessorsPool {

	private static ProcessorsPool pool;
	private List<ProcessorPluginWrapper> processors;

	private ProcessorsPool() {
		initialize();
		createFakeData();
	}

	private void createFakeData() {
		InputProcessors inputProcessors = InputProcessors.getInstance();
		for (Entry<ProcessorsPaletteItem, ProcessorLogic> item : inputProcessors.inputProcessors
				.entrySet()) {
			addProcessor(item.getKey(), item.getValue());
		}
	}

	private void initialize() {
		processors = new ArrayList<>();
	}

	public static synchronized ProcessorsPool getInstance() {
		if (pool == null) {
			pool = new ProcessorsPool();
		}
		return pool;
	}

	public List<ProcessorPluginWrapper> getProcessors() {
		return processors;
	}

	public void addProcessor(ProcessorsPaletteItem paletteItem,
			ProcessorLogic processorLogic) {
		ProcessorPluginWrapper item = new ProcessorPluginWrapper(paletteItem,
				processorLogic);
		if (!processors.contains(item)) {
			processors.add(item);
		}
	}
}
