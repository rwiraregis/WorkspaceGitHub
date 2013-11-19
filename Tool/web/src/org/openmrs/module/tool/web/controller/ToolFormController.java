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
package org.openmrs.module.tool.web.controller;

import java.util.Collection;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openmrs.PatientIdentifier;
import org.openmrs.PatientIdentifierType;
import org.openmrs.api.context.Context;
import org.openmrs.module.tool.service.ToolService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * This controller backs the /web/module/basicmoduleForm.jsp page. This controller is tied to that
 * jsp page in the /metadata/moduleApplicationContext.xml file
 */
public class ToolFormController extends AbstractController {
	
	private SessionFactory sessionFactory;
	
	private Session session;
	
	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		try {
			session = getSessionFactory().getCurrentSession();
		}
		catch (Exception e) {
			log.error("!!!!!!!!!!"); // TODO: handle exception
		}
		
		ToolService toolService = Context.getService(ToolService.class);
		
		log.info("toolServicetoolServicetoolServicetoolService toolService"+toolService);
		
		ModelAndView mav = new ModelAndView();
			
		// Getting patient identifier type
		
		String patientIdentifierType = request.getParameter("patientIdentifierType");
		
		// Getting fosa id	
		
		String fosaId = request.getParameter("FOSAId");
		// results to be desplayed after patient format has changed
		HashMap<String, String> oldAndNewPatientIdentifiers = new HashMap<String, String>();
		
		// check patient identify type
		
		PatientIdentifierType primarycareIdentifierType = null;
		if (patientIdentifierType != null && fosaId != null)
			if (patientIdentifierType.equals("TRACnet")) {
				
				primarycareIdentifierType = Context.getPatientService().getPatientIdentifierType(4);
				
			}
		
		if (primarycareIdentifierType != null) {
			
			Collection<PatientIdentifier> patientIdentifiers = Context.getPatientService().getPatientIdentifiers(
			    primarycareIdentifierType);
			
			for (PatientIdentifier patientIdentifier : patientIdentifiers) {
				Integer patientId = patientIdentifier.getPatient().getPatientId();
				//get old identify type
				String oldPatientIdentifier = patientIdentifier.getIdentifier();
				// defining  new  identifier
				
				String newPatientIdentifier ="";
				String newFormat = "";
				String validationMessage = null;
				// check if the patient identifier does not have alriedy a new identifier format 
				if (!patientIdentifier.getIdentifier().contains("-")) {
					
					// generate new format
					
					if (fosaId.length() == 1) {
						newFormat = "000" + fosaId + "-";
					} else if (fosaId.length() == 2) {
						newFormat = "00" + fosaId + "-";
					} else if (fosaId.length() == 3) {
						newFormat = "0" + fosaId + "-";
					} else if (fosaId.length() == 4) {
						newFormat = fosaId + "-";
					} else {
						validationMessage = "you have provided a wrong FOSA ID";
						mav.addObject("validationMessage", validationMessage);
					}
					//patientIdentifier.setIdentifier(newFormat + patientIdentifier.getIdentifier());
					
					// defining  new  identifier
					
					newPatientIdentifier = newFormat + patientIdentifier.getIdentifier();
					
					
					
					// update patient identifier table  
					toolService.changePatientIdentifierFormat(newPatientIdentifier, patientId, session);
					// change patient regex format
					
					toolService.chengePatientIdentifierRegexFormat(session);
					// results to be desplayed after patient format has changed
					oldAndNewPatientIdentifiers.put(oldPatientIdentifier, newPatientIdentifier);
					
					
					
				}else{
					
					//get patient Identifier
					
					String[] patientIdentiferNumber = oldPatientIdentifier.split("-");
					
					
                 // generate new format
					
					
					if (fosaId.length() == 1) {
						newFormat = "000" + fosaId + "-";
					} else if (fosaId.length() == 2) {
						newFormat = "00" + fosaId + "-";
					} else if (fosaId.length() == 3) {
						newFormat = "0" + fosaId + "-";
					} else if (fosaId.length() == 4) {
						newFormat = fosaId + "-";
					} else {
						validationMessage = "you have provided a wrong FOSA ID";
						mav.addObject("validationMessage", validationMessage);
					}
					//patientIdentifier.setIdentifier(newFormat + patientIdentifier.getIdentifier());
					
					// defining  new  identifier
					
					newPatientIdentifier = newFormat + patientIdentiferNumber[1];
					
					
					
					// update patient identifier table  
					toolService.changePatientIdentifierFormat(newPatientIdentifier, patientId, session);
					// change patient regex format
					
					toolService.chengePatientIdentifierRegexFormat(session);
					// results to be desplayed after patient format has changed
					oldAndNewPatientIdentifiers.put(oldPatientIdentifier, newPatientIdentifier);
					
					
					
				}
				
			}
			mav.addObject("oldAndNewPatientIdentifiers",oldAndNewPatientIdentifiers);
			mav.addObject("patientIdentifiers", patientIdentifiers);
			mav.addObject("fosaId", fosaId.length());
			
		}
		
		mav.setViewName("module/tool/toolForm");
		
		return mav;
	}
	
	/*public void changePatientIdentifierFormat(String patientIdentifier, Integer patientId, Session session) {
		
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
	}*/
	/** Logger for this class and subclasses */
	/*
		protected final Log log = LogFactory.getLog(getClass());
		
	*//**
	 * Returns any extra data in a key-->value pair kind of way
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.Errors)
	 */
	/*
		@Override
		protected Map<String, Object> referenceData(HttpServletRequest request, Object obj, Errors err) throws Exception {
			
			// this method doesn't return any extra data right now, just an empty map
			return new HashMap<String, Object>();
		}
		
		*//**
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	/*
		@Override
		protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object object,
		                                BindException exceptions) throws Exception {
			//HttpSession httpSession = request.getSession();
			
			return new ModelAndView(new RedirectView(getSuccessView()));
		}
		
		*//**
	 * This class returns the form backing object. This can be a string, a boolean, or a normal
	 * java pojo. The type can be set in the /config/moduleApplicationContext.xml file or it can be
	 * just defined by the return type of this method
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	/*
		@Override
		protected Collection<PatientIdentifier> formBackingObject(HttpServletRequest request) throws Exception {
			
			// get all patients that have an identifier "1234"
			// see http://resources.openmrs.org/doc/index.html?org/openmrs/api/PatientService.html for
			// a list of all PatientService methods
			
			
			//Collection<Patient> patients = Context.getPatientService().findPatients("1234", false);
			
			PatientIdentifierType primarycareIdentifierType = Context.getPatientService().getPatientIdentifierType(3);
			
			log.info("This is a list of patient identifier type"+primarycareIdentifierType.getName());
			
			Collection<PatientIdentifier> patientIdentifiers = Context.getPatientService().getPatientIdentifiers(primarycareIdentifierType);
			
			log.info("This is a list of patient identifierskkkkkkkkkkkkkkkkkkkkk"+patientIdentifiers.size());
		
			//Collection<Patient> patients = Context.getPatientService().getAllPatients();		
			
			for (PatientIdentifier patientIdentifier : patientIdentifiers) {
				
				
				log.info("patientIdentifier patientIdentifier patientIdentifier patientIdentifier"+patientIdentifier.getIdentifier());
		        
	        }
			
			// this object will be made available to the jsp page under the variable name
			// that is defined in the /metadata/moduleApplicationContext.xml file 
			// under the "commandName" tag
			return patientIdentifiers;
		}
		*/

}
