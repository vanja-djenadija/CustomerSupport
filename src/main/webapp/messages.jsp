<%--
  Created by IntelliJ IDEA.
  User: Korisnik
  Date: 30.1.2023.
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.example.customersupport.beans.MessageBean" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="messageService" class="com.example.customersupport.services.MessageService" scope="application"/>
<jsp:useBean id="adminBean" class="com.example.customersupport.beans.AdminBean" scope="session"/>
<!doctype html>
<%
    if (!adminBean.isLoggedIn())
        response.sendRedirect("sign-in.jsp");
%>
<html lang="en">
<head>
    <title>Messages</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#example').DataTable();
        });
    </script>
</head>
<body style="margin:20px auto">
<%@include file="WEB-INF/header.jsp" %>
<main role="main" class="container">
    <div class="container">
        <br><br>
        <h2>Messages</h2>
        <table id="example" class="table table-striped" style="width:100%">
            <caption>Messages</caption>
            <thead>
            <tr>
                <th scope="col">Id</th>
                <!-- TODO: Set max width for column. -->
                <th scope="col">Content</th>
                <th scope="col">User e-mail</th>
                <th scope="col">Read</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <% for (MessageBean message : messageService.getMessages()) { %>
            <tr>
                <td>
                    <%=message.getId()%>
                </td>
                <td>
                    <%=message.getContent()%>
                </td>
                <td>
                    <%=message.getUserMail()%>
                </td>
                <%if (!message.isRead())%>
                <td>
                    <label for="isRead"><%=message.isRead() ? "YES" : "NO"%>
                    </label>
                    <input class="form-check-input" type="checkbox" id="isRead"
                           style="margin-left: 20px" <%=message.isRead() ? "checked" : ""%>
                           disabled/>
                </td>
                <td>
                    <button type="button" class="btn btn-primary view-button"
                            onclick="location.href='message.jsp?id=<%=message.getId()%>'"><i class="fa fa-eye"></i>
                        &nbsp;View
                    </button>
                </td>
            </tr>
            <% } %>

            </tbody>
            <tfoot>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Content</th>
                <th scope="col">User e-mail</th>
                <th scope="col">Read</th>
                <th scope="col">Actions</th>
            </tr>
            </tfoot>
        </table>
    </div>
</main>
</body>
</html>