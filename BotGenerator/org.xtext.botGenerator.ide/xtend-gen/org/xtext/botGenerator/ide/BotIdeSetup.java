/**
 * generated by Xtext 2.19.0
 */
package org.xtext.botGenerator.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.xtext.util.Modules2;
import org.xtext.botGenerator.BotRuntimeModule;
import org.xtext.botGenerator.BotStandaloneSetup;
import org.xtext.botGenerator.ide.BotIdeModule;

/**
 * Initialization support for running Xtext languages as language servers.
 */
@SuppressWarnings("all")
public class BotIdeSetup extends BotStandaloneSetup {
  @Override
  public Injector createInjector() {
    BotRuntimeModule _botRuntimeModule = new BotRuntimeModule();
    BotIdeModule _botIdeModule = new BotIdeModule();
    return Guice.createInjector(Modules2.mixin(_botRuntimeModule, _botIdeModule));
  }
}
