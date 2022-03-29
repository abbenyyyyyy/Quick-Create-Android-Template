package com.github.abbenyyyyyy.quickcreateandroidtemplate.dialogs

import com.intellij.openapi.ui.DialogWrapper
import javax.swing.JComponent

/**
 * 新建 Activity 模版的用户输入确认弹窗
 */
class NewActivityTemplateDialog(
    val parentPath: String,
    val appPackage: String,
    val pathPackage: String,
) : DialogWrapper(false) {
    override fun createCenterPanel(): JComponent? {
        TODO("Not yet implemented")
    }
}