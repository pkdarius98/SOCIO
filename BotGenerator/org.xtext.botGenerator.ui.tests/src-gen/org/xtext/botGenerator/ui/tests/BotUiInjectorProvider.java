/*
 * generated by Xtext 2.19.0
 */
package org.xtext.botGenerator.ui.tests;

import com.google.inject.Injector;
import org.eclipse.xtext.testing.IInjectorProvider;
import org.xtext.botGenerator.ui.internal.BotGeneratorActivator;

public class BotUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return BotGeneratorActivator.getInstance().getInjector("org.xtext.botGenerator.Bot");
	}

}
