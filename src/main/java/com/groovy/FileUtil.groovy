package com.groovy

def changeFileName(basePath,oldName,newName) {
    File file = new File(basePath)
    def files = file.listFiles()
    files.each {
        println(it)
    }
}

changeFileName("C:\\Users\\Administrator\\.ssh","","")

