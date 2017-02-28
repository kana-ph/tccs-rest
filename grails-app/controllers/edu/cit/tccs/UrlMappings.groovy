package edu.cit.tccs

class UrlMappings {

    static mappings = {
        "/api/v1/user"(controller: 'user') {
            action = [GET: 'showAll', POST: 'save']
        }

        "/api/v1/user/${id}"(controller: 'user') {
            action = [GET: 'show', PUT: 'update', DELETE: 'delete']
        }

		"/user"(controller: 'user', action: 'index', method: 'GET')
		"/user/create"(controller: 'user', action: 'create', method: 'GET')
		"/user/edit"(controller: 'user', action: 'edit', method: 'GET')

        "/"(view:"/index")
		"/index"(view: '/dashboard')

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
