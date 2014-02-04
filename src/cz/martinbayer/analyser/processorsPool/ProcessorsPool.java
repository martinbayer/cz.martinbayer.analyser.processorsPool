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

	private void createFakeData() {
		InputProcessors inputProcessors = InputProcessors.getInstance();
		for (Entry<IProcessorsPaletteItem, IProcessorLogic<IXMLog>> item : inputProcessors.inputProcessors
				.entrySet()) {
			addProcessor(item.getKey(), item.getValue());
		}
	}

	public void initialize(BundleContext ctx) {
		processors = new ArrayList<>();
		initializeFromBundles(ctx);
	}

	public static synchronized ProcessorsPool getInstance() {
		if (pool == null) {
			pool = new ProcessorsPool();
		}
		return pool;
	}

	private void initializeFromBundles(BundleContext ctx) {
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
			for (IProcessorItemWrapper<IXMLog> s : services) {
				processors.add(s);
			}
		} catch (InvalidSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<IProcessorItemWrapper<IXMLog>> getProcessors() {
		if (processors == null) {
			throw new NullPointerException(
					"ProcessorsPool not initialized. Initialize the pool with initialize(BundleContext ctx) method");
		}
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
