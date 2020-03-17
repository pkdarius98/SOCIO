/**
 * generated by Xtext 2.19.0
 */
package org.xtext.botGenerator.validation;

import com.google.common.base.Objects;
import generator.Action;
import generator.Bot;
import generator.BotInteraction;
import generator.Element;
import generator.GeneratorPackage;
import generator.HTTPRequest;
import generator.HTTPRequestToke;
import generator.HTTPResponse;
import generator.HTTPReturnType;
import generator.Intent;
import generator.Parameter;
import generator.ParameterReferenceToken;
import generator.Simple;
import generator.SimpleInput;
import generator.TrainingPhrase;
import generator.UserInteraction;
import java.util.ArrayList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;
import org.xtext.botGenerator.validation.AbstractBotValidator;

/**
 * This class contains custom validation rules.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
@SuppressWarnings("all")
public class BotValidator extends AbstractBotValidator {
  @Check
  public void checkHTTTPRequestTokenDataKey(final HTTPRequestToke httpRequestToken) {
    if (((!Objects.equal(httpRequestToken.getType(), HTTPReturnType.DATA)) && (!httpRequestToken.getDataKey().isEmpty()))) {
      this.warning("The data key is only using with the data type of http request", 
        GeneratorPackage.Literals.HTTP_REQUEST_TOKE__DATA_KEY, "invalid data key");
    }
  }
  
  @Check
  public void checkParameterReferences(final ParameterReferenceToken references) {
    EObject container = references.eContainer();
    if ((!(container instanceof TrainingPhrase))) {
      return;
    }
    EObject containercontainer = container.eContainer();
    if ((!(containercontainer instanceof Intent))) {
      return;
    }
    Intent intent = ((Intent) containercontainer);
    boolean _contains = intent.getParameters().contains(references.getParameter());
    boolean _not = (!_contains);
    if (_not) {
      this.error("This parameter is not from the list of intents parameters", 
        GeneratorPackage.Literals.PARAMETER_REFERENCE_TOKEN__PARAMETER);
    }
  }
  
  @Check
  public void flowPath(final Bot bot) {
    for (int i = 0; (i < bot.getFlows().size()); i++) {
      {
        UserInteraction current = bot.getFlows().get(i);
        for (int j = (i + 1); (j < bot.getFlows().size()); j++) {
          {
            UserInteraction nexts = bot.getFlows().get(j);
            Intent _intent = current.getIntent();
            Intent _intent_1 = nexts.getIntent();
            boolean _tripleEquals = (_intent == _intent_1);
            if (_tripleEquals) {
              String _name = current.getIntent().getName();
              String _plus = ("Only one path can start with the intent " + _name);
              this.error(_plus, 
                GeneratorPackage.Literals.BOT__FLOWS);
            }
          }
        }
      }
    }
  }
  
  @Check
  public void flowPathState(final BotInteraction state) {
    for (int i = 0; (i < state.getOutcoming().size()); i++) {
      {
        UserInteraction current = state.getOutcoming().get(i);
        for (int j = (i + 1); (j < state.getOutcoming().size()); j++) {
          {
            UserInteraction nexts = state.getOutcoming().get(j);
            Intent _intent = current.getIntent();
            Intent _intent_1 = nexts.getIntent();
            boolean _tripleEquals = (_intent == _intent_1);
            if (_tripleEquals) {
              String _name = current.getIntent().getName();
              String _plus = ("Only one path can start with the intent " + _name);
              this.error(_plus, 
                GeneratorPackage.Literals.BOT_INTERACTION__OUTCOMING);
            }
          }
        }
      }
    }
  }
  
  @Check
  public void nameUnique(final Element e) {
    EObject container = e.eContainer();
    if ((container instanceof Bot)) {
      ArrayList<Element> elements = new ArrayList<Element>();
      elements.addAll(((Bot)container).getIntents());
      elements.addAll(((Bot)container).getActions());
      elements.addAll(((Bot)container).getEntities());
      for (final Element i : elements) {
        if (((!i.equals(e)) && i.getName().equals(e.getName()))) {
          String _name = i.getName();
          String _plus = ("There are several elements with the name " + _name);
          String _plus_1 = (_plus + ". The name of the elements must be unique");
          this.error(_plus_1, 
            GeneratorPackage.Literals.ELEMENT__NAME);
        }
      }
    }
  }
  
  @Check
  public void nameUnique(final Parameter param) {
    EObject container = param.eContainer();
    if ((container instanceof Intent)) {
      EList<Parameter> _parameters = ((Intent)container).getParameters();
      for (final Parameter p : _parameters) {
        if (((!param.equals(p)) && param.getName().equals(p.getName()))) {
          String _name = p.getName();
          String _plus = ("There are several parameters with the name " + _name);
          String _plus_1 = (_plus + " in this intent. The name of the parameters must be unique");
          this.error(_plus_1, 
            GeneratorPackage.Literals.ELEMENT__NAME);
        }
      }
    }
  }
  
  @Check
  public void nameUnique(final SimpleInput input) {
    EObject container = input.eContainer();
    if ((container instanceof Simple)) {
      EList<SimpleInput> _inputs = ((Simple)container).getInputs();
      for (final SimpleInput i : _inputs) {
        if (((!input.equals(i)) && input.getName().equals(i.getName()))) {
          String _name = i.getName();
          String _plus = ("There are several entries with the name " + _name);
          String _plus_1 = (_plus + " in this entity. The name of the entries must be unique");
          this.error(_plus_1, 
            GeneratorPackage.Literals.ELEMENT__NAME);
        }
      }
    }
  }
  
  @Check
  public void paramEntity(final Parameter param) {
    if (((param.getEntity() == null) && (param.getDefaultEntity() == null))) {
      this.error("The parameter must have a entity", 
        GeneratorPackage.Literals.PARAMETER__ENTITY);
    }
  }
  
  @Check
  public void requestExecution(final BotInteraction interaction) {
    EList<Action> _actions = interaction.getActions();
    for (final Action action : _actions) {
      if ((action instanceof HTTPResponse)) {
        int index = interaction.getActions().indexOf(action);
        if ((index == 0)) {
          this.error("Before an HttpResponse must go the HttpRequest which reference", GeneratorPackage.Literals.BOT_INTERACTION__ACTIONS);
        } else {
          Action _get = interaction.getActions().get((index - 1));
          HTTPRequest _hTTPRequest = ((HTTPResponse)action).getHTTPRequest();
          boolean _tripleNotEquals = (_get != _hTTPRequest);
          if (_tripleNotEquals) {
            this.error("Before an HttpResponse must go the HttpRequest which reference", GeneratorPackage.Literals.BOT_INTERACTION__ACTIONS);
          }
        }
      }
    }
  }
}
