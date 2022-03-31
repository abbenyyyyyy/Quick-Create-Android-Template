package com.github.abbenyyyyyy.quickcreateandroidtemplate.actions

import com.android.resources.ResourceFolderType
import com.android.tools.idea.npw.project.getModuleTemplates
import com.android.tools.idea.npw.project.getPackageForPath
import com.android.tools.idea.projectsystem.SourceProviders
import com.github.abbenyyyyyy.quickcreateandroidtemplate.QuickTemplateBundle
import com.github.abbenyyyyyy.quickcreateandroidtemplate.dialogs.NewActivityTemplateDialog
import com.github.abbenyyyyyy.quickcreateandroidtemplate.utils.CustomPathUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.psi.PsiManager
import org.jetbrains.android.dom.manifest.Manifest
import org.jetbrains.android.facet.AndroidFacet
import org.jetbrains.android.util.AndroidUtils
import org.jetbrains.kotlin.idea.KotlinIcons

class NewAndroidTemplateAction : AnAction(
    QuickTemplateBundle.message("action.new.quickActivityTemplate"),
    "",
    KotlinIcons.CLASS
) {
    override fun actionPerformed(e: AnActionEvent) {
        val currentProject = e.project ?: return
        // action 对应的上下文
        val dataContext = e.dataContext
        // 获取到当前 IDE 的对象
        val view = LangDataKeys.IDE_VIEW.getData(dataContext) ?: return
        val module = LangDataKeys.MODULE.getData(dataContext) ?: return
        val facet = AndroidFacet.getInstance(module) ?: return
        // action 右键点击的文件夹
        val dir = view.orChooseDirectory ?: return
        val manifest = Manifest.getMainManifest(facet) ?: return
        // 这里获得的是右键文件夹(文件)所在模块的清单文件里的 package 字段
        val appPackage = manifest.`package`.value ?: ""
        // 这里获得的是右键文件夹对应的 package
        val pathPackage = facet.getPackageForPath(facet.getModuleTemplates(dir.virtualFile), dir.virtualFile)
        // 这里获得 layout 文件夹的 PsiDirectory
        val mainManifestFile = SourceProviders.getInstance(facet).mainManifestFile?:return
        val resFile = mainManifestFile.parent.findChild("res")?:return
        val layoutFile = AndroidUtils.createChildDirectoryIfNotExist(currentProject,resFile,ResourceFolderType.LAYOUT.name)
        val layoutResDir = PsiManager.getInstance(currentProject).findDirectory(layoutFile)?:return
        NewActivityTemplateDialog(
            currentProject,
            manifest,
            dir,
            layoutResDir,
            CustomPathUtil.getFirstUpperCasePath(dir.virtualFile.name),
            pathPackage
        ).show()
    }
}