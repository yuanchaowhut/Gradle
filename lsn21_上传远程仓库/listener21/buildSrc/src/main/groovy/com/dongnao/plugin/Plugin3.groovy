package com.dongnao.plugin

import com.google.gson.Gson
import org.gradle.api.Plugin
import org.gradle.api.Project

class Plugin3 implements Plugin<Project>{

    @Override
    void apply(Project project) {
        println("plugin3")
        Gson gson


    }
}