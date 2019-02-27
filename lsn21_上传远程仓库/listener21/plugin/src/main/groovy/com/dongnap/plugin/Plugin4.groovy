package com.dongnap.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class Plugin4 implements Plugin<Project>{

    @Override
    void apply(Project project) {
        println("plugin4")
    }
}