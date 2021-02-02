package havis.device.io.empty.osgi;

import havis.device.io.IODevice;
import havis.device.io.common.CommunicationHandler;
import havis.device.io.common.MainController;

import java.io.IOException;
import java.util.logging.Logger;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	Logger log = Logger.getLogger(Activator.class.getName());
	private ServiceRegistration<?> serviceRegistration;

	@Override
	public void start(BundleContext context) throws Exception {
		registerService(context);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		unregisterService();
	}

	private void registerService(BundleContext context) throws IOException {
		if (serviceRegistration == null) {
			serviceRegistration = context.registerService(IODevice.class.getName(), new ServiceFactory<IODevice>() {
				@Override
				public IODevice getService(Bundle bundle, ServiceRegistration<IODevice> registration) {
					ClassLoader current = Thread.currentThread().getContextClassLoader();
					try {
						Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
						return new CommunicationHandler();
					} finally {
						Thread.currentThread().setContextClassLoader(current);
					}
				}

				@Override
				public void ungetService(Bundle bundle, ServiceRegistration<IODevice> registration, IODevice service) {
					/* RFU */
				}
			}, null);
		}
	}

	private void unregisterService() {
		MainController.dispose();
		if (serviceRegistration != null)
			serviceRegistration.unregister();
	}
}
