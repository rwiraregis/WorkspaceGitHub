/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.tool.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.openmrs.module.tool.db.ToolDAO;
import org.openmrs.module.tool.service.ToolService;


/**
 *
 */
public class ToolServiceImpl implements ToolService{

	private ToolDAO toolDAO;
	protected final Log log = LogFactory.getLog(getClass()); 
	public ToolDAO getToolDAO() {
		return toolDAO;
	}
	
	public void setToolDAO(ToolDAO toolDAO) {
		this.toolDAO = toolDAO;
	}
	
	/**
     * @return 
	 * @see org.openmrs.module.tool.service.ToolService#changePatientIdentifierFormat(java.lang.String, java.lang.Integer, org.hibernate.Session)
     */
    @Override
    public void changePatientIdentifierFormat(String patientIdentifier, Integer patientId, Session session) {
	    // TODO Auto-generated method stub
    	
    	log.info(" im a reaching here i m reaching here"+patientId+" and "+patientIdentifier);
    	
	      toolDAO.changePatientIdentifierFormat(patientIdentifier, patientId, session) ;
    }

	/**
     * @see org.openmrs.module.tool.service.ToolService#chengePatientIdentifierRegexFormat(org.hibernate.Session)
     */
    @Override
    public void chengePatientIdentifierRegexFormat(Session session) {
	    // TODO Auto-generated method stub
    	toolDAO.chengePatientIdentifierRegexFormat(session);
	    
    }

}
