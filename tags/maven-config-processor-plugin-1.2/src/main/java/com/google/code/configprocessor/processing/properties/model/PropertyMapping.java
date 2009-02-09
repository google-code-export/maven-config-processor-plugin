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
package com.google.code.configprocessor.processing.properties.model;

public class PropertyMapping implements PropertiesFileItem {

	private String propertyName;
	private String propertyValue;
	
	public PropertyMapping(String propertyName, String propertyValue) {
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}
	
	public String getAsText() {
		StringBuilder sb = new StringBuilder();
		sb.append(getPropertyName());
		sb.append("=");
		if (propertyValue != null) {
			sb.append(propertyValue);
		}
		
		return sb.toString();
	}
	
	public void appendLine(String line) {
		propertyValue += line;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	
	public String getPropertyValue() {
		return propertyValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((propertyName == null) ? 0 : propertyName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PropertyMapping other = (PropertyMapping) obj;
		if (propertyName == null) {
			if (other.propertyName != null) {
				return false;
			}
		} else if (!propertyName.equals(other.propertyName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Mapping [" + propertyName + "=>" + propertyValue + "]";
	}
}
