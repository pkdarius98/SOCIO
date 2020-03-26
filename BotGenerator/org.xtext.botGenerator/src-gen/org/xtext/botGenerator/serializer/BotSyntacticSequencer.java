/*
 * generated by Xtext 2.19.0
 */
package org.xtext.botGenerator.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;
import org.xtext.botGenerator.services.BotGrammarAccess;

@SuppressWarnings("all")
public class BotSyntacticSequencer extends AbstractSyntacticSequencer {

	protected BotGrammarAccess grammarAccess;
	protected AbstractElementAlias match_Text_TextKeyword_5_q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (BotGrammarAccess) access;
		match_Text_TextKeyword_5_q = new TokenAlias(false, true, grammarAccess.getTextAccess().getTextKeyword_5());
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		return "";
	}
	
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if (match_Text_TextKeyword_5_q.equals(syntax))
				emit_Text_TextKeyword_5_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Ambiguous syntax:
	 *     'text:'?
	 *
	 * This ambiguous syntax occurs at:
	 *     language=Language ':' (ambiguity) inputs+=TextInputText
	 *     name=EString ':' (ambiguity) inputs+=TextInputText
	 */
	protected void emit_Text_TextKeyword_5_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
