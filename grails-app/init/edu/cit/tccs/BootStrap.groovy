package edu.cit.tccs
class BootStrap {

    def init = { servletContext ->
        new Role(authority: "ROLE_USER").save()
        new Role(authority: "ROLE_ADMIN").save()
        new Role(authority: "ROLE_HEAD").save()

        new Department(name: "APPDEV").save()
        new Department(name: "ACCOUNTING").save()
        new Department(name: "ADMIN").save()
        new Department(name: "NOC").save()
    }
    def destroy = {
    }
}
