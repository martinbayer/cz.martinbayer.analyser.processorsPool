package cz.martinbayer.analyser.processorsPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import cz.martinbayer.analyser.processors.IProcessorItemWrapper;
import cz.martinbayer.analyser.processors.model.IE4LogsisLog;

public class ProcessorsPool {

	private static ProcessorsPool pool;
	private List<IProcessorItemWrapper<IE4LogsisLog>> processors;
	private List<Bundle> processorsBundles;

	public void initialize(BundleContext ctx) {
		processors = new ArrayList<>();
		processorsBundles = new ArrayList<>();
		initializeFromBundles(ctx);
	}

	public static synchronized ProcessorsPool getInstance() {
		if (pool == null) {
			pool = new ProcessorsPool();
		}
		return pool;
	}

	private void initializeFromBundles(BundleContext ctx) {
		ArrayList<IProcessorItemWrapper<IE4LogsisLog>> services = new ArrayList<>();
		ServiceReference<?>[] ref;

		try {
			ref = ctx.getServiceReferences(
					IProcessorItemWrapper.class.getName(), null);
			if (ref == null) {
				return;
			}
			for (ServiceReference<?> sr : ref) {
				IProcessorItemWrapper<IE4LogsisLog> service = (IProcessorItemWrapper<IE4LogsisLog>) ctx
						.getService(sr);
				services.add(service);
				/* save the reference to every processor's bundle */
				processorsBundles.add(sr.getBundle());
			}
			for (IProcessorItemWrapper<IE4LogsisLog> s : services) {
				processors.add(s);
			}
		} catch (InvalidSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<IProcessorItemWrapper<IE4LogsisLog>> getProcessors() {
		if (processors == null) {
			throw new NullPointerException(
					"ProcessorsPool not initialized. Initialize the pool with initialize(BundleContext ctx) method");
		}
		return Collections.unmodifiableList(processors);
	}

	public List<Bundle> getProcBundles() {
		if (processorsBundles == null) {
			throw new NullPointerException(
					"ProcessorsPool not initialized. Initialize the pool with initialize(BundleContext ctx) method");
		}
		return Collections.unmodifiableList(processorsBundles);
	}
}
