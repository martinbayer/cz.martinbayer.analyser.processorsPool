package cz.martinbayer.analyser.processorsPool.fakedata;

import cz.martinbayer.analyser.processors.model.XMLog;

public class FakeXMLog extends XMLog {

	private String processorName;

	public String getProcessorName() {
		return processorName;
	}

	public void setProcessorName(String processorName) {
		this.processorName = processorName;
	}

}
