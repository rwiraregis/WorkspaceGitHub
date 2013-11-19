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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openmrs.PatientIdentifier;
import org.openmrs.PatientIdentifierType;
import org.openmrs.api.context.Context;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * This controller backs the /web/module/basicmoduleForm.jsp page. This controller is tied to that
 * jsp page in the /metadata/moduleApplicationContext.xml file
 */
public class UpdateFOSAController extends AbstractController {
	
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
		ModelAndView mav = new ModelAndView();
			
		// Getting FOSA identifier type
		
		String patientIdentifierType = request.getParameter("patientIdentifierType");
		
		
		
		    String[] fosaID = getFOSAId().split("-");
			
			mav.addObject("fosaId",fosaID[0]);
			
		
		
		mav.setViewName("module/tool/updateFOSA");
		
		return mav;
	}
	
	public String getFOSAId() {
		
		SQLQuery query = session.createSQLQuery("SELECT  identifier FROM patient_identifier limit 1;");
		
		try {
			session.beginTransaction();
			query.executeUpdate();
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			log.error(e);
		}
		String FOSAId = (String) query.list().get(0);
		return FOSAId;
	}
    
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
