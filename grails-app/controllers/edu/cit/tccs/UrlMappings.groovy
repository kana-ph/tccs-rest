package edu.cit.tccs

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/v1/user"(controller: 'user') {
            action = [GET: 'showAll', POST: 'save']
        }

        "/api/v1/user/${id}"(controller: 'user') {
            action = [GET: 'show', PUT: 'update', DELETE: 'delete']
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
