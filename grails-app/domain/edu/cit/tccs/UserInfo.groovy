package edu.cit.tccs

class UserInfo {

    String firstName
    String lastName
    String middleName
    String position
    String emailAddress
    Department department

    static belongsTo = [user: User]

    static constraints = {
    }

    static mapping = {
        id generator:'sequence', column:'id', params:[sequence:'user_info_sequence']
    }
}
