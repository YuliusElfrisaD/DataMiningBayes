
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Bayes {
    
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        
        
        System.out.println("\n===================================================="
                + "============================================================="
                + "====");
        
        System.out.println("TABEL PROBABILITAS");
        System.out.println("\t\tOUTLOOK\t\t\tTEMPERATURE\t\tHUMIDITY\t\tWINDY\t\tPLAY");
        System.out.println("\t\tYES\tNO\t\tYES\tNO\t\tYES\tNO\t\tYES\tNO\tYES\tNO");
        
//        System.out.println("Sunny\t\t"+SunnyYes()+"\t"+SunnyNo()+"\tHot\t"+HotYes()
//                +"\t"+HotNo()+"\tHigh\t"+HighYes()+"\t"+HighNo()+"\tTrue\t"+
//                TrueYes()+"\t"+TrueNo()+"\t"+PlayYes()+"\t"+PlayNo());
//        
//        System.out.println("Overcast\t"+OvercastYes()+"\t"+OvercastNo()+"\tMild\t"
//                +MildYes()+"\t"+MildNo()+"\tNormal\t"+NormalYes()+"\t"+NormalNo()+
//                "\tFalse\t"+FalseYes()+"\t"+FalseNo());
//        
//        System.out.println("Rainy\t\t"+RainyYes()+"\t"+RainyNo()+"\tCool\t"+CoolYes()
//                +"\t"+CoolNo());
//        
//        System.out.println("===================================================="
//                + "============================================================="
//                + "====");
//        
//        System.out.println("Sunny\t\t"+ProbabilitasSunnyYes()+"\t"+ProbabilitasSunnyNo()
//                +"\tHot\t"+ProbabilitasHotYes()+"\t"+ProbabilitasHotNo()+"\tHigh\t"
//                +ProbabilitasHighYes()+"\t"+ProbabilitasHighNo()+"\tTrue\t"
//                +ProbabilitasTrueYes()+"\t"+ProbabilitasTrueNo()+"\t"+ProbabilitasYes()
//                +"\t"+ProbabilitasNo());
//        
//        System.out.println("Overcast\t"+ProbabilitasNormalYes()+"\t"+ProbabilitasOvercastNo()
//                +"\tMild\t"+ProbabilitasMildYes()+"\t"+ProbabilitasMildNo()
//                +"\tNormal\t"+ProbabilitasNormalYes()+"\t"+ProbabilitasNormalNo()
//                +"\tFalse\t"+ProbabilitasFalseYes()+"\t"+ProbabilitasFalseNo());
//        
//        System.out.println("Rainy\t\t"+ProbabilitasRainyYes()+"\t"+ProbabilitasRainyNo()
//                +"\tCool\t"+ProbabilitasCoolYes()+"\t"+ProbabilitasCoolNo());
//        
//        System.out.println("===================================================="
//                + "============================================================="
//                + "====");
        
        System.out.println("PERHITUNGAN BAYES");
        System.out.print("OUTLOOK (SUNNY, OVERCAST, RAINY)\t:");
        System.out.print("TEMPERATURE (HOT, MILD, COOL)\t:");
        System.out.print("HUMIDITY (HIGH, NORMAL)\t:");
        System.out.print("WINDY (TRUE, FALSE)\t:");
        
    }

    public static Connection getKoneksi() {

        String host = "localhost";
        String port = "1521";
        String db = "xe";
        String usr = "hr";
        String pwd = "MHS155314024";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Maaf, driver class tidak ditemukan");
            System.out.println(ex.getMessage());
        }
        //Hubungkan ke sumber data
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + ":" + db, usr, pwd);
        } catch (SQLException ex) {
            System.out.println("Maaf, koneksi tidak berhasil");
            System.out.println(ex.getMessage());
        }
        if (conn != null) {
            System.out.println("Koneksi ke database berhasil terhubung");
        } else {
            System.out.println("Maaf, koneksi ke database gagal terbentuk");
        }
        return conn;
    }
    
    static Connection conn = getKoneksi();
    
    private static double PlayYes() throws SQLException{
        
        Statement statment = conn.createStatement();
                
        int yes = 0;
        String query = "SELECT COUNT(PLAY) FROM WEATHERDATA WHERE PLAY = 'YES'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            yes = rs.getInt("COUNT(PLAY)");
        }
        return yes;
    }
    
    private static double PlayNo() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'NO'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double TotalData() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double SunnyYes() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT COUNT(PLAY) FROM WEATHERDATA WHERE PLAY = 'YES' AND OUTLOOK = 'SUNNY'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double SunnyNo() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT COUNT(PLAY) FROM WEATHERDATA WHERE PLAY = 'NO' AND OUTLOOK = 'SUNNY'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double OvercastYes() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT COUNT(PLAY) FROM WEATHERDATA WHERE PLAY = 'YES' AND OUTLOOK = 'OVERCAST'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double OvercastNo() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'NO' AND OUTLOOK = 'OVERCAST'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double RainyYes() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'YES' AND OUTLOOK = 'RAINY'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double RainyNo() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'NO' AND OUTLOOK = 'RAINY'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double HotYes() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'YES' AND TEMPERATURE = 'HOT'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double HotNo() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'NO' AND TEMPERATURE = 'HOT'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double MildYes() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'YES' AND TEMPERATURE = 'MILD'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double MildNo() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'NO' AND TEMPERATURE = 'MILD'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double CoolYes() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'YES' AND TEMPERATURE = 'COOL'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double CoolNo() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'NO' AND TEMPERATURE = 'COOL'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double HighYes() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'YES' AND HUMIDITY = 'HIGH'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double HighNo() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'NO' AND HUMIDITY = 'HIGH'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double NormalYes() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'YES' AND HUMIDITY = 'NORMAL'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double NormalNo() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'NO' AND HUMIDITY = 'NORMAL'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double TrueYes() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'YES' AND WINDY = 'TRUE'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double TrueNo() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'NO' AND WINDY = 'TRUE'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double FalseYes() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'YES' AND WINDY = 'FALSE'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double FalseNo() throws SQLException{
        Statement statment = conn.createStatement();
                
        int no = 0;
        String query = "SELECT count(PLAY) from WEATHERDATA WHERE PLAY = 'NO' AND WINDY = 'FALSE'";
        ResultSet rs = statment.executeQuery(query);
        while (rs.next()) {
            no = rs.getInt("COUNT(PLAY)");
        }
        return no;
    }
    
    private static double ProbabilitasYes() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(PlayYes()/TotalData()));
    }
    
    private static double ProbabilitasNo() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(PlayNo()/TotalData()));
    }
    
    private static double ProbabilitasSunnyYes() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(SunnyYes()/PlayYes()));
    }
    
    private static double ProbabilitasSunnyNo() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(SunnyNo()/PlayNo()));
    }
    
    private static double ProbabilitasOvercastYes() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(OvercastYes()/PlayYes()));
    }
    
    private static double ProbabilitasOvercastNo() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(OvercastNo()/PlayNo()));
    }
    
    private static double ProbabilitasRainyYes() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(RainyYes()/PlayYes()));
    }
    
    private static double ProbabilitasRainyNo() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(RainyNo()/PlayNo()));
    }
    
    private static double ProbabilitasHotYes() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(HotYes()/PlayYes()));
    }
    
    private static double ProbabilitasHotNo() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(HotNo()/PlayNo()));
    }
    
    private static double ProbabilitasMildYes() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(MildYes()/PlayYes()));
    }
    
    private static double ProbabilitasMildNo() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(MildNo()/PlayNo()));
    }
    
    private static double ProbabilitasCoolYes() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(CoolYes()/PlayYes()));
    }
    
    private static double ProbabilitasCoolNo() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(CoolNo()/PlayNo()));
    }
    
    private static double ProbabilitasHighYes() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(HighYes()/PlayYes()));
    }
    
    private static double ProbabilitasHighNo() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(HighNo()/PlayNo()));
    }
    
    private static double ProbabilitasNormalYes() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(NormalYes()/PlayYes()));
    }
    
    private static double ProbabilitasNormalNo() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(NormalNo()/PlayNo()));
    }
    
    private static double ProbabilitasTrueYes() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(TrueYes()/PlayYes()));
    }
    
    private static double ProbabilitasTrueNo() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(TrueNo()/PlayNo()));
    }
    
    private static double ProbabilitasFalseYes() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(FalseYes()/PlayYes()));
    }
    
    private static double ProbabilitasFalseNo() throws SQLException{
        return Double.parseDouble(new DecimalFormat("##.##").format(FalseNo()/PlayNo()));
    }
    
    private static void PerhitunganBayes(String outlook, String temperature, 
            String humidity, String windy) throws SQLException{
        double bayes=0, outlYes=0, tempYes=0, humYes=0, winYes= 0, outlNo=0, 
                tempNo=0, humYNo=0, winNo= 0;
        if (outlook == "SUNNY") {
            outlYes = ProbabilitasSunnyYes();
            outlNo = ProbabilitasSunnyNo();
        } else if(outlook == "OVERCAST"){
            outlYes = ProbabilitasOvercastYes();
            outlNo = ProbabilitasOvercastNo();
        } else if(outlook == "RAINY"){
            outlYes = ProbabilitasRainyYes();
            outlNo = ProbabilitasRainyNo();
        }
        
        if (temperature == "HOT") {
            tempYes = ProbabilitasHotYes();
            tempNo = ProbabilitasHotNo();
        } else if(outlook == "MILD"){
            tempYes = ProbabilitasMildYes();
            tempNo = ProbabilitasMildNo();
        } else if(outlook == "COOL"){
            tempYes = ProbabilitasCoolYes();
            tempNo = ProbabilitasCoolNo();
        }
        
        if (humidity == "HIGH") {
            humYes = ProbabilitasHotYes();
            humYNo = ProbabilitasHotNo();
        } else if(outlook == "NORMAL"){
            humYes = ProbabilitasMildYes();
            humYNo = ProbabilitasMildNo();
        }
        
        if (windy == "TRUE") {
            winYes = ProbabilitasHotYes();
            winNo = ProbabilitasHotNo();
        } else if(outlook == "FALSE"){
            winYes = ProbabilitasMildYes();
            winNo = ProbabilitasMildNo();
        }
    }

}
