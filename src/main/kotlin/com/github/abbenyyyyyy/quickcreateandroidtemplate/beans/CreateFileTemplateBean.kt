package com.github.abbenyyyyyy.quickcreateandroidtemplate.beans

import com.intellij.psi.PsiDirectory
import java.util.Properties

data class CreateFileTemplateBean(
    /**
     * 模版名
     */
    val templateName: String,
    /**
     * 新建的文件名
     */
    val fileName: String,
    /**
     * 替换模版中的变量
     */
    val properties: Properties,
    /**
     * 新建文件的目录
     */
    val directory: PsiDirectory,
)