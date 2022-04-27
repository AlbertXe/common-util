import com.alibaba.fastjson.JSON
import com.groovy.BaseDTO

import java.lang.reflect.Field

//class HelloWord{
//    public static void main(String[] args) {
//        println("hello world")
//        println(plus(1,2))
//    }
//}

static def plus(a,b) {
    println(a + b)
    return a + b
}

plus(1, 2)

plus(2,3)


import java.nio.file.Files

def changeFileName(basePath,oldName,newName) {
    def files = Files.find(basePath)
    files.each {
        println(it)
    }
}

changeFileName("C:\\Users\\Administrator\\.ssh")

baseFields = ['crtTms','crtUsr'] as LinkedHashSet<String>
shardingKey = 'userId'

fields = baseFields+shardingKey as LinkedHashSet<String>

println fields



parseJava()

def parseJava(String fileName){
    def fields = BaseDTO.class.getDeclaredFields()
    def fieldMap = [:]
    fields.each {e->
        println e
        fieldMap.put(e.getName(),e.toGenericString())
    }
    println fieldMap

    println fieldMap.id

    fields.eachWithIndex { Field field, int i ->
        println i+" "+field.getName()
    }
}



