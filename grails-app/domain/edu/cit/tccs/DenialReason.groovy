package edu.cit.tccs

class DenialReason {

	String reason

	static belongsTo = [correction: Correction]

    static constraints = {
    }

    static mapping = {
        id generator:'sequence', column:'id', params:[sequence:'denial_reason_sequence']
    }
}
