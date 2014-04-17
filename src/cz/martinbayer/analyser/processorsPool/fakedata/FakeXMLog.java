package cz.martinbayer.analyser.processorsPool.fakedata;

import cz.martinbayer.analyser.processors.model.E4LogsisLog;

public class FakeXMLog extends E4LogsisLog {

	private String processorName;

	public String getProcessorName() {
		return processorName;
	}

	public void setProcessorName(String processorName) {
		this.processorName = processorName;
	}

}
