package com.github.abbenyyyyyy.quickcreateandroidtemplate.actions

import com.android.tools.idea.npw.project.getModuleTemplates
import com.android.tools.idea.npw.project.getPackageForPath
import com.github.abbenyyyyyy.quickcreateandroidtemplate.QuickTemplateBundle
import com.github.abbenyyyyyy.quickcreateandroidtemplate.dialogs.NewActivityTemplateDialog
import com.github.abbenyyyyyy.quickcreateandroidtemplate.utils.PrintUtil
import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.ide.fileTemplates.FileTemplateUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import org.jetbrains.android.dom.manifest.Manifest
import org.jetbrains.android.facet.AndroidFacet
import org.jetbrains.kotlin.idea.KotlinIcons

class NewAndroidTemplateAction : AnAction(
    QuickTemplateBundle.message("action.new.quickActivityTemplate"),
    "",
    KotlinIcons.CLASS
) {
    override fun actionPerformed(e: AnActionEvent) {
        // action 对应的上下文
        val dataContext = e.dataContext
        // 获取到当前 IDE 的对象
        val view = LangDataKeys.IDE_VIEW.getData(dataContext) ?: return
        val module = LangDataKeys.MODULE.getData(dataContext) ?: return
        val facet = AndroidFacet.getInstance(module) ?: return
        // action 右键点击的文件夹
        val dir = view.orChooseDirectory ?: return
        val manifest = Manifest.getMainManifest(facet)
        // 这里获得的是右键文件夹(文件)所在模块的清单文件里的 package 字段
        val appPackage = manifest?.`package`?.value ?: ""
        // 这里获得的是右键文件夹对应的 package
        val pathPackage = facet.getPackageForPath(facet.getModuleTemplates(dir.virtualFile), dir.virtualFile)
//        PrintUtil.log("检查:${dir.virtualFile} ..${appPackage} .. ${dir.virtualFile.name}")
//        PrintUtil.log("----${dir.virtualFile.parent.name} ..${dir.virtualFile.path} ..${dir.virtualFile.canonicalPath}")
//        PrintUtil.log("----${pathPackage}")
        NewActivityTemplateDialog(dir.virtualFile.name, appPackage, pathPackage).show()
    }
}