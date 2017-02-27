package edu.cit.tccs

import edu.cit.tccs.type.*

class Correction {

	Date correctionDate
	String comment
	Reason reason // missed, system err TODO remove comment
	CorrectionType type // clock in/out TODO remove comment
	CorrectionStatus status // pending, approved TODO remove comment
	Date dateCreated

	boolean isUpdated() {
		version > 0
	}

	DenialReason getDenialReason() {
		DenialReason.findByCorrection(this)
	}

	static belongsTo = [user: User]

	static hasMany = [proofs: Proof]

    static constraints = {
    	comment maxSize: 1000, blank: true
    	denialReason nullable: true
    }

    static mapping = {
        id generator:'sequence', column:'id', params:[sequence:'correction_sequence']
    }
}
