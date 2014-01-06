package cz.martinbayer.analyser.processorsPool.fakedata;

import java.net.URL;
import java.util.HashMap;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import cz.martinbayer.analyser.logic.model.XMLog;
import cz.martinbayer.analyser.logic.model.XMLogData;
import cz.martinbayer.analyser.logic.processor.InputProcessor;
import cz.martinbayer.analyser.logic.processor.LogProcessor;
import cz.martinbayer.e4.analyser.description.gui.ProcessorsPaletteItem;
import cz.martinbayer.e4.analyser.description.logic.ProcessorLogic;
import cz.martinbayer.utils.ImageUtils;

public class InputProcessors {

	public HashMap<ProcessorsPaletteItem, ProcessorLogic> inputProcessors = new HashMap<>();

	private InputProcessors() {
		initialize();
	}

	private void initialize() {
		final InputProcessor1 ip1 = new InputProcessor1("input processor 1");
		ProcessorLogic ip1Logic = new ProcessorLogic() {

			@Override
			public LogProcessor getProcessor() {
				return ip1;
			}
		};

		ProcessorsPaletteItem ip1Item = new ProcessorsPaletteItem() {

			private Class<? extends LogProcessor> type;

			@Override
			public ImageDescriptor getImageDescriptor() {
				return prepareImgDesc();
			}

			private ImageDescriptor prepareImgDesc() {
				Bundle bundle = FrameworkUtil.getBundle(this.getClass());
				URL url = FileLocator.find(bundle, new Path("icons/txt.png"),
						null);
				return ImageDescriptor.createFromURL(url);

			}

			@Override
			public ImageDescriptor getSmallImageDescriptor() {
				return ImageUtils.resize(prepareImgDesc(), 20, 20, true);
			}

			@Override
			public ImageDescriptor getImageDescriptor(int width, int height) {
				return getSmallImageDescriptor();
			}

			@Override
			public void setType(Class<? extends LogProcessor> clazz) {
				this.type = clazz;
			}

			@Override
			public Class<? extends LogProcessor> getType() {
				return this.type;
			}
		};
		inputProcessors.put(ip1Item, ip1Logic);

		final InputProcessor2 ip2 = new InputProcessor2("input processor 2");

		ProcessorLogic ip2Logic = new ProcessorLogic() {

			@Override
			public LogProcessor getProcessor() {
				return ip2;
			}
		};

		ProcessorsPaletteItem ip2Item = new ProcessorsPaletteItem() {

			private Class<? extends LogProcessor> type;

			@Override
			public ImageDescriptor getImageDescriptor() {
				return prepareImgDesc();
			}

			private ImageDescriptor prepareImgDesc() {
				Bundle bundle = FrameworkUtil.getBundle(this.getClass());
				URL url = FileLocator.find(bundle, new Path("icons/db.png"),
						null);
				return ImageDescriptor.createFromURL(url);

			}

			@Override
			public ImageDescriptor getSmallImageDescriptor() {
				return ImageUtils.resize(prepareImgDesc(), 20, 20, true);
			}

			@Override
			public ImageDescriptor getImageDescriptor(int width, int height) {
				return getSmallImageDescriptor();
			}

			@Override
			public void setType(Class<? extends LogProcessor> clazz) {
				this.type = clazz;
			}

			@Override
			public Class<? extends LogProcessor> getType() {
				return this.type;
			}
		};
		inputProcessors.put(ip2Item, ip2Logic);
	}

	public static InputProcessors getInstance() {
		return new InputProcessors();
	}

}

class InputProcessor1 extends InputProcessor {
	XMLogData<FakeXMLog> data;
	private String name;

	public InputProcessor1(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	protected void read() {
		data = new XMLogData<>();

		FakeXMLog log1 = new FakeXMLog();
		log1.setProcessorName("InputProcessor 1_1");
		data.addLogRecord(log1);

		FakeXMLog log2 = new FakeXMLog();
		log2.setProcessorName("InputProcessor 1_2");
		data.addLogRecord(log2);

		FakeXMLog log3 = new FakeXMLog();
		log3.setProcessorName("InputProcessor 1_3");
		data.addLogRecord(log3);

		setLogData(data);
	}

	@Override
	protected void process() {
		for (XMLog log : logData.getLogRecords()) {
			if (log instanceof FakeXMLog) {
				((FakeXMLog) log).setProcessorName(((FakeXMLog) log)
						.getProcessorName().concat("...processed"));
			}
		}
	}
}

class InputProcessor2 extends InputProcessor {
	XMLogData<FakeXMLog> data;
	private String name;

	public InputProcessor2(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	protected void read() {
		data = new XMLogData<>();

		FakeXMLog log1 = new FakeXMLog();
		log1.setProcessorName("InputProcessor 2_1");
		data.addLogRecord(log1);

		FakeXMLog log2 = new FakeXMLog();
		log2.setProcessorName("InputProcessor 2_2");
		data.addLogRecord(log2);

		FakeXMLog log3 = new FakeXMLog();
		log3.setProcessorName("InputProcessor 2_3");
		data.addLogRecord(log3);

		setLogData(data);
	}

	@Override
	protected void process() {
		for (XMLog log : logData.getLogRecords()) {
			if (log instanceof FakeXMLog) {
				((FakeXMLog) log).setProcessorName(((FakeXMLog) log)
						.getProcessorName().concat("...processed"));
			}
		}
	}

}