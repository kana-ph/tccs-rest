package edu.cit.tccs

import grails.transaction.Transactional

@Transactional
class DepartmentService {

    Department fetchByName(String name) {
    	return Department.findByName(name)
    }
}
