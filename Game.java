import java.util.Scanner;

public class Game {
    private Player player;
    private Control control;
    private int level;
    private Scanner scanner;

   
    public Game() {
        this.level = 1; 
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        control = new Control(level); 
        player = new Player(control.getStartX(), control.getStartY(), control);

        showMenu();
        int usersChoice = userAnswer();
        if (usersChoice == 1) {
            startPlay();
        } else if (usersChoice == 2) {
            showInstructions();
        } else if (usersChoice == 3) {
            System.out.println("Выход из игры...");
            return;
        }
    }

    public void showMenu() {
        clearConsole();
        System.out.println("Добро пожаловать в супер игру! Выберите, что хотите делать☺☺☺:");
        System.out.println("1. Погнали играть♥");
        System.out.println("2. Я не знаю правила, расскажи, пожалуйста☹");
        System.out.println("3. Что-то нет настроения играть:(");
        System.out.print("Выбирай: ");
    }

    public int userAnswer() {
        int choice = -1;
        while (choice < 1 || choice > 3) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 3) {
                    System.out.println("Ошибка: число должно быть от 1 до 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод, введите целое число.");
            }
        }
        return choice;
    }

    public void clearConsole() { //ваш код не работает у меня на mac, поэтому я сделала просто вот так, чтобы работало везде 
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public void showInstructions() {
        clearConsole();
        System.out.println("Правила довольно просты☺. Смотри, это просто обычный лабиринт!");
        System.out.println("♡ - сюда ты можешь ходить");
        System.out.println("♥ - а вот сюда уже не можешь");
        System.out.println("Своим передвижением нужно управлять с помощью букв: U - вверх, D - вниз, R - вправо, L - влево");
        System.out.println("Если понял правила, нажми E, чтобы вернуться в меню");

        String yourAnswer; 

        while (true) {
            yourAnswer = scanner.nextLine();
            if (yourAnswer.equalsIgnoreCase("E")) {
                start();
                return;
            } else {
                System.out.println("Я не понимаю тебя, пожалуйста, попробуй еще раз");
            }
        }
    }

    public void startPlay() {
        while (level <= 3) {
            clearConsole();
            control.displayControl(player.getX(), player.getY());
            System.out.print("Введите команду (U/D/R/L): ");
            String command = scanner.nextLine().toUpperCase();

            switch (command) {
                case "U":
                    player.moveUp();
                    break;
                case "D":
                    player.moveDown();
                    break;
                case "R":
                    player.moveRight();
                    break;
                case "L":
                    player.moveLeft();
                    break;
                default:
                    System.out.println("Неверная команда. Пожалуйста, введите U, D, R или L.");
                    continue; 
            }

           
            if (player.getX() == control.getEndX() && player.getY() == control.getEndY()) {
                System.out.println("Поздравляем! Вы прошли уровень " + level);
                if (level == 3) {
                    System.out.println("Вы прошли игру, пока-пока!");
                    return;
                }

                System.out.print("Хотите ли вы продолжить играть и перейти на следующий уровень? (ответьте 'да' или 'нет'): ");
                String answerYesOrNo;
                while (true) {
                    answerYesOrNo = scanner.nextLine().toLowerCase();
                    if (answerYesOrNo.equals("да")) {
                        level++; 
                        control = new Control(level); 
                        player = new Player(control.getStartX(), control.getStartY(), control); 
                        break;
                    } else if (answerYesOrNo.equals("нет")) {
                        System.out.println("Хорошо, тогда до встречи♥");
                        return; 
                    } else {
                        System.out.println("Я не понимаю тебя, напиши 'да' или 'нет'");
                    }
                }
            }
        }
    }
}
