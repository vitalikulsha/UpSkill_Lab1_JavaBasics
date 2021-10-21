# SOLUTION
## Functional requirements
1. The Object of the game: pieces (checkers) and a board.
2. Number of players: automatic 1 (play with computer), optional 2 (play against each other)
3. At the start of the game, the pieces are automatically placed in position.
4. Victory: 
    * by agreement of the parties; 
    * if the opponent has no pieces.
5. Draw:
    * by agreement of the parties;
    * if there has been no capture of a piece after 15 moves
6. When a pawn reaches the opposite edge of the board, it becomes a queen.
7. Game menu.
## Non-functional
1. Gameplay visualization. 
2. Registration at the entrance to the game.
3. Guide describing the rules of the game
4. Various difficulty levels.
## Description of design entities
| Entities | Attributes | Relationships |
|:----------------|:---------------|:--------------------|
| __Game__ | id: int <br> board: Board <br> player: Player <br> isGameOver:boolean <br> menu: Menu| When the system starts up, a game is created. When the game starts, a game board is created and the players are automatically set to the initial state of the game. The game state can be forcedly changed from the menu. The game state changes automatically depending on the player's status. |
| __Player__ | id: int <br> name: String <br> checkers:list\<Checker\> <br> status: Enum | The player has a set of checkers consistent with the set of checkers in the game. The player can move checkers according to the rules. By moving a checker, the player changes its position on the board. The player has a status that affects the state of the game.|
|__Checker__|id: int <br> color: String <br> position: int[][] <br> isQueen: boolean|A checker has color, position and status. The checker moves around the board with the help of the player. One player can only have checkers of one specific color.|
|__Board__|id: int <br> table: Cell[8][8]|The game board consists of a set of black and white cells.|
|__Cell__|id: int <br> size: int <br> color: String <br> isEmpty: boolean|The cell has its own size, color and state: empty or busy. Checkers can only move on black cells and if this cell is.|
