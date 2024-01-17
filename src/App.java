import java.util.Scanner;

public class App {
  
    public static int getinput(String description){
        Scanner scan = new Scanner(System.in);
        System.out.println(description);
        int movement = scan.nextInt();

        return movement;
    }

     public static int wallcolloidcheck(int currentoperator, int keypress){
        if(currentoperator == 0 && keypress == 8){
            currentoperator = 18;
        }
        if(currentoperator == 19 && keypress == 2){
            currentoperator = 1;
        }
        if(currentoperator == 0 && keypress == 4){
            currentoperator = 38;
        }
        if(currentoperator > 38 && keypress == 6){
            currentoperator = 2;
        }
       
        return currentoperator;
     }
   
     public static boolean playercolloidcheck(char[][] maze,int pos_x,int pos_y){
        boolean colloided = false;
       if(maze[pos_x][pos_y] == '|' || maze[pos_x][pos_y] == '='){
        colloided = true;
       }else{
         colloided = false;
       }
        return colloided;
     }
     
     public static void endgame(String gameover){
        System.out.println(gameover);
        System.exit(0);
     } 
   
     public static int add_dollor(char[][] maze,int pos_x,int pos_y,int dollor_count){
        if(maze[pos_x][pos_y] == '$'){
            dollor_count = dollor_count + 1;
        }else{
            dollor_count = dollor_count ;
        }
        return dollor_count;
     }
 
    public static void mazeupdate(char[][] maze,char player,int pos_x,int pos_y,int dollor_count) {
        int prev_x = pos_x;
        int prev_y = pos_y;
        System.out.println("8 -> up , 2 -> down , 6 -> right , 4 -> left");
        int keypress = getinput("enter only the following above numbers");
        switch(keypress){
            case 8 : pos_x = pos_x - 1;break;
            case 2 : pos_x = pos_x + 1;break;
            case 4 : pos_y = pos_y - 2;break;
            case 6 : pos_y = pos_y + 2;break;
            default : System.out.println("you entered the number that not even mentioned above");
        }
       dollor_count =  add_dollor(maze,pos_x,pos_y,dollor_count);
       pos_x = wallcolloidcheck(pos_x ,keypress);
       pos_y = wallcolloidcheck(pos_y,keypress);
       boolean gameover =  playercolloidcheck(maze,pos_x,pos_y);
       if(gameover == true){
         endgame("Game Over ........");
       }
       maze[pos_x][pos_y] = player;
       maze[prev_x][prev_y] = '.'; 
       
        System.out.println("your score:" + dollor_count);
      
        draw(maze);
        update(maze, player, pos_x, pos_y,dollor_count);
      }
 
      public static int randomNogenertor(int max,int min){
        int position = (int)(Math.random()*(max-min)+min);
        return position;
      }

    public static void update(char[][] maze,char player,int pos_x,int pos_y,int dollor_count){
        mazeupdate(maze, player, pos_x, pos_y,dollor_count);
    }
    
    public static void draw(char[][] maze){
        for(int i =0;i<20;i++ ){
            for(int j=0;j<41;j++){
                System.out.print(maze[i][j]);
            }
            System.out.println();
         }
    }

    public static void checkboundary(int pos_x,int pos_y) {
        if(pos_x >= 38){
            pos_x = 38;
        }
        if(pos_y >= 18){
            pos_y = 18;
        }
    }
   
    public static int spawnevenlocation(int current_y){
        if(current_y % 2 == 0){
            current_y = current_y;
        }
        if(current_y % 2 != 0){
            current_y = current_y + 1;
        }
        return current_y;
    }

    public static void spawnmoney(char[][] maze,char dollor,int no_ofdollor){
      for(int i = 0;i< no_ofdollor;i++){
        int money_x = randomNogenertor(18, 1);
        int money_y = randomNogenertor(38, 1);
        money_y = spawnevenlocation(money_y);
        if(maze[money_x][money_y] == '|' || maze[money_x][money_y] == '=' || maze[money_x][money_y] == '@'){
            money_x = randomNogenertor(18, 1);
            money_y = randomNogenertor(38, 1);
        }
         maze[money_x][money_y] = dollor;
      }
    }

    public static char checkmazeborder(char maze[][],int pillarpattern_x,int pillarpattern_y,char pillar){
  for(int i = 0;i<20;i++){
    for(int j = 0;j<41;j++){
        if((j == 0 && maze[i][j] == '=') || (j == 40 && maze[i][j] == '=')){
            maze[i][j] = '|';
            pillar = maze[i][j];
        } 
        if((i == 0 && maze[i][j] == '|') || (i == 19 && maze[i][j] == '|')){
            maze[i][j] = '=';
            pillar = maze[i][j];
        } 
        
       
    }
   
  }
       
         return pillar;
    }
    

    public static void generatemazepattern(char[][] maze,int lvl_of_hardness){
        char pillartypes[] = {'|','=','|','=','|','=','|','=','|','=','|','=','|','='};
        for(int i = 0; i<20;i++){

            for(int j = 0; j<41;j++){
              
               if(j % 2 == 0){
                maze[i][j] = '.';
               }
               if(j % 2 != 0){
                maze[i][j] = ' ';
               }
               if( i == 0){
                maze[i][j] = '-';
               }
               if(i == 19){
                maze[i][j] = '-';
               }
               if(j == 0){
                maze[i][j] = ':';
               }
               if(j == 40){
                maze[i][j] = ':';
               }
            }
        }
        int hardness = 0;
        switch(lvl_of_hardness){
            case 1: hardness = 100;break;
            case 2: hardness = 150;break;
            case 3: hardness = 200;break;
            default : System.out.println(" u didn't select the proper number hence it choose hard lvl"); hardness = 400;break; 
        }
        for(int k = 0; k< hardness;k++){
            int pillarpattern_x = randomNogenertor(19,0);
            int pillarpattern_y = randomNogenertor(40, 0);
            pillarpattern_y = spawnevenlocation(pillarpattern_y);
            char pillar = pillartypes[(int)(Math.random()*(13-0)+0)];
            pillar  =  checkmazeborder(maze,pillarpattern_x,pillarpattern_y,pillar);
            maze[pillarpattern_x][pillarpattern_y] = pillar;
        }
    }

    public static void main(String[] args)  {
        char player = '@';
        char dollor = '$';
        int no_ofdollor = 20;
        int dollor_count = 0;
        int lvl_of_hardness = 0;
        int pos_x = randomNogenertor(18,1);
        int pos_y = randomNogenertor(38, 1);         
        pos_y =  spawnevenlocation(pos_y);
        checkboundary(pos_x,pos_y);
        char maze[][] = new char[20][41];
        System.out.println("select the lvl of hardness u want");
        System.out.println("1.easy");
        System.out.println("2.medium");
        System.out.println("3.hard");
        lvl_of_hardness = getinput("choose the corresponding number for lvl_of_ hardness");
        generatemazepattern(maze,lvl_of_hardness);
        spawnmoney(maze, dollor, no_ofdollor);
        maze[pos_x][pos_y] = player;
        draw(maze);
        update(maze,player,pos_x,pos_y,dollor_count);     
    }

 
}
  
