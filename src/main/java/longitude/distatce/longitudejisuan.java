package longitude.distatce;

public class longitudejisuan {
	public static double getDistatce(double lat1, double lat2, double lon1,    double lon2) {
        double R = 6371;
        double distance = 0.0;
        double dLat = (lat2 - lat1) * Math.PI / 180;
        double dLon = (lon2 - lon1) * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1 * Math.PI / 180)
                * Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        distance = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))) * R;
        return distance;
    }
	
	public static void main(String args[]){
		double lat1 = 28.151;
		double lat2 = 28.2524;
		double lon1 = 112.425;
		double lon2 = 112.426;
		System.out.println(getDistatce(lat1,lat2,lon1,lon2));
	}
}
