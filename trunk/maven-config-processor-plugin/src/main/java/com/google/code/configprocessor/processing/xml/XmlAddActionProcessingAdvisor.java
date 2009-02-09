/*
 * Copyright (C) 2009 Leandro de Oliveira Aparecido <lehphyro@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.code.configprocessor.processing.xml;

import javax.xml.namespace.*;

import org.w3c.dom.*;

import com.google.code.configprocessor.*;
import com.google.code.configprocessor.processing.*;

public class XmlAddActionProcessingAdvisor extends AbstractXmlActionProcessingAdvisor {

	private AddAction action;
	private String textFragment;
	
	public XmlAddActionProcessingAdvisor(AddAction action, ExpressionResolver expressionResolver, NamespaceContext namespaceContext)
	throws ParsingException {
		super(expressionResolver, namespaceContext);
		
		this.action = action;
		if (this.action.getBefore() != null) {
			compile(action.getBefore());
		} else if (this.action.getAfter() != null) {
			compile(action.getAfter());
		} else {
			throw new ParsingException("Add action must specify [before] or [after] attribute");
		}
		this.textFragment = resolve(action.getValue());
	}
	
	public void process(Document document) throws ParsingException {
		Node node = evaluateForSingleNode(document);
		Node parent = node.getParentNode();

		try {
			Document fragment = XmlHelper.parse(textFragment, true);
			
			Node referenceNode;
			if (action.getBefore() != null) {
				referenceNode = node;
			} else {
				referenceNode = node.getNextSibling();
				if (referenceNode == null) {
					referenceNode = node;
				}
			}
			
			NodeList nodeList = fragment.getFirstChild().getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node importedNode = document.importNode(nodeList.item(i), true);
				parent.insertBefore(importedNode, referenceNode);
			}
		} catch (Exception e) {
			throw new ParsingException(e);
		}
	}
	
}
