package edu.cit.tccs

class Proof {

	byte[] fileData
	String fileName

	static belongsTo = [correction: Correction]

    static constraints = {
    }

    static mapping = {
        id generator:'sequence', column:'id', params:[sequence:'proof_sequence']
    }
}
