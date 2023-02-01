import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ingreso = new Scanner(System.in);
        int tablero[][] = new int[3][3];
        String seguir = "";
        System.out.println("BIENVENIDO AL ROMPECABEZA");
        int ganar = 0;
        do{
            generarTablero(tablero);
            do {
                mostarTablero(tablero);
                movimiento(tablero);
                ganar = ganador(tablero);
                if(ganar == 1){
                    System.out.println("Felicidades ha ganado el juego!");
                }
            }while(ganar == 0);
            System.out.println("¿Desea volver a jugar?");
            seguir = ingreso.nextLine();
            seguir = seguir.toLowerCase();
        }while(seguir.equals("si"));
    }

    public static void generarTablero(int tablero[][]){
        int encontrado = 0;
        reiniciarTablero(tablero);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                int numero = (int)(Math.random()*9 + 1);
                encontrado = busquedaRepetido(tablero,numero);
                if(encontrado == 1){
                    j--;
                }else{
                    tablero[i][j] = numero;
                }
            }
        }
    }

    public static void mostarTablero(int tablero[][]){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(tablero[i][j] != 9){
                    System.out.print("[ " + tablero[i][j] + " ]");
                }else{
                    System.out.print("[   ]");
                }
            }
            System.out.println("");
        }
    }

    public static void ubicacion(int tablero[][]){
        if(tablero[0][0] == 9){
            System.out.println("¿Desea mover el " + tablero[0][1] + " o el " + tablero[1][0]);
        }else if(tablero[0][1] == 9){
            System.out.println("¿Desea mover el " + tablero[0][0] + " el " + tablero[1][1] + " o el " + tablero[0][2]);
        }else if(tablero[0][2] == 9){
            System.out.println("¿Desea mover el " + tablero[0][1] + " o el " + tablero[1][2]);
        }else if(tablero[1][0] == 9){
            System.out.println("¿Desea mover el " + tablero[0][0] + " el " + tablero[1][2] + " o el " + tablero[2][0]);
        }else if(tablero[1][1] == 9){
            System.out.println("¿Desea mover el " + tablero[0][1] + " el " + tablero[1][0] + " el " + tablero[1][2]
                    + " o el " + tablero[2][1]);
        }else if(tablero[1][2] == 9){
            System.out.println("¿Desea mover el " + tablero[0][2] + " el " + tablero[1][1] + " o el " + tablero[2][2]);
        }else if(tablero[2][0] == 9){
            System.out.println("¿Desea mover el " + tablero[1][0] + " o el " + tablero[2][1]);
        }else if(tablero[2][1] == 9){
            System.out.println("¿Desea mover el " + tablero[2][1] + " el " + tablero[1][1] + " o el " + tablero[2][2]);
        }else{
            System.out.println("¿Desea mover el " + tablero[2][1] + " o el " + tablero[1][2]);
        }
    }

    public static int busquedaRepetido(int tablero[][],int numero){
        int encontrado = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(tablero[i][j] == numero){
                    encontrado = 1;
                    break;
                }
            }
            if(encontrado == 1){
                break;
            }
        }
        return encontrado;
    }

    public static void reiniciarTablero(int tablero[][]){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                tablero[i][j] = 0;
            }
        }
    }

    public static void movimiento(int tablero[][]){
        Scanner in = new Scanner(System.in);
        int filasColumnas[] = new int[2];
        int numAMover[] = new int[2];
        int error = 0;
        ubicacion(tablero);
        int movi = in.nextInt();
        error = chequearMovimiento(tablero,movi);
        if(error == 1){
            System.out.println("Error al mover");
        } else{
            filasColumnas = busqueda(tablero,movi);
            numAMover = busqueda(tablero,9);
            tablero[filasColumnas[0]][filasColumnas[1]] = 9;
            tablero[numAMover[0]][numAMover[1]] = movi;
        }

    }

    public static int ganador(int tablero[][]){
        int ganar = 0;
        if(tablero[0][0] == 1 &&
        tablero[0][1] == 2 &&
        tablero[0][2] == 3 &&
        tablero[1][0] == 4 &&
        tablero[1][1] == 5 &&
        tablero[1][2] == 6 &&
        tablero[2][0] == 7 &&
        tablero[2][1] == 8 &&
        tablero[2][2] == 9){
            ganar = 1;
        }
        return ganar;
    }

    public static int[] busqueda(int tablero[][],int num){
        int filasYColumnas[] = new int[2];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(num == tablero[i][j]){
                    filasYColumnas[0] = i;
                    filasYColumnas[1] = j;
                }
            }
        }
        return filasYColumnas;
    }

    public static int chequearMovimiento(int tablero[][],int movi){
        int error = 0;
        if(tablero[0][0] == 9){
            if(movi != tablero[0][1] && movi != tablero[1][0]){
                error = 1;
            }
        }else if(tablero[0][1] == 9){
            if(movi != tablero[0][0] && movi != tablero[1][1] && movi != tablero[0][2]){
                error = 1;
            }
        }else if(tablero[0][2] == 9){
            if(movi != tablero[0][1] && movi != tablero[1][2]){
                error = 1;
            }
        }else if(tablero[1][0] == 9){
            if(movi != tablero[0][0] && movi != tablero[1][2] && movi != tablero[2][0]){
                error = 1;
            }
        }else if(tablero[1][1] == 9){
            if(movi != tablero[0][1] && movi != tablero[1][0] && movi != tablero[1][2] && movi != tablero[2][1]){
                error = 1;
            }
        }else if(tablero[1][2] == 9){
            if(movi != tablero[0][2] && movi != tablero[1][1] && movi != tablero[2][2]){
                error = 1;
            }
        }else if(tablero[2][0] == 9){
            if(movi != tablero[1][0] && movi != tablero[2][1]){
                error = 1;
            }
        }else if(tablero[2][1] == 9){
            if(movi != tablero[2][1] && movi != tablero[1][1] && movi != tablero[2][2]){
                error = 1;
            }
        }else{
            if(movi != tablero[2][1] && movi != tablero[1][2]){
                error = 1;
            }
        }
        return error;
    }
}