import com.alibaba.fastjson.JSON

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



