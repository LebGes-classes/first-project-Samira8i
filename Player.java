
public class Player {
    private int x, y;
    private Control control;

    public Player(int startX, int startY, Control control) {
        this.x = startX;
        this.y = startY;
        this.control = control;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    
    public void moveUp() {
        if (control.isValidMove(x - 1, y)) {
            x--;
        }
    }

    
    public void moveDown() {
        if (control.isValidMove(x + 1, y)) {
            x++;
        }
    }

   
    public void moveRight() {
        if (control.isValidMove(x, y + 1)) {
            y++;
        }
    }

   
    public void moveLeft() {
        if (control.isValidMove(x, y - 1)) {
            y--;
        }
    }
}