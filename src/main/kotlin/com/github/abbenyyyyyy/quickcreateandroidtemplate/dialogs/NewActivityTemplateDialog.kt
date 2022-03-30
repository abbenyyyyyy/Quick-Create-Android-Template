package com.github.abbenyyyyyy.quickcreateandroidtemplate.dialogs

import com.github.abbenyyyyyy.quickcreateandroidtemplate.QuickTemplateBundle
import com.github.abbenyyyyyy.quickcreateandroidtemplate.utils.CustomPathUtil
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.fileTemplates.FileTemplateUtil
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.psi.PsiDirectory
import com.intellij.util.ui.JBEmptyBorder
import java.awt.Component
import java.awt.Dimension
import java.awt.Font
import javax.swing.*
import javax.swing.border.MatteBorder
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

/**
 * 新建 Activity 模版的用户输入确认弹窗
 */
class NewActivityTemplateDialog(
    private val project: Project,
    private val chooseDirectory: PsiDirectory,
    private val parentPath: String,
    private val appPackage: String,
    private val pathPackage: String,
) : DialogWrapper(false) {

    private val maxEditTextLength = 30
    private val singleLineHeight = 40

    private var editKeyword = JTextField(parentPath, maxEditTextLength)
    private var editActivityName = JTextField("${parentPath}Activity", maxEditTextLength)
    private var editViewModelName = JTextField("${parentPath}ViewModel", maxEditTextLength)
    private var editActivityLayoutName = JTextField("${parentPath.toLowerCase()}_activity", maxEditTextLength)
    private var editPackageName = JTextField(pathPackage, maxEditTextLength)

    init {
        title = QuickTemplateBundle.message("dialog.new.activityTemplate.title")
        setCancelButtonText(QuickTemplateBundle.message("dialog.new.activityTemplate.cancel"))
        setOKButtonText(QuickTemplateBundle.message("dialog.new.activityTemplate.ok"))
        init()
    }

    override fun createCenterPanel(): JComponent {
        val dialogPanel = JPanel().apply {
            this.border = JBEmptyBorder(10, 5, 10, 5)
            this.minimumSize = Dimension(800, 450)
            this.layout = BoxLayout(this, BoxLayout.PAGE_AXIS)
        }
        dialogPanel.add(JLabel(QuickTemplateBundle.message("dialog.new.activityTemplate.tip")).apply {
            this.font = Font(this.font.fontName, Font.BOLD, 16)
            this.alignmentX = Component.LEFT_ALIGNMENT
        })
        dialogPanel.add(JLabel(QuickTemplateBundle.message("dialog.new.activityTemplate.tipDescription")).apply {
            this.font = Font(this.font.fontName, Font.PLAIN, 12)
            this.border = JBEmptyBorder(5, 0, 5, 0)
            this.alignmentX = Component.LEFT_ALIGNMENT
        })
        // 编辑区域,含 Key Word 、 Activity Name 、 ViewModel Name 、 Activity Layout Name 、 Package Name 的编辑
        val editContent = JPanel().apply {
            this.border = JBEmptyBorder(0, 10, 0, 0)
            this.alignmentX = Component.LEFT_ALIGNMENT
            this.layout = BoxLayout(this, BoxLayout.PAGE_AXIS)
        }
        // Key Word
        editContent.add(createTipJLabel(QuickTemplateBundle.message("dialog.new.activityTemplate.tipKeyword")))
        editContent.add(configEditJTextField(editKeyword))
        editKeyword.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent?) {
                keywordUpdate()
            }

            override fun removeUpdate(e: DocumentEvent?) {
                keywordUpdate()
            }

            override fun changedUpdate(e: DocumentEvent?) {
                keywordUpdate()
            }
        })
        // Activity Name
        editContent.add(createTipJLabel(QuickTemplateBundle.message("dialog.new.activityTemplate.tipActivityName")))
        editContent.add(configEditJTextField(editActivityName))
        // ViewModel Name
        editContent.add(createTipJLabel(QuickTemplateBundle.message("dialog.new.activityTemplate.tipViewModelName")))
        editContent.add(configEditJTextField(editViewModelName))
        // Activity Layout Name
        editContent.add(createTipJLabel(QuickTemplateBundle.message("dialog.new.activityTemplate.tipActivityLayoutName")))
        editContent.add(configEditJTextField(editActivityLayoutName))
        // Package Name
        editContent.add(createTipJLabel(QuickTemplateBundle.message("dialog.new.activityTemplate.tipPackageName")))
        editContent.add(configEditJTextField(editPackageName))

        dialogPanel.add(editContent)
        return dialogPanel
    }

    override fun doOKAction() {
        // 检查输入是否合法
        val warnMessage = when {
            editActivityName.text.isEmpty() -> {
                QuickTemplateBundle.message("dialog.warn.activityTemplate.emptyActivityName")
            }
            editViewModelName.text.isEmpty() -> {
                QuickTemplateBundle.message("dialog.warn.activityTemplate.emptyViewModelName")
            }
            editActivityLayoutName.text.isEmpty() -> {
                QuickTemplateBundle.message("dialog.warn.activityTemplate.emptyActivityLayoutName")
            }
            editPackageName.text.isEmpty() -> {
                QuickTemplateBundle.message("dialog.warn.activityTemplate.emptyPackageName")
            }
            else -> {
                ""
            }
        }
        if (warnMessage.isNotEmpty()) {
            WarnDialog(warnMessage).show()
        } else {
            ApplicationManager.getApplication().executeOnPooledThread {
                // 新建 layout 布局文件

                // 新建 ViewModel 文件

                // 新建 Activity 文件



                val fileTemplateManager = FileTemplateManager.getInstance(project)
                // 生成传递给模版的值
                val templateProperties = fileTemplateManager.defaultProperties
                templateProperties["PATH_PACKAGE_NAME"] = editPackageName.text
                templateProperties["APP_PACKAGE"] = appPackage
                templateProperties["VIEW_MODEL_NAME"] = editViewModelName.text
                //
                val fileTemplate = fileTemplateManager.getInternalTemplate("QuickViewModel")
                FileTemplateUtil.createFromTemplate(
                    fileTemplate,
                    editViewModelName.text,
                    templateProperties,
                    chooseDirectory
                )
            }
            super.doOKAction()
        }
    }

    private fun keywordUpdate() {
        if (editKeyword.text.isNotEmpty()) {
            val keyWord = CustomPathUtil.getFirstUpperCasePath(editKeyword.text.toLowerCase())
            editActivityName.text = "${keyWord}Activity"
            editViewModelName.text = "${keyWord}ViewModel"
            editActivityLayoutName.text = "${keyWord.toLowerCase()}_activity"
        }
    }

    private fun createTipJLabel(text: String): JLabel = JLabel(text).apply {
        this.font = Font(this.font.fontName, Font.PLAIN, 14)
        this.border = JBEmptyBorder(10, 0, 5, 0)
        this.alignmentX = Component.LEFT_ALIGNMENT
    }

    private fun configEditJTextField(jTextField: JTextField): JTextField {
        return jTextField.apply {
            this.preferredSize = Dimension(this.preferredSize.width, singleLineHeight)
            this.maximumSize = Dimension(this.maximumSize.width, singleLineHeight)
            this.alignmentX = Component.LEFT_ALIGNMENT
        }
    }
}