package edu.cit.tccs

import edu.cit.tccs.type.RoleAuthority

import org.springframework.http.HttpStatus

class UserController {

    static responseFormats = ['json']

    DepartmentService departmentService
    UserService userService

    def save() {
        Map json = request.JSON
        String firstName = json.firstName
        String middleName = json.middleName
        String lastName = json.lastName
        String email = json.emailAddress
        String position = json.position
        Department department = fetchDepartment(json.department)
        List roles = fetchRoles(json.roleAdmin, json.roleHead)

        UserInfo info = userService.save(firstName, middleName, lastName, email, position, department, roles)

        response.status = HttpStatus.CREATED.value()
        respond(serialize(info))
    }

    def show() {
        long id = params.id as long
        UserInfo info = userService.fetchById(id)

        response.status = HttpStatus.OK.value()
        respond(serialize(info))
    }

    def showAll() {
        List<UserInfo> infos = userService.fetchAll()

        response.status = HttpStatus.OK.value()
        respond(serializeList(infos))
    }

    def update() {
        Map json = request.JSON
        long id = params.id as long

        String firstName = json.firstName
        String middleName = json.middleName
        String lastName = json.lastName
        String email = json.emailAddress
        String position = json.position
        Department department = fetchDepartment(json.department)
        List roles = fetchRoles(json.roleAdmin, json.roleHead)

        UserInfo info = userService.update(id, firstName, middleName, lastName, email, position, department, roles)

        response.status = HttpStatus.OK.value()
        respond(serialize(info))
    }

    def delete() {
        long id = params.id as long
        UserInfo info = userService.deleteById(id)

        render status: HttpStatus.NO_CONTENT
    }

    private Department fetchDepartment(String name) {
        return departmentService.fetchByName(name)
    }

    private List<RoleAuthority> fetchRoles(boolean roleAdmin, boolean roleHead) {
        List roles = [RoleAuthority.ROLE_USER]

        if (roleAdmin) {
            roles << RoleAuthority.ROLE_ADMIN
        }

        if (roleHead) {
            roles << RoleAuthority.ROLE_HEAD
        }

        return roles
    }

    private Map serialize(UserInfo info) {
        return [
            firstName: info.firstName,
            lastName: info.lastName,
            middleName: info.middleName,
            position: info.position,
            emailAddress: info.emailAddress,
            department: info.department.name,
            roles: info.user.authorities*.authority
        ]
    }

    private List serializeList(List<UserInfo> infos) {
        return infos.collect(this.&serialize)
    }
}
