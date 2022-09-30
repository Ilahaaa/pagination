        <%@ page import="java.sql.Connection" %>
<%@ page import="com.example.demo.dao.UserDAO" %>
<%@ page import="com.example.demo.dao.DbConnection" %>
<%@ page import="com.example.demo.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<table >
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Age</th>


    </tr>
    </thead>
    <tbody>

  <%  UserDAO dao = new UserDAO(DbConnection.connectDb());
      String p = request.getParameter("page");

      int _p = 1;
      if (p != null) {
          _p = Integer.parseInt(p);
      }
      int total_rows = dao.total();
      int npp = 15;

      List<User> list = dao.getAllUsers(_p, npp);
      for (User u : list
      ) {

  %>

    <tr>

        <th><%=u.getId()%></th>
        <th><%=u.getName()%></th>
        <th><%=u.getSurname()%></th>
        <th><%=u.getAge()%></th>


    </tr>

    <%
        }
    %>
    </tbody>

</table>

<%
    int paging_count=total_rows / npp;
    if( total_rows % npp > 0) {
        paging_count = paging_count + 1;
     }
    if( paging_count > 1 ) {
        out.print(dao.pagination(paging_count, _p));
    }

    //int paging_count = total_rows/npp;  //6

%>

<!--a href="http://localhost:8080/demo_war_exploded?page=<%=_p - 1%>">Previous</a>
<a href="http://localhost:8080/demo_war_exploded?page=<%=_p + 1%>">Next</a-->


</body>
</html>