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

import java.util.*;

import org.w3c.dom.*;

import com.google.code.configprocessor.*;

public class NestedXmlActionProcessingAdvisor implements XmlActionProcessingAdvisor {

	private List<XmlActionProcessingAdvisor> advisors;
	
	public NestedXmlActionProcessingAdvisor(List<XmlActionProcessingAdvisor> advisors) {
		this.advisors = advisors;
	}
	
	public void process(Document document) throws ParsingException {
		for (XmlActionProcessingAdvisor advisor : advisors) {
			advisor.process(document);
		}
	}
}
