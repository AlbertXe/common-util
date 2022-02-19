package com.groovy

import java.nio.file.Files

def changeFileName(basePath,oldName,newName) {
    def files = Files.find(basePath)
    files.each {
        println(it)
    }
}

changeFileName("C:\\Users\\Administrator\\.ssh")

