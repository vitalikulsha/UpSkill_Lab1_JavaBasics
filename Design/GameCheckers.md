# Requirements for a system
## Functional requirements
1. Object of the game: pieces (checkers) and a board.
2. Number of players: automatic 1, optional 2 (play against each other)
3. At the start of the game, the pieces are automatically placed in position.
4. Victory: 
    * by agreement of the parties; 
    * if the opponent has no pieces.
5. Draw:
    * by agreement of the parties;
    * if after 15 moves there has been no capture of a piece.
6. When a pawn reaches the opposite edge of the board, it becomes a queen.
## Non-functional
1. Game menu.
2. Registration at the entrance to the game.
3. Help describing the rules of the game.
# Use cases
## 1. Launching the game
|          Пункт USE_CASES | Описание пункта |
|:-----------|:------------|
| __Действующие лица__ | Система, игрок |
| __Цель__ | Начать игру |
| __Успешный сценарий__ | 1. Игрок запускает приложение.
|| 2. Система запускает приложение и выводит на экран кнопку "Start".
|| 3. Игрок нажимает кнопку "Start".
|| 4. Система предлагает выбрать цвет пешек.,
|| 5. Игрок выбирает цвет пешек.
|| 6. Система расставляет пешки по исходным позициям. Игроку с белыми пешками предосталяется право первого хода.
