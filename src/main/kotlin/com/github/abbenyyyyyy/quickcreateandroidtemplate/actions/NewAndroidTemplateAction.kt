package com.github.abbenyyyyyy.quickcreateandroidtemplate.actions

import com.github.abbenyyyyyy.quickcreateandroidtemplate.QuickTemplateBundle
import com.github.abbenyyyyyy.quickcreateandroidtemplate.utils.PrintUtil
import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import org.jetbrains.kotlin.idea.KotlinIcons

class NewAndroidTemplateAction : CreateFileFromTemplateAction(
    QuickTemplateBundle.message("action.new.quickActivityTemplate"),
    "",
    KotlinIcons.CLASS
) {
    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        PrintUtil.log("触发")
    }

    override fun getActionName(directory: PsiDirectory?, newName: String, templateName: String?): String {
        return QuickTemplateBundle.message("action.new.quickActivityTemplate")
    }
}