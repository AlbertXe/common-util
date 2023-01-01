package com.groovy.Ac

import groovy.text.GStringTemplateEngine

tables = getTables("D:\\idea_study2\\common-util\\common\\src\\main\\java\\com\\groovy\\Ac\\Ac.tables.xml")

failures = generateAcTables()

def bindings = [:]

def generateAcTables() {

}

def getTables(String tableFile) {
    def file = new File(tableFile)
    def tables = new XmlParser().parse(file).children()

    println(tables)
    tables.each {tbl->
        println(tbl)
    }
    wrireFile("dao.tpl",bindings)
}

def wrireFile(fileName,bindings) {
    def f = new File(fileName)
    def engine = new GStringTemplateEngine()
    def template = engine.createTemplate(f).make(bindings)
    println(template.toString())

}