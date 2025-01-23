package me.ivanmart.plaiaundi.Menus;

public class AdminMenu {

    public void start(){
        System.out.println("""
                +------------------------------+
                | Selecciona como vas a seguir |
                | 1. Cliente                   |
                | 2. Administrador             |
                +------------------------------+
                """);
        int c;
        do {
            c = Util.getInt();
            switch (c){
                case 1:
                    ClientMenu menu = new ClientMenu();
                    menu.start();
                    break;
                case 2:
                    continuar();
                    break;
                default:
                    System.out.println("[Info] Selecciona un valor válido.");
                    break;
            }
        }while (c!=1 && c != 2);
    }

    private void continuar(){
        // TODO
    }
}
