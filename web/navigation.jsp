
<ul class="nav justify-content-center">
    <li class="nav-item">
        <a class="nav-link" href="CategoryServlet">Add question</a>
    </li>
    <%
        HttpSession sessionn = request.getSession();

        int roleId = (int) sessionn.getAttribute("roleId");

        if(roleId==1) {
            out.println("  <li class=\"nav-item\">\n" +
                    "            <a class=\"nav-link active\" href=\"UserControllerServlet\">Your profile</a>\n" +
                    "              </li>    <li class=\"nav-item\">\n" +
                    "        <a class=\"nav-link\" href=\"QuestionController\" >All questions</a>\n" +
                    "    </li>");



        } else if (roleId==2) {
            out.println("  <li class=\"nav-item\">\n" +
                    "            <a class=\"nav-link active\" href=\"QuestionController\">ACCEPT THE QUESTIONS</a>\n" +
                    "              </li> ");
        }else if (roleId==3) {
            out.println("  \n" +
                    "    <li class=\"nav-item\">\n" +
                    "        <a class=\"nav-link\" href=\"QuestionController\" >All questions</a>\n" +
                    "    </li> ");
        }

    %>

    <li class="nav-item">
        <a class="nav-link" href="UsersQuestionController" >My questions</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="index.jsp">Logout</a>
    </li>
</ul>






