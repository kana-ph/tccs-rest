package edu.cit.tccs

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	String pinCode
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	def beforeInsert() {
		encodePassword()
		encodePinCode()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
		if (isDirty('pinCode')) {
			encodePinCode()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	protected void encodePinCode() {
		pinCode = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(pinCode) : pinCode
	}

	static transients = ['springSecurityService']

	static constraints = {
		password blank: false, password: true
		username blank: false, unique: true
	}

	static mapping = {
		table 'users'
		id generator:'sequence', column:'id', params:[sequence:'user_sequence']
		password column: '`password`'
	}
}
