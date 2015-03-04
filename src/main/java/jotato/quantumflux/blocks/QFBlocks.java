package jotato.quantumflux.blocks;

public class QFBlocks
{
    public static BlockEntropyAccelerator entropyAccelerator;
    public static BlockQuibitCluster quibitCluster_1;
    public static BlockQuibitCluster quibitCluster_2;
    public static BlockQuibitCluster quibitCluster_3;
    public static BlockQuibitCluster quibitCluster_4;
    public static BlockQuibitCluster quibitCluster_5;
    public static BlockZPE zpe;
    public static BlockRFEntangler rfEntangler;
    public static BlockRFExciter rfExciter;

    public static void init()
    {
        entropyAccelerator = new BlockEntropyAccelerator();
        quibitCluster_1 = new BlockQuibitCluster(1);
        quibitCluster_2 = new BlockQuibitCluster(2);
        quibitCluster_3 = new BlockQuibitCluster(3);
        quibitCluster_4 = new BlockQuibitCluster(4);
        quibitCluster_5 = new BlockQuibitCluster(5);
        zpe = new BlockZPE();
        rfEntangler = new BlockRFEntangler();
        rfExciter = new BlockRFExciter();
    }
}
