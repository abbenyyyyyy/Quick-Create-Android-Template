package com.github.abbenyyyyyy.quickcreateandroidtemplate.utils

import com.github.abbenyyyyyy.quickcreateandroidtemplate.beans.CreateFileTemplateBean
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.fileTemplates.FileTemplateUtil
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement

class FileUtil(project: Project) {

    val fileTemplateManager: FileTemplateManager = FileTemplateManager.getInstance(project)

    fun createFile(createFileTemplateBean: CreateFileTemplateBean): PsiElement {
        val fileTemplate = fileTemplateManager.getInternalTemplate(createFileTemplateBean.templateName)
        return FileTemplateUtil.createFromTemplate(
            fileTemplate,
            createFileTemplateBean.fileName,
            createFileTemplateBean.properties,
            createFileTemplateBean.directory
        )
    }
}