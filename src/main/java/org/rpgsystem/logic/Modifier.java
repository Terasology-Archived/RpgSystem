/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rpgsystem.logic;

/**
 * These are modifiers for RPGsystem.
 * they contain id and float value
 * @author esereja <esereja@yahoo.co.uk>
 */
public class Modifier{
	
	/**
	 * Identifier of modifier, use always same id for same source
	 */
	protected String id;
	/**
	 * modifier value
	 */
	protected float value;
	
	/**
	 * this constructs modifier for RPGsystem
	 * @param id Identifier of modifier, use always same id for same source
	 * @param value value
	 */
	public Modifier(String id,float value){
		this.id=id;
		this.value=value;
	}

	/**
	 * @return the modifier
	 */
	public float getValue() {
		return value;
	}

	/**
	 * @param value the modifier to set
	 */
	public void setValue(float value) {
		this.value = value;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
