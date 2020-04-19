package com.example.dellog

import org.gradle.api.Plugin
import org.gradle.api.Project

class DelLogPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.extensions.create('dellogExtension', DelLogExtension)
        project.afterEvaluate {
            println "----- afterEvaluate -----"
            println "----- do action delete log -----"
            //在gradle 构建完之后执行
            project.logger.error("dellogExtension : " + project.dellogExtension.sourceDir)
            println "----- project.projectDir -> " + project.projectDir.toString() + " -----"
            def rootDir = project.projectDir.toString().plus(project.dellogExtension.sourceDir)
            project.logger.error(rootDir)
            DelLogUtil.delLog(new File(rootDir))
            println "----- action delete log done -----"
        }

        // 声明dellog的task
        project.task('dellog', {
            println "----- execute delete log task -----"
            project.logger.error("dellogExtension : " + project.dellogExtension.sourceDir)
            def rootDir = project.projectDir.toString().plus(project.dellogExtension.sourceDir)
            project.logger.error(rootDir)
            DelLogUtil.delLog(new File(rootDir))
            println "----- task delete log executed -----"
        })
    }
}
