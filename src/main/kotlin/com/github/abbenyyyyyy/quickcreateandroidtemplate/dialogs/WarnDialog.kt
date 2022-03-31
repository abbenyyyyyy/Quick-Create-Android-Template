package com.github.abbenyyyyyy.quickcreateandroidtemplate.dialogs

import com.github.abbenyyyyyy.quickcreateandroidtemplate.QuickTemplateBundle
import com.intellij.openapi.ui.DialogWrapper
import java.awt.Component
import java.awt.Dimension
import java.awt.Font
import javax.swing.*

/**
 * 自定义提示弹窗
 */
class WarnDialog(
    private val customMessage: String,
    customTitle: String = QuickTemplateBundle.message("dialog.warn.title"),
    private val cancelText: String = QuickTemplateBundle.message("dialog.warn.cancel")
) : DialogWrapper(false) {

    private val dialogPanel = JPanel()

    init {
        title = customTitle
        init()
    }

    override fun createCenterPanel(): JComponent {
        dialogPanel.layout = BoxLayout(dialogPanel, BoxLayout.PAGE_AXIS)
        dialogPanel.minimumSize = Dimension(320, 180)
        dialogPanel.add(Box.createRigidArea(Dimension(0, 10)))
        val label = JLabel(customMessage).apply {
            this.font = Font(this.font.fontName, Font.BOLD, 14)
            this.alignmentX = Component.LEFT_ALIGNMENT
        }
        dialogPanel.add(label)
        return dialogPanel
    }

    override fun createActions(): Array<Action> {
        return arrayOf(DialogWrapperExitAction(cancelText, -1))
    }
}
