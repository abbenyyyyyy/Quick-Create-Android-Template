package com.github.abbenyyyyyy.quickcreateandroidtemplate.dialogs

import com.github.abbenyyyyyy.quickcreateandroidtemplate.QuickTemplateBundle
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.util.ui.JBEmptyBorder
import java.awt.Component
import java.awt.Font
import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel

/**
 * 新建 Activity 模版的用户输入确认弹窗
 */
class NewActivityTemplateDialog(
    val parentPath: String,
    val appPackage: String,
    val pathPackage: String,
) : DialogWrapper(false) {

    init {
        title = QuickTemplateBundle.message("dialog.new.activityTemplate.title")
        setCancelButtonText(QuickTemplateBundle.message("dialog.new.activityTemplate.cancel"))
        setOKButtonText(QuickTemplateBundle.message("dialog.new.activityTemplate.ok"))
        init()
    }

    override fun createCenterPanel(): JComponent? {
        val dialogPanel = JPanel().apply {
            this.border = JBEmptyBorder(10, 5, 10, 5)
        }
        dialogPanel.layout = BoxLayout(dialogPanel, BoxLayout.PAGE_AXIS)
        dialogPanel.add(JLabel(QuickTemplateBundle.message("dialog.new.activityTemplate.tip")).apply {
            this.font = Font(this.font.fontName, Font.BOLD, 16)
            this.alignmentX = Component.LEFT_ALIGNMENT
        })
        dialogPanel.add(JLabel(QuickTemplateBundle.message("dialog.new.activityTemplate.tipDescription")).apply {
            this.font = Font(this.font.fontName, Font.PLAIN, 14)
            this.border = JBEmptyBorder(10, 0, 10, 0)
            this.alignmentX = Component.LEFT_ALIGNMENT
        })
        return dialogPanel
    }
}