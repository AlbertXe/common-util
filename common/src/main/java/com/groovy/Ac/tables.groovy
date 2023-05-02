package com.groovy.Ac

import groovy.text.GStringTemplateEngine

def bindings = [:]
tables = getTables("/Users/xiehongwei/IdeaProjects/common-util/common/src/main/java/com/groovy/Ac/Ac.tables.xml",bindings)

failures = generateAcTables('AcEvntTbl',bindings)



def generateAcTables(tableId,bindings) {
    def table = bindings.tableMap.get(tableId)
    println("generateAcTables table="+table)

    table.basePackage="com.groovy.Ac"
    wrireFile("/Users/xiehongwei/IdeaProjects/common-util/common/src/main/java/com/groovy/Ac/dao.tpl",table)


}

def getTables(tableFile,bindings) {
    def file = new File(tableFile)
    def tables = new XmlParser().parse(file)

    println("tables=" + tables)

    def tableMap = [:]
    tables.each { tbl ->
        println("tbl=" + tbl)
        def table = [:]
        table.id = tbl.@id
        table.name = tbl.@name

        def fieldMap = [:]
        def fieldList = []
        def fields = tbl.columns.field;
        fields.each { fld ->
            def field = [:]
            field.id=fld.@id
            field.name=fld.@name
            field.user=fld.@user

            fieldMap.put(field.id,field)
            fieldList.add(field)
        }
        table.fieldMap = fieldMap
        table.fieldList = fieldList
                tableMap.put(table.id,table)
    }
    bindings.tableMap=tableMap
}

def wrireFile(templateFile, binding) {
    def f = new File(templateFile)
    def engine = new GStringTemplateEngine()
    def template = engine.createTemplate(f).make(binding)

    def outFile = "/Users/xiehongwei/IdeaProjects/common-util/common/src/main/java/com/groovy/Ac/AcEvntTblDao.java"
    def writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outFile)))
    writer.write(template.toString())
    writer.flush()
    writer.close()

}