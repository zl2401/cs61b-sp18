public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static double g = 6.67e-11;

	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double d;
		d = Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
		return d;
	}

	public double calcForceExertedBy(Planet p) {
		double d = this.calcDistance(p);
		double f = Planet.g * mass * p.mass / (d * d);
		return f;
	}

	public double calcForceExertedByX(Planet p) {
		double f = this.calcForceExertedBy(p);
		double dx = p.xxPos - xxPos;
		double d = this.calcDistance(p);
		double fx = f * dx / d; 
		return fx;
	}

	public double calcForceExertedByY(Planet p) {
		double f = this.calcForceExertedBy(p);
		double dy = p.yyPos - yyPos;
		double d = this.calcDistance(p);
		double fy = f * dy / d; 
		return fy;
	}

	public double calcNetForceExertedByX(Planet[] arrayP) {
		int num_P = arrayP.length;
		double netFx = 0.0;
		for (int i = 0; i < num_P; i++) {
			if (this.equals(arrayP[i])) {
				continue;
			}
			netFx += this.calcForceExertedByX(arrayP[i]);
		} 
		return netFx;
	}

	public double calcNetForceExertedByY(Planet[] arrayP) {
		int num_P = arrayP.length;
		double netFy = 0.0;
		for (int i = 0; i < num_P; i++) {
			if (this.equals(arrayP[i])) {
				continue;
			}
			netFy += this.calcForceExertedByY(arrayP[i]);
		} 
		return netFy;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel += aX * dt;
		yyVel += aY * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}

	public void draw() {
		String imageToDraw = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, imageToDraw);
	}
}