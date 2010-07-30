/*******************************************************************************
 * Copyright (c) 2010 Eric Bodden.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Eric Bodden - initial API and implementation
 ******************************************************************************/
package de.bodden.tamiflex.db.datamodel;

import java.sql.Timestamp;

/**
 * Entry in the table Runs.
 * @author Ivaylo Petkov and Oleg Manov
 */
public class Run extends DBEntry{
	/**
	 * The local host.
	 */
	private String host;
	/**
	 * The current time.
	 */
	private Timestamp time;
	
	/**
	 * The constructor
	 * @param host	
	 * 				the local host
	 * @param time	
	 * 				the current time
	 */
	public Run(String host,Timestamp time) {
		this.host=host;
		this.time=time;
		this.setTableName("Runs");
		this.setIdFieldName("idRuns");
		this.fields="idruns,host,time";
	}

	public String generateInsertStatement() {
		String statement="insert into ";
		statement+=getTableName();
		statement+=" (host,time) values(";
		statement+="'"+host+"'";
		statement+=",'"+time+"'";
		statement+=")";
		return statement;
	}

	public String generateIDSearchStatement() {
		String statement="select ";
		statement+=getIdFieldName();
		statement+=" from ";
		statement+=getTableName();
		statement+=" where ";
		statement+="host='"+host+"'";
		statement+=" and ";
		statement+="time='"+time+"'";
		return statement;
	}
}
