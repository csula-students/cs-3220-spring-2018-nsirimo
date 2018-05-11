<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <title>Code Clicker Game</title>
        <link rel="stylesheet" href="app.css">
        <script>
            window.game.state = {};
            for (i = 0; i < state.generators.length; i++) {
                state.genertors[i].quantity = 0;
                state.generators[i].unlockValue = state.generators[i].unlockAt;
            }
            for (i = 0; i < state.story.length; i++) {
                state.story[i].state = 'hidden';
                state.story[i].triggeredAt = state.story[i].triggerAt;
            }
            window.game.state = state; // where state is passed from Controller as JSON string
        </script>
    </head>

    <body>
        <header>
            <h1>Doge Coin!</h1>
            <h1>CLICK IT MAN!</h1>
        </header>
        <main>
            <game-story-book></game-story-book>

            <div id="curr">
                <div class="container">
                    <game-counter></game-counter>
                    <game-button></game-button>
                </div>
            </div>

            <div id="market">
                <div class="container">
                    <c:forEach var="i" begin="0" end="${lastGen}">
                        <game-generator data-id="${i}"></game-generator>
                    </c:forEach>
                </div>
        </main>
        <script src="app.bundle.js"></script>
    </body>

    </html>