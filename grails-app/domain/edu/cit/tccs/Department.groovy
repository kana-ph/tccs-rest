package edu.cit.tccs

class Department {

	String name

    static constraints = {
    }

    static mapping = {
        id generator:'sequence', column:'id', params:[sequence:'department_sequence']
    }
}
