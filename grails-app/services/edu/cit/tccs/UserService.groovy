package edu.cit.tccs

import edu.cit.tccs.type.RoleAuthority

import grails.transaction.Transactional

@Transactional
class UserService {

    UserInfo save(String firstName, String middleName, String lastName, String email, String position, Department department, List<RoleAuthority> roles) {

        String password = generatePassword()
        String pinCode = generatePinCode()
        User user = new User(username: email, password: password, pinCode: pinCode).save(flush: true)
        UserInfo info = new UserInfo(
            user: user,
            firstName: firstName,
            middleName: middleName,
            lastName: lastName,
            position: position,
            emailAddress: email,
            department: department
        ).save(flush: true)
        saveRoles(user, roles)

        // TODO invoke emailService here
        // emailService.sendVerificationEmail(info, password, pinCode)

        return info
    }

    private void saveRoles(User user, List<RoleAuthority> roles) {
        roles.each { roleAuthority ->
            Role role = Role.findByAuthority(roleAuthority.toString())
            UserRole.create(user, role)
        }
    }

    private String generatePassword() {
        String uuid = UUID.randomUUID().toString()
        return uuid.tokenize('-')[0]
    }

    private String generatePinCode() {
        String time = System.currentTimeMillis().toString()
        return time[-4..-1]
    }
}
