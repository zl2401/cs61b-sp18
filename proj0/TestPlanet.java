public class TestPlanet {
	public static void main(String[] args) {
		double sun_xx = 1.0e12, sun_yy = 2.0e11, sun_m = 2.0e30;
		double sat_xx = 2.3e12, sat_yy = 9.5e11, sat_m = 6.0e26;
		Planet sun = new Planet(sun_xx, sun_yy, 0.0, 0.0, sun_m, "sun");
		Planet sat = new Planet(sat_xx, sat_yy, 0.0, 0.0, sat_m, "sat");
		System.out.print("the force between two planets is ");
		System.out.println(sun.calcForceExertedBy(sat));
	}
}