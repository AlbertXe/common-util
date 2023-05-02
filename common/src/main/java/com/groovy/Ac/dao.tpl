package ${basePackage};

public class ${id}Dao{
    <% fieldList.each { it->%>
    private String ${it.id};<%}%>
    <% fieldList.each { it->%>
        public void get${it.id}(){
        }
        <%}%>
}