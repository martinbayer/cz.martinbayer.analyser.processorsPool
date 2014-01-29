package cz.martinbayer.analyser.processorsPool.fakedata;

import java.net.URL;
import java.util.HashMap;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import cz.martinbayer.analyser.processors.IProcessorLogic;
import cz.martinbayer.analyser.processors.IProcessorsPaletteItem;
import cz.martinbayer.analyser.processors.model.IXMLog;
import cz.martinbayer.analyser.processors.model.XMLogData;
import cz.martinbayer.analyser.processors.types.InputProcessor;
import cz.martinbayer.analyser.processors.types.LogProcessor;
import cz.martinbayer.utils.ImageUtils;

public class InputProcessors {

	public HashMap<IProcessorsPaletteItem, IProcessorLogic<IXMLog>> inputProcessors = new HashMap<>();

	private InputProcessors() {
		initialize();
	}

	private void initialize() {
		final InputProcessor1 ip1 = new InputProcessor1("input processor 1");
		IProcessorLogic<IXMLog> ip1Logic = new IProcessorLogic<IXMLog>() {

			@Override
			public LogProcessor<IXMLog> getProcessor() {
				return ip1;
			}
		};

		IProcessorsPaletteItem ip1Item = new IProcessorsPaletteItem() {

			private Class<? extends LogProcessor<? extends IXMLog>> type;

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
			public void setType(
					Class<? extends LogProcessor<? extends IXMLog>> type) {
				this.type = type;
			}

			@Override
			public String getLabel() {
				return "Label for ip1";
			}

			@Override
			public Class<? extends LogProcessor<? extends IXMLog>> getType() {
				return this.type;
			}
		};
		inputProcessors.put(ip1Item, ip1Logic);

		final InputProcessor2 ip2 = new InputProcessor2("input processor 2");

		IProcessorLogic<IXMLog> ip2Logic = new IProcessorLogic<IXMLog>() {

			@Override
			public LogProcessor<IXMLog> getProcessor() {
				return ip2;
			}
		};

		IProcessorsPaletteItem ip2Item = new IProcessorsPaletteItem() {

			private Class<? extends LogProcessor<? extends IXMLog>> type;

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
			public Class<? extends LogProcessor<? extends IXMLog>> getType() {
				return this.type;
			}

			@Override
			public void setType(
					Class<? extends LogProcessor<? extends IXMLog>> type) {
				this.type = type;
			}

			@Override
			public String getLabel() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		inputProcessors.put(ip2Item, ip2Logic);
	}

	public static InputProcessors getInstance() {
		return new InputProcessors();
	}

}

class InputProcessor1 extends InputProcessor<IXMLog> {
	XMLogData<IXMLog> data;
	private String name;

	public InputProcessor1(String name) {
		this.name = name;
	}

	@Override
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
		for (IXMLog log : logData.getLogRecords()) {
			if (log instanceof FakeXMLog) {
				((FakeXMLog) log).setProcessorName(((FakeXMLog) log)
						.getProcessorName().concat("...processed"));
			}
		}
	}
}

class InputProcessor2 extends InputProcessor<IXMLog> {
	XMLogData<IXMLog> data;
	private String name;

	public InputProcessor2(String name) {
		this.name = name;
	}

	@Override
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
		for (IXMLog log : logData.getLogRecords()) {
			if (log instanceof FakeXMLog) {
				((FakeXMLog) log).setProcessorName(((FakeXMLog) log)
						.getProcessorName().concat("...processed"));
			}
		}
	}

}