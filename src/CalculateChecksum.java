import tools.ChecksumCalculator;

public class CalculateChecksum {


    public static void main(String[] args) {
        //Adding calculation
        System.out.println("Checksum Add result: " + ChecksumCalculator.Add("14FE0131", 0, 3));
        System.out.println("Checksum Subtract result: " + ChecksumCalculator.Subtract("14FE0131", 0, 3));
        System.out.println("Checksum BitwiseAND result: " + ChecksumCalculator.BitwiseAND("14FE0131", 0, 3));
        System.out.println("Checksum BitwiseOr result: " + ChecksumCalculator.BitwiseOR("14FE0131", 0, 3));
    }
}
