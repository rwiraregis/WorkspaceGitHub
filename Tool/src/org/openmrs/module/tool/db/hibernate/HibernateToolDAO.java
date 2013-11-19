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
package org.openmrs.module.tool.db.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openmrs.module.tool.db.ToolDAO;


/**
 *
 */
public class HibernateToolDAO implements ToolDAO {

    private SessionFactory sessionFactory;
	
	protected final Log log = LogFactory.getLog(getClass());
	
	
	
     public void changePatientIdentifierFormat(String patientIdentifier, Integer patientId, Session session) {
		
		SQLQuery query = session.createSQLQuery("update patient_identifier set identifier = '" + patientIdentifier
		        + "' where patient_id = " + patientId + " and identifier_type = 4;");
		
		try {
			session.beginTransaction();
			query.executeUpdate();
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			log.error(e);
		}
	}
    public void chengePatientIdentifierRegexFormat(Session session) {
    	
		SQLQuery query = session.createSQLQuery("update patient_identifier_type set format = '\\\\\\d{4}\\\\\\-\\\\\\d{6}' where patient_identifier_type_id = 4;");
		
		try {
			session.beginTransaction();
			query.executeUpdate();
			session.getTransaction().commit();
			
		}
		catch (HibernateException e) {
			log.error(e);
		}
	}

}
