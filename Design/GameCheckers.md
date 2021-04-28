# Requirements for a system
## Functional requirements
1. Object of the game: pieces (checkers) and a board.
2. Number of players: automatic 1 (play with computer), optional 2 (play against each other)
3. At the start of the game, the pieces are automatically placed in position.
4. Victory: 
    * by agreement of the parties; 
    * if the opponent has no pieces.
5. Draw:
    * by agreement of the parties;
    * if after 15 moves there has been no capture of a piece.
6. When a pawn reaches the opposite edge of the board, it becomes a queen.
7. Game menu.
## Non-functional
1. Gameplay visualization. 
2. Registration at the entrance to the game.
3. Help describing the rules of the game.
4. Various difficulty levels.
# Use cases
## 1. Launching the game
| Item use case | Description |
|:-----------|:------------|
| __Acting persons__ | System, player |
| __Goal__ | Start game. |
| __Successful scenario__ | 1. The player launches the application.
|| 2. The system launches the application and displays the "START " button.
|| 3. The player presses the "START" button.
|| 4. The system offers to choose the color of the checkers.
|| 5. The player chooses the color of checkers.
|| 6. The system places checkers in their original positions. The player with white checkers is given the right of the first move.
| __Результат__ | The game is running. The color of the checkers is distributed among the participants in the game. The player with white checkers is waiting for the first move. |
## 2. Player turn
| Item use case | Description |
|:-----------|:------------|
| __Acting persons__ | System, gamer |
| __Goal__ | The player made a move |
| __Successful scenario__ | 1. The player chooses a checker to make his move.
|| 2. The system highlights the checker and switches it into an active state.
|| 3. The player indicates the place where the checker needs to be moved.
|| 4. The system moves the checker to the specified position and passes the move to another player.
| __Результат__ | The player made a move |
| __Расширения__ | 1. If the move is unacceptable, the system issues a message about it.
|| 2. The player clicks "Allowed Moves". The system suggests the allowed moves with the selected checker.
|| 3. The player captures the opponent's checker (s) during his turn. The system removes the captured checker (s) from the game.
## 3. Victory
| Item use case | Description |
|:-----------|:------------|
| __Acting persons__ | System, gamer |
| __Goal__ | Победа в игре |
| __Successful scenario__ | 1. Реализовать сценарий "Player turn"
|| 2. После хода игрока 1 у игрока 2 отсутвуют шашки. 
|| 3. Система выдает сообщение о победе игрока 1 и предлагает начать занова/реванш.
| __Результат__ | Игрок победил. |
| __Расширения__ | 1. Если игрок 2 сдался, система выдает сообшение, что игрок 1 победил. 
|| 2. Активный игрок всегда может из меню выбрать сдаться или предложить ничью.
## 4. Draw
| Item use case | Description |
|:-----------|:------------|
| __Acting persons__ | System, gamer |
| __Goal__ | Ничья в игре. |
| __Successful scenario__ | 1. Реализовать сценарий "Player turn".
|| 2. Игроки совершили 15 безрезультатных ходов.
|| 3. Система выводит сообщение о ничьей и предлагает начать занова.
| __Результат__ | Ничья |
| __Расширения__ | 1. Если игрок не может в течении 3 минут сделать ход, система предлагает завершить игру ничьей или сдаться .
|| 2. Активный игрок всегда может из меню выбрать сдаться или предложить ничью.
## 5. The checker becomes a queen
| Item use case | Description |
|:-----------|:------------|
| __Acting persons__ | System, gamer |
| __Goal__ | Шашка становится дамкой. |
| __Successful scenario__ | 1. Реализовать сценарий "Player turn".
|| 2. Шашка игрока достигла противоположной стороны доски.
|| 3. Система переключает шашку в состояние дамка. 
| __Результат__ | Обычная шашка теперь дамка. |
