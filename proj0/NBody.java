public class NBody {
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}
	
	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int num_P = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[num_P];
		for (int i = 0; i < num_P; i++) {
			double pX = in.readDouble();
			double pY = in.readDouble();
			double vX = in.readDouble();
			double vY = in.readDouble();
			double m = in.readDouble();
			String gif = in.readString();
			planets[i] = new Planet(pX, pY, vX, vY, m, gif);
		} 
		return planets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		int num_P = planets.length;		
		String imgname = "starfield.jpg";
		String imgnameFull = "images/" + imgname;
		int waitTimeMilliseconds = 10;
		
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, imgnameFull);

		for (Planet p: planets) {
			p.draw();
		}

		StdDraw.show();

		double currentTime = 0.0;
		while (currentTime < T) {
			double[] xForces = new double[num_P];
			double[] yForces = new double[num_P];
			for (int i = 0; i < num_P; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for (int i = 0; i < num_P; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.setScale(-radius, radius);
			StdDraw.clear();
			StdDraw.picture(0, 0, imgnameFull);

			for (Planet p: planets) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(waitTimeMilliseconds);

			currentTime += dt;
		} 

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}