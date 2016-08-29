package com.mlick.demo.animation.view;

//游戏规则以及机器自动走的规则
public class LoadUtil {
    //    public static int sizeSquares = 5; //设置棋盘上的尺寸
    //    public static int isRBplayer = 1; //轮到谁走棋  1 表示红方或者黑方  0 表示蓝方或者白方
//    public static int pcSquares[][] = new int[sizeSquares + 1][sizeSquares + 1]; //期盘的大小
//    public static int HistoryTable[] = new int[1000]; //历史表
    public static class Pos  //内部类定义棋盘的走向
    {
        boolean up, down;
        boolean right, left;
    }

    //初始化棋盘
//    public static void initSquares() {
//        for (int i = 0; i <= 5; i++) {
//            for (int j = 0; j <= 5; j++) {
//                pcSquares[i][j] = 0;
//            }
//        }
//    }

    /**
     * @param map 棋盘的棋盘
     * @param x   从0开始记起
     * @param y   从0 开始记起
     * @return 返回是否组成了comber 需要走多少步 5子连珠(包括五斜、水平线和垂直线上) 多两步; 3斜、4斜和方 多一步
     */
    public static int mapJudgment(int map[][], int x, int y) {
        Pos pos = new Pos();
        int cur = map[x][y]; //标记当前的棋子
        int i, j;
        int ret = 0; //返回当前要走的步数
        int score;
        boolean uflag;

        // square
        pos.up = (x - 1 >= 1 && cur == map[x - 1][y]) ? true : false;
        pos.left = (y - 1 >= 1 && cur == map[x][y - 1]) ? true : false;
        pos.down = (x + 1 <= 5 && cur == map[x + 1][y]) ? true : false;
        pos.right = (y + 1 <= 5 && cur == map[x][y + 1]) ? true : false;

        if ((pos.up || pos.right) && (pos.left || pos.right)) {
            if (pos.up && pos.left) if (cur == map[x - 1][y - 1]) {
                //Toast.makeText(null, "乘方", Toast.LENGTH_LONG).show();
                ret++;
            }
            if (pos.up && pos.right) if (cur == map[x - 1][y + 1]) ret++;
            if (pos.down && pos.left) if (cur == map[x + 1][y - 1]) ret++;
            if (pos.down && pos.right) if (cur == map[x + 1][y + 1]) ret++;
        }


        uflag = true;
        for (i = 0, j = y; i < 5; i++)//5子连珠 对角线 左高右低
            if (cur != map[i][j]) {
                uflag = false;
                break;
            }

        if (uflag == true) ret += 2;

        uflag = true;
        for (i = x, j = 0; j < 5; j++)//5子连珠 对角线 右高左低
            if (cur != map[i][j]) {
                uflag = false;
                break;
            }
        if (uflag == true) ret += 2;

        uflag = true;
        score = 0;
        for (i = x - 1, j = y - 1; i >= 1 && j >= 1; i--, j--)
            if (cur != map[i][j]) {
                uflag = false;
                break;
            } else score++;
        if (uflag == true) {
            for (i = x + 1, j = y + 1; i <= 5 && j <= 5; i++, j++)
                if (cur != map[i][j]) {
                    uflag = false;
                    break;
                } else score++;
            if (uflag == true) switch (score) {
                case 4:
                    ret++;
                case 2:
                case 3:
                    ret++;
                default:
                    break;
            }
        }

        uflag = true;
        score = 0;
        for (i = x - 1, j = y + 1; i >= 1 && j <= 5; i--, j++)
            if (cur != map[i][j]) {
                uflag = false;
                break;
            } else score++;
        for (i = x + 1, j = y - 1; i <= 5 && j >= 1; i++, j--)
            if (cur != map[i][j]) {
                uflag = false;
                break;
            } else score++;
        if (uflag == true) switch (score) {
            case 4:
                ret++;
            case 2:
            case 3:
                ret++;
            default:
                break;
        }
        return ret;
    }


}
