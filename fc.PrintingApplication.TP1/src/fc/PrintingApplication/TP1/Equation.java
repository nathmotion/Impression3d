
public class Equation {
public int Dy;
public int Dx;
//public int Dc;
public int a;
public int b;
public int c;
public Point p1;
public Point p2;
Segment s;
  
  
	public Equation(){
		
	}

	public Equation (Arrete A){
		Dy=(int)A.s2.y - (int)A.s1.y;
		Dx=(int)A.s2.x - (int)A.s1.x;

		a=-Dy;
		b=Dx;

		c=(int)(Dy*A.s1.x)-(int)(Dx*A.s1.y);  

	}
	
	public Equation (Point p1,Point p2){
		Dy=(int)p2.y -(int) p1.y;
		Dx=(int)p2.x - (int)p1.x;
		this.p1=p1;
		this.p2=p2;
		this.s=new Segment(p1,p2);
		a=-Dy;
		b=Dx;

		c=(int)(Dy*p1.x)-(int)(Dx*p1.y);  

	}
	public Equation (Segment s){
		Dy=(int)s.p2.y - (int)s.p1.y;
		Dx=(int)s.p2.x - (int)s.p1.x;
		this.s=s;
		a=-Dy;
		b=Dx;

		c=(int)(Dy*s.p1.x)-(int)(Dx*s.p1.y);  

	}
	

	// 
	/*public double resEqua(Point2D p){
		return 
	}*/
}
