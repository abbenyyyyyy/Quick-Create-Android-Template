package com.github.abbenyyyyyy.quickcreateandroidtemplate.services

import com.intellij.openapi.project.Project
import com.github.abbenyyyyyy.quickcreateandroidtemplate.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
