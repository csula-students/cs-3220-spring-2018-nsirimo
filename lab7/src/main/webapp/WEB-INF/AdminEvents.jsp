<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Doge Coin Game</title>
        <link rel="stylesheet" type="text/css" href="../custom.css">
    </head>

    <body></body>
    <div class="main">
        <h1 class="pageTitle">Dat Doge Coin Gen!</h1>
        <nav class="navBar">
            <a href="admin-info.html">Game Information</a>
            <a href="admin-generators.html">Coin Generators</a>
            <a href="admin-events.html">
                <div class="navTitle">Events</div>
            </a>
        </nav>
    </div>
    <div class="container">
        <div class="genForm">
            <form method="POST">
                <label for="event_name">Event Name:</label>

                <input type="text" id="event_name" name="event_name" value="" required>
                <label for="description">Event Description</label>

                <textarea id="description" name="description" value="" required></textarea>
                <label for="trigger">Trigger at</label>

                <input type="number" id="trigger" name="trigger" value="" required>

                <input type="submit" name="status" value="add"></input>
            </form>
        </div>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Trigger At</th>
            </tr>
            <c:forEach items="${data}" var="event">
                <form method="POST">
                <tr>
                    <td>${event.getName()}</td>
                    <td>${event.getDescription()}</td>
                    <td>${event.getTriggerAt()}</td>
                    <td> <a href="../admin/events/delete?id=${event.getId()}">Delete</a></td>
                </tr>
            </form>
            </c:forEach>
        </table>
    </div>
    </div>
    </div>
</body>

</html>