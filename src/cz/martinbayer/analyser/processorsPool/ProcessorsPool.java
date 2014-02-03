package cz.martinbayer.analyser.processorsPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import cz.martinbayer.analyser.processors.IProcessorItemWrapper;
import cz.martinbayer.analyser.processors.IProcessorLogic;
import cz.martinbayer.analyser.processors.IProcessorsPaletteItem;
import cz.martinbayer.analyser.processors.model.IXMLog;
import cz.martinbayer.analyser.processorsPool.fakedata.InputProcessors;

public class ProcessorsPool {

	private static ProcessorsPool pool;
	private List<IProcessorItemWrapper<IXMLog>> processors;

	private ProcessorsPool() {
		initialize();
		createFakeData();
	}

	private void createFakeData() {
		InputProcessors inputProcessors = InputProcessors.getInstance();
		for (Entry<IProcessorsPaletteItem, IProcessorLogic<IXMLog>> item : inputProcessors.inputProcessors
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

	public int initializeFromBundles(BundleContext ctx) {
		ArrayList<IProcessorItemWrapper<IXMLog>> services = new ArrayList<>();
		ServiceReference<?>[] ref;

		try {
			ref = ctx.getServiceReferences(
					IProcessorItemWrapper.class.getName(), null);

			for (ServiceReference<?> sr : ref) {
				IProcessorItemWrapper<IXMLog> service = (IProcessorItemWrapper<IXMLog>) ctx
						.getService(sr);
				services.add(service);
			}
			StringBuffer sb = new StringBuffer();
			sb.append("starting").append("\n");
			for (IProcessorItemWrapper<IXMLog> s : services) {
				sb.append(s.getProcessorPaletteItem().getLabel());
			}
			System.out.println(sb.toString());
		} catch (InvalidSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public List<IProcessorItemWrapper<IXMLog>> getProcessors() {
		return processors;
	}

	public void addProcessor(IProcessorsPaletteItem paletteItem,
			IProcessorLogic<IXMLog> processorLogic) {
		ProcessorPluginWrapper item = new ProcessorPluginWrapper(paletteItem,
				processorLogic);
		if (!processors.contains(item)) {
			processors.add(item);
		}
	}
}
